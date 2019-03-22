package com.eomcs.lms;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
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
    
    return factoryBean.getObject();
  }
}  
