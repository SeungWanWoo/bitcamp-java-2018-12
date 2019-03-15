package com.eomcs.lms;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 특정 패키지를 탐색하여 @Component 애노테이션이 붙은 클래스에 대해 인스턴스를 생성하게
// 만드는 애너테이션
@ComponentScan(basePackages="com.eomcs.lms")
// .properties 파일을 로딩시키는 애너테이션
@PropertySource("classpath:/com/eomcs/lms/conf/jdbc.properties")
// Spring의 트랜잭션 관리자를 활성화시킬 때 사용한다.
@EnableTransactionManagement

// Mybatis 에서 DAO 구현체를 자동 생성하게 할 때 사용한다.
// DAO 인터페이스가 있는 패키지를 지정한다.
@MapperScan("com.eomcs.lms.dao")
public class AppConfig {
  
  // @Autowired 
  // => 빈 컨테이너에서 값을 꺼내 지정한 타입의 값을 꺼내 필드에 넣으라는 명령이다.
  //    예) 다음은 빈 컨테이너에서 Environment 타입의 객체를 찾아 env라는 필드에
  //        주소를 주입하라는 뜻.
  @Autowired Environment env;
  // Environment?
  // => @PropertySource 애노테이션에서 지정한 .properties 파일의 값을 꺼내는 일을 한다.
  
  // @Bean
  // => 객체를 생성해주는 메서드(Factory 메서드)를 표시할 때 사용한다.
  // => Spring IoC 컨테이너는 이 애노테이션이 붙은 메서드를 호출하여 그 리턴 값을 보관한다.
  // => 객체를 보관할 때 사용할 이름은 Bean 애노테이션에 지정한 이름이다.
  // => 만약 Bean 애노테이션에 지정하지 않으면 메서드 이름을 사용하여 객체를 보관한다.
  //
  // DB 커넥션풀 기능을 수행할 DataSource 객체를 생성하는 메서드
  @Bean
  public DataSource dataSource() {
    // DBMS 정보는 jdbc.properties 파일에 있다.
    // 위에서 주입받은 Spring IoC 컨테이너에서 주입 받은 env 객체를 사용하여
    // 이 파일의 값을 꺼내면 된다.
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName(env.getProperty("jdbc.driver"));
    ds.setUrl(env.getProperty("jdbc.url"));
    ds.setUsername(env.getProperty("jdbc.username"));
    ds.setPassword(env.getProperty("jdbc.password"));
    return ds;
  }
  
  // Connection의 commit/rollback을 처리할 트랜잭션 관리자를 생성하는 메서드
  // => 트랜잭션 매니저의 이름은 "transactionManager" 이어야 한다.
  //    만약 다른 이름을 사용한다면 트랜잭션 매니저를 다룰 때마다 
  //    정확하게 그 이름을 지정해야 하는 번거로움이 발생한다.
  // => Spring은 트랜잭션 관리자로서 PlatformTransactionManager 구현체를 사용한다.
  // => 트랜잭션 관리자는 Connection을 다루는 일을 하고,
  //    Connection은 DataSource로부터 얻는다.
  //    따라서 트랜잭션 관리자를 만드려면 DataSource 객체가 있어야 한다.
  // => 메서드의 파라미터로 DataSource 객체를 요구하라!
  //    그러면 Spring IoC 컨테이너가 메서드를 호출할 때 파라미터 값으로 주입해 줄 것이다.
  @Bean
  public PlatformTransactionManager transactionManager(
      DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
  
  //SqlSessionFactory를 생성하는 메서드
  @Bean
  public SqlSessionFactory SqlSessionFactory(
      DataSource dataSource,
      ApplicationContext appCtx) throws Exception {
    // mybatis에서 SqlSessionFactory를 생성할 때 사용하라고 제공하는 객체이다.
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    
    // SqlSessionFactory 객체를 생성하는데 필요한 객체를 주입한다.
    factoryBean.setDataSource(dataSource);
    
    // 도메인 클래스의 별명을 자동으로 생성하도록 도메인 클래스가 들어있는 패키지를 지정한다.
    factoryBean.setTypeAliasesPackage("com.eomcs.lms.domain");
    
    // Sql 매퍼 파일이 있는 패키지를 등록한다.
    factoryBean.setMapperLocations(
        appCtx.getResources("classpath:/com/eomcs/lms/mapper/*.xml"));
    
    return factoryBean.getObject();
  }
  
  /*
  // Dao 구현체 자동 생성하기 방법1:
  // => mybatis에서 Spring과 함께 사용하라고 제공해주는 SqlSessionTemplate을 사용한다.
  // => @MapperScan 애노테이션을 사용하면 더 간단히 DAO 구현체를 자동 생성할 수 있다.
  @Bean
  public SqlSessionTemplate SqlSessionTemplate(
      SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
  
  // BoardDao 구현체를 만들어 주는 메서드
  @Bean
  public BoardDao boardDao(
      SqlSessionTemplate sqlSessionTemplate) throws Exception {
    return sqlSessionTemplate.getMapper(BoardDao.class);
  }
  
  // LessonDao 구현체를 만들어 주는 메서드
  @Bean
  public LessonDao lessonDao(
      SqlSessionTemplate sqlSessionTemplate) throws Exception {
    return sqlSessionTemplate.getMapper(LessonDao.class);
  }
  
  // MemberDao 구현체를 만들어 주는 메서드
  @Bean
  public MemberDao memberDao(
      SqlSessionTemplate sqlSessionTemplate) throws Exception {
    return sqlSessionTemplate.getMapper(MemberDao.class);
  }
  
  // PhotoBoardDao 구현체를 만들어 주는 메서드
  @Bean
  public PhotoBoardDao PhotoBoardDao(
      SqlSessionTemplate sqlSessionTemplate) throws Exception {
    return sqlSessionTemplate.getMapper(PhotoBoardDao.class);
  }
  
  //PhotoFileDao 구현체를 만들어 주는 메서드
  @Bean
  public PhotoFileDao PhotoFileDao(
      SqlSessionTemplate sqlSessionTemplate) throws Exception {
    return sqlSessionTemplate.getMapper(PhotoFileDao.class);
  }
  */
}  
