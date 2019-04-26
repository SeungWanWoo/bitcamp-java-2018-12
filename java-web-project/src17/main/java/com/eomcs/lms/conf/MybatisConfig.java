package com.eomcs.lms.conf;

import javax.sql.DataSource;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration // Spring IoC 컨테이너 설정을 수행하는 클래스임을 표시한다.
@MapperScan("com.eomcs.lms.dao")
public class MybatisConfig {
  final static Logger logger = LogManager.getLogger(MybatisConfig.class);

  public MybatisConfig() {
    logger.debug("MybatisConfig 객체 생성...");
  }
  @Autowired Environment env;

  @Bean
  public SqlSessionFactory SqlSessionFactory(
      DataSource dataSource,
      ApplicationContext appCtx) throws Exception {

    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    
    factoryBean.setDataSource(dataSource);
    factoryBean.setTypeAliasesPackage("com.eomcs.lms.domain");
    factoryBean.setMapperLocations(
        appCtx.getResources("classpath:/com/eomcs/lms/mapper/*.xml"));
    
    // Mybatis에서 로그를 다룰 때 사용할 라이브러리를 지정한다.
    LogFactory.useLog4J2Logging();
    
    return factoryBean.getObject();
  }
}  
