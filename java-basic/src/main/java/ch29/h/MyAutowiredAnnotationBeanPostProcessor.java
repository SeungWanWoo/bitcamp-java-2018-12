package ch29.h;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyAutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

  // 생성된 모든 객체를 기록한다.
  HashMap<Class<?>, Object> beans = new HashMap<>();

  // 파라미터 값이 준비되지 않아서 호출이 연기된 @Autowired 메서드를 기록한 맵.
  HashMap<Class<?>, List<AutowiredMethod>> autowiredMethodMap = new HashMap<>();


  // 객체에 대해 모든 초기화가 끝난 후에 @Autowired 애노테이션을 처리하자!
  // 따라서 다음 메서드만 오버라이딩 한다.
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

    // 생성된 객체를 기록한다.
    // => 나중에 의존 객체를 주입할 때 사용할 것이다.
    beans.put(bean.getClass(), bean);

    // 항상 어떤 객체가 생성되면 자신을 원하는 setter가 있는지 검사하여
    // 그 메서드를 호출해야 한다.
    callAutowiredMethod(bean);
    
    System.out.println("@Autowired 애노테이션 처리: ");

    // 이 객체의 모든 public 메서드를 꺼낸다.
    Method[] methods = bean.getClass().getMethods();
    for (Method m : methods) {
      // 메서드에서 @Autowired 애노테이션이 있는지 확인한다.
      Autowired anno = m.getAnnotation(Autowired.class);
      if (anno == null) // @Autowired가 아닌 메서드는 무시한다.
        continue;

      // 애노테이션이 있다면 이 셋터 메서드가 어떤 타입의 값을 원하는지 알아본다.
      Class<?> paramType = m.getParameters()[0].getType();
      // setter가 원하는 파라미터 값이 있는지 확인한다.
      if (isRegistered(paramType)) {
        // setter가 원하는 파라미터 값이 있다면 즉시 호출하여 의존 객체를 주입한다.
        try {
          m.invoke(bean, getDependency(paramType));
        } catch (Exception e) {
          e.printStackTrace();
        }
      } else {
        // setter가 원하는 파라미터 값이 없다면,
        // 일단 그 값이 나타날 때까지 기다리기 호출을 연기하자!
        // 그 파라미터 타입의 값을 원하는 메서드 정보를 기록한다.
        addAutowiredMethod(paramType, new AutowiredMethod(bean, m));
      }
      /*
       * 객체 생성했어? 그럼 오토와이어드가 붙었는지 확인해봐!
       * 없으면 다음 메서드, 있다면 파라미터 값을 꺼내!
       */
    }
    return bean;
  }

  private boolean isRegistered(Class<?> type) {
    return beans.get(type) != null ? true : false;
  }

  private Object getDependency(Class<?> type) {
    return beans.get(type);
  }

  private void addAutowiredMethod(
      Class<?> paramType, AutowiredMethod autowiredMethod) {
    List<AutowiredMethod> methods = autowiredMethodMap.get(paramType);
    if (methods == null) { // 파라미터 타입의 값을 원하는 setter가 등록된 적이 없다면,
      methods = new ArrayList<>();  // 새 목록을 만들고
      autowiredMethodMap.put(paramType, methods); // 일단 맵에 저장한다.
    }
    // 지정된 파라미터 타입의 값을 원하는 setter를 추가한다.
    methods.add(autowiredMethod);
  }
  private void callAutowiredMethod(Object paramValue) {
    // 이 타입의 빈을 원하는 세터 목록을 꺼낸다.
    List<AutowiredMethod> setters =
        autowiredMethodMap.get(paramValue.getClass());
      
    if (setters == null)
      return;
    
    for(AutowiredMethod setter : setters) {
      try {
      setter.method.invoke(setter.object, paramValue);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  class AutowiredMethod {
    Object object; // 메서드를 호출할 때 사용할 인스턴스
    Method method; // @Autowired가 붙은 메서드

    public AutowiredMethod(Object object, Method method) {
      this.object = object;
      this.method = method;
    }
  }
}
