package com.eomcs.lms.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.ibatis.logging.LogFactory;

@WebListener
public class AppInitListener implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // 웹 애플리케이션을 시작할 때 MyBatis가 사용할 로그 팩토리를 지정한다.
    // => Log4J2를 사용하도록 설정한다.
    LogFactory.useLog4J2Logging();
  }
}
