// 요청 핸들러의 아규먼트 - 도메인 객체(값 객체; Value Object)로 요청 파라미터 값 받기
package bitcamp.app1; 

import java.beans.PropertyEditorSupport;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/c04_4") // 컨트롤러에 URL을 매핑한다.
public class Controller04_4 {

  // 클라이언트가 보낸 요처 파라미터(String 타입) 값을
  // request handler의 아규먼트 타입(String, int, boolean 등)의 값으로 바꿀 때
  // primitive type에 대해서는 자동으로 변환해 준다.
  // 그 외의 타입에 대해서는 프로퍼티 에디터(타입 변환기)가 없으면 예외를 발생 시킨다.
  // 테스트:
  // .../c04_4/h1?model=sonata&capacity=5&auto=true&createdDate=2019-4-19
  @GetMapping("h1")
  @ResponseBody
  public void handler1(
      PrintWriter out,
      String model,
      int capacity, // String ====> int : Integer.parseInt(String)
      boolean auto, // String ====> boolean : Boolean.parseBoolean(String)
      Date createdDate // 기존 String에 대해서는 아무 무리없이 작동되었으나 Date 타입으로 바꾸면
      // 바로 예외를 띄운다. (No Matching Editor or conversion strategy found)
      // 프로퍼티 에디터를 설정하지 않으면 변환 오류 발생
      ) {
    out.printf("model=%s\n", model);
    out.printf("capacity=%s\n", capacity);
    out.printf("auto=%s\n", auto);
    out.printf("createdDate=%s\n", createdDate);
  }
  //테스트:
  // .../c04_4/h2?car=sonata,5,true,2019-4-19
  @GetMapping("h2")
  @ResponseBody
  public void handler2(
      PrintWriter out,
      // 콤마(,)로 구분된 문자열을 Car 객체로 변환하기?
      // => String ==> Car 프로퍼티 에디터를 등록하면 된다.
      @RequestParam("car") Car car
      ) {
    out.println(car);
  }
  
  //테스트:
  // .../c04_4/h3?engine=bitengine,3500,16
  @GetMapping("h3")
  @ResponseBody
  public void handler3(
      PrintWriter out,
      // 콤마(,)로 구분된 문자열을 Car 객체로 변환하기?
      // => String ==> Car 프로퍼티 에디터를 등록하면 된다.
      @RequestParam("engine") Engine engine
      ) {
    out.println(engine);
  }

  // 프로퍼티 에디터를 프론트 컨트롤러에게 적용하기
  // => 프론트 컨트롤러는 request handler를 호출하기 전에
  //    넘겨줄 아규먼트 값을 준비한다.
  //    각 아규먼트 값을 준비할 때 @InitBinder가 표시된 메서드를 호출하여
  //    프로퍼티 에디터(변환기)를 준비시킨다.
  //    그리고 이 준비된 값 변환기(프로퍼티 에디터)를 이용하여 파라미터 값을
  //    request handler의 아규먼트가 원하는 타입의 값을 바꿔
  //    아규먼트 개수 만큼 이 메서드를 호출한다.
  // => 따라서 프로퍼티 에디터를 적용하려면 
  //    @InitBinder가 표시된 메서드가 적절한 지점이다.
  //    즉 이 메서드에 프로퍼티 에디터를 등록하는 코드를 둔다.
  //
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    // System.out.println("Controller04_4.ok()");
    // 프로퍼티 에디터를 등록하려면 그 일을 수행할 객체(WebDataBinder)가 필요하다.
    // request handler처럼 아규먼트를 선언하여 
    // 프론트 컨트롤러에게 달라고 요청하라.
    
    // String ==> Date 프로퍼티 에디터 준비
    DatePropertyEditor propEditor = new DatePropertyEditor();
    
    // WebDataBinder에 등록하기
    binder.registerCustomEditor(
        java.util.Date.class, // String을 Date 타입으로 바꾸는 에디터임을 지정한다.
        propEditor // 바꿔주는 일을 하는 프로퍼티 에디터를 등록한다.
        );
    
