package com.eomcs.lms;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import com.eomcs.lms.context.ApplicationContextException;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.context.RequestMappingHandlerMapping;
import com.eomcs.lms.context.RequestMappingHandlerMapping.RequestMappingHandler;

public class ApplicationInitializer implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      // Spring IoC 컨테이너 준비
      ApplicationContext appCtx = 
          new AnnotationConfigApplicationContext(AppConfig.class);

      // ServerApp 쪽에서 사용할 수 있도록 ApplicationContext를 맵에 저장한다.
      context.put("applicationContext", appCtx);

      // RequestMappingHandlerMapping 객체 준비
      // 클라이언트 요청을 처리한 메서드 정보가 들어 있는 객체
      context.put("handlerMapping", 
          prepareRequestMappingHandlerMapping(appCtx));
      // 이 방법의 문제점으로는 위 객체를 실행하기전에 실행될 수 있다.
      // 해결점 : 옵저버 패턴을 적용하면 해결할 수 있다.
    } catch (Exception e) {
      throw new ApplicationContextException(e);
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
  }

  //bean 생성을 완료한 후 작업 수행
  public RequestMappingHandlerMapping prepareRequestMappingHandlerMapping(
      ApplicationContext iocContainer) {
    // RequestMappingHandler 정보를 관리할 객체 생성
    RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();

    // 빈 컨테이너에서 객체를 모두 꺼낸다.
    Collection<Object> beans = 
        iocContainer.getBeansWithAnnotation(Component.class).values();
    // 1) 빈컨테이너에서 객체 정보를 꺼낸다.
    for (Object bean : beans) {
      // 각 객체에 대해 @RequestMapping 메서드를 찾는다.
      Method[] methods = bean.getClass().getMethods();
      // 2) 빼온 객체에 대한 메서드 정보를 꺼낸다.
      for(Method method : methods) {
        // 3) 빼온 메서드 정보에서 해당 애너테이션이 붙었는지 확인
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if (requestMapping == null)
          continue;

        // 4) RequestMapping이 붙은 메서드를 찾았으면 그 정보를 RequestMappingHandler에 담는다.
        RequestMappingHandler handler = new RequestMappingHandler(bean, method);

        // 5) 그리고 이 요청 핸들러(@RequestMapping 애노테이션이 붙은 메서드)를 저장한다.
        handlerMapping.add(requestMapping.value(), handler);

        // RequestMapping에 명령어에 대한 메서드가 실행된다.
      }
    }
    // 최종적으로 해당하는 핸들러까지 저장한다.
    // ServerApp에서 꺼낼 수 있도록 RequestMappingHandlerMapping 객체를 리턴한다.
    return handlerMapping;
  }

}
