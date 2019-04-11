package com.eomcs.lms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import com.eomcs.lms.AppConfig;

// 웹 애플리케이션이 시작되거나 종료될 때 보고를 받는 클래스
// => Observer 디자인 패턴이 적용된 예이다.
//
//@WebListener // 애노테이션 대신에 web.xml에 선언해도 된다.
public class ContextLoaderListener implements ServletContextListener {
  final static Logger logger = LogManager.getLogger(ContextLoaderListener.class);
  
  static class WebAppConfig {
    static ServletContext servletContext;
    
    @Bean
    public ServletContext servletContext() {
      return WebAppConfig.servletContext;
    }
  }
  
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // Spring IoC 컨테이너가 객체를 생성하기 위해서 설정 클래스를 사용하기 전에
    // WebAppConfig 클래스에 대해 ServletContext를 주입한다.
    WebAppConfig.servletContext = sce.getServletContext();
    
    logger.info("Spring IoC 컨테이너 준비");
    ApplicationContext iocContainer = new AnnotationConfigApplicationContext(
        AppConfig.class, WebAppConfig.class);
    printBeans(iocContainer);

    ServletContext sc = sce.getServletContext();
    sc.setAttribute("iocContainer", iocContainer);
  }

  private void printBeans(ApplicationContext iocContainer) {
    String[] names = iocContainer.getBeanDefinitionNames();
    // 개발하는 동안 참고할 로그는 보통 debug 등급으로 출력한다.
    logger.debug("-----------------------------------------");
    for (String name : names) {
      logger.debug(String.format("빈 생성 됨 (객체명=%s, 클래스명=%s)",name,
          iocContainer.getBean(name).getClass().getName()));
    }
    logger.debug("-----------------------------------------");
  }
    
}
