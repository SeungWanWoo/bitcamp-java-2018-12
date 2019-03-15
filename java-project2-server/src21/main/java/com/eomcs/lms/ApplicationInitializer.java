package com.eomcs.lms;

import java.util.Map;
import com.eomcs.lms.context.ApplicationContext;
import com.eomcs.lms.context.ApplicationContextException;
import com.eomcs.lms.context.ApplicationContextListener;

public class ApplicationInitializer implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      // IoC 컨테이너 준비
      ApplicationContext appCtx = 
          new ApplicationContext(AppConfig.class);
      
      // ServerApp 쪽에서 사용할 수 있도록 ApplicationContext를 맵에 저장한다.
      context.put("applicationContext", appCtx);

    } catch (Exception e) {
      throw new ApplicationContextException(e);
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
  }

}