    CarPropertyEditor carEditor = new CarPropertyEditor();
    
    binder.registerCustomEditor(
        Car.class, // String을 Car 타입으로 바꾸는 에디터임을 지정한다.
        carEditor // 바꿔주는 일을 하는 프로퍼티 에디터를 등록한다.
        );
    EnginePropertyEditor engineEditor = new EnginePropertyEditor();
    binder.registerCustomEditor(
        Engine.class, 
        engineEditor);
  }

  // PropertyEditor 만들기
  // => 문자열을 특정 타입의 프로퍼티 값으로 변환시킬 때 사용하는 에디터이다.
  // => java.beans.PropertyEditor 인터페이스를 구현해야 한다.
  // => PropertyEditor를 직접 구현하면 너무 많은 메서드를 오버라이딩 해야하기 때문에
  //    자바에서는 도우미 클래스인 PropertyEditorSupport 클래스를 제공한다.
  //    이 클래스는 PropertyEditor를 미리 구현하였다.
  //    따라서 이 클래스를 상속 받는 것이 더 낫다.
  class DatePropertyEditor extends PropertyEditorSupport {

    // yyyy-MM-dd 형태의 문자열을 java.util.Date 객체로 만들어주는 클래스를 준비한다.
    SimpleDateFormat format;

    public DatePropertyEditor() {
      // 프로퍼티 에디터를 사용하는 측에서 어떤 형식의 문자열을 Date 객체로 만들 것인지
      // 설정하지 않았다면 기본으로 yyyy-MM-dd 형식의 문자열을 Date 객체로 만들도록
      // 준비한다.
      format = new SimpleDateFormat("yyyy-MM-dd");
    }

    public DatePropertyEditor(SimpleDateFormat format) {
      // 물론 프로퍼티 에디터를 사용하는 측에서 날짜에 대한 문자열 형식을 지정할 수도 있다.
      this.format = format;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
      System.out.println("DatePropertyEditor.setAsText()");
      // 프로퍼티 에디터를 사용하는 측(예: 프론트 컨트롤러)에서
      // 문자열을 Date 객체로 바꾸기 위해 이 메서드를 호출할 것이다.
      // 그러면 이 메서드에서 문자열을 프로퍼티가 원하는 타입으로 변환한 후 저장하면 된다.
      try {
        Date date = format.parse(text); // String ==> java.util.Date
        setValue(date);   // 내부에 저장

      } catch (ParseException e) {
        throw new IllegalArgumentException(e);
      }
    }

    @Override
    public Object getValue() {
      System.out.println("DatePropertyEditor.getValue()");
      // 이 메서드는 프로퍼티 에디터를 사용하는 측(예: 프론트 컨트롤러)에서
      // 변환된 값을 꺼낼 때 호출된다.
      // 이 메서드를 오버라이딩한 이유는 이 메서드가 호출된 것을
      // 확인하기 위함이다. 원래는 오버라이딩 해야 할 이유가 없다.
      return super.getValue();
    }
  }
  
  class CarPropertyEditor extends PropertyEditorSupport {

    SimpleDateFormat format;
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
      try {
        Car car = new Car();
        String[] carValues = text.split(",");
        car.setModel(carValues[0]);
        car.setCapacity(Integer.parseInt(carValues[1]));
        car.setAuto(Boolean.parseBoolean(carValues[2]));
        format = new SimpleDateFormat("yyyy-MM-dd");
        car.setCreatedDate(format.parse(carValues[3]));
        setValue(car);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }
  }
  
  class EnginePropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
      try {
        Engine engine = new Engine();
        String[] values = text.split(",");
        engine.setModel(values[0]);
        engine.setCc(Integer.parseInt(values[1]));
        engine.setValve(Integer.parseInt(values[2]));
        
        setValue(engine);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }
  }
}


