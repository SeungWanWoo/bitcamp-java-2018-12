package com.eomcs.lms;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.mybatis.DaoFactory;
import com.eomcs.mybatis.SqlSessionFactoryProxy;
import com.eomcs.mybatis.TransactionManger;

@ComponentScan(basePackages="com.eomcs.lms")
public class AppConfig {
  // BoardDao 구현체를 만들어 주는 메서드
  @Bean
  public BoardDao boardDao(DaoFactory daoFactory) {
    return daoFactory.create(BoardDao.class);
  }
  
  // LessonDao 구현체를 만들어 주는 메서드
  @Bean
  public LessonDao lessonDao(DaoFactory daoFactory) {
    return daoFactory.create(LessonDao.class);
  }
  
  // MemberDao 구현체를 만들어 주는 메서드
  @Bean
  public MemberDao memberDao(DaoFactory daoFactory) {
    return daoFactory.create(MemberDao.class);
  }
  
  // PhotoBoardDao 구현체를 만들어 주는 메서드
  @Bean
  public PhotoBoardDao PhotoBoardDao(DaoFactory daoFactory) {
    return daoFactory.create(PhotoBoardDao.class);
  }
  
  @Bean
  public PhotoFileDao PhotoFileDao(DaoFactory daoFactory) {
    return daoFactory.create(PhotoFileDao.class);
  }
  
  // SqlSessionFactoryProxy 객체를 만들어 주는 메서드
  @Bean
  public SqlSessionFactoryProxy sqlSessionFactoryProxy() throws Exception {
    // Mybatis의 SqlSessionFactory 준비 => SqlSessionFactoryProxy에 포장하여 리턴
    // SqlSessionFactory 구현체의 프록시를 만든다.
    // => 트랜잭션 관리를 위해서이다.
    return new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(
        Resources.getResourceAsStream("com/eomcs/lms/conf/mybatis-config.xml")));
  }
  // TransactionManager 객체를 만들어 주는 메서드
  @Bean
  public TransactionManger transactionManger(
      SqlSessionFactoryProxy sqlSessionFactoryProxy) {
    return new TransactionManger(sqlSessionFactoryProxy);
  }
  
  // DaoFactory 객체를 만들어 주는 메서드
  // => Dao구현체가 생성됨
  @Bean
  public DaoFactory daoFactory(SqlSessionFactoryProxy sqlSessionFactoryProxy) {
    return new DaoFactory(sqlSessionFactoryProxy);
  }
  
}  
