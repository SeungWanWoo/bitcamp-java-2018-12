package ch30.f;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// XML 설정을 사용할 때는 이 클래스의 객체 생성을 XML에서 했기 때문에
// @Component 애노테이션을 붙이지 않았다.
// 이제는 XML에서 이 클래스의 객체를 만들지 않기 때문에 이 애노테이션을 붙여야 한다.
@Component

// 이 클래스가 AOP 기능을 수행하는 클래스임을 표시해야한다.
@Aspect
public class MyAdvice {
  
  // Pointcut을 미리 정의한다.
  // => 메서드 선언부에 붙여야 한다.
  // => 메서드의 파라미터는 없고, 구현을 비워둔다.
  // => 이 메서드는 pointcut을 지정하는 용도로만 사용한다.
  @Pointcut("execution(* ch30.f.X.*(..))")
  public void calculaotrOperation() {}
  /*
   * <aop:before pointcut="execution(* ch30.e.X.*(..)) and args(p2, p3, p1)"
                    method="doBefore" />
   */
  @Before("calculaotrOperation() and args(p2, p3, p1)")
  public void doBefore(String p1, int p2, int p3) {
    System.out.printf("%s.doBefore()\n", this.getClass().getName());
    System.out.printf("  => %s %d %d\n", p1, p2, p3);
  }
  
  /*
   * <aop:after pointcut-ref="pointcut1" method="doAfter"/>
   */
  @After("calculaotrOperation()")
  public void doAfter() {
    System.out.printf("%s.doAfter()\n", this.getClass().getName());
  }
  
  /*
   * <aop:after-returning 
                   pointcut-ref="pointcut1"
                   returning="rv"
                   method="doAfterReturning"/>
   */
  @AfterReturning(
      pointcut="calculaotrOperation()",
      returning="rv")
  public void doAfterReturning(Object rv) {
    System.out.printf("%s.doAfterReturning()\n", this.getClass().getName());
    System.out.printf("  => %d\n", rv);
  }
  
  /*
   * <aop:after-throwing 
                   pointcut-ref="pointcut1"
                   throwing="err"
                   method="doAfterThrowing"/>
   */
  @AfterThrowing(
      pointcut="calculaotrOperation()",
      throwing="err"
      )
  public void doAfterThrowing(Exception err) {
    System.out.printf("%s.doAfterThrowing()\n", this.getClass().getName());
    System.out.printf("  => %s\n", err.getMessage());
  }
}
