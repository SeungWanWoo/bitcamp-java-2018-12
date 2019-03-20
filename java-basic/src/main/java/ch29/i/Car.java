package ch29.i;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//빈의 이름을 지정하지 않으면 클래스 이름을 빈의 이름으로 사용한다.
//단, 첫 알파벳은 소문자로 한다.
//패키지 이름은 포함하지 않는다.
//XML 선언으로 빈을 생성할 때 이름을 지정하지 않으면
//"패키지명.클래스명#번호" 형태로 이름이 지어진다.
//그러나 @Component 애노테이션을 사용할 때는 
//클래스 명만 빈의 이름으로 사용된다.
//@Component("mycar") => 빈의 이름이 mycar로 설정된다.
@Component  // => 빈의 이름이 car로 설정된다.
public class Car {
  private String maker;
  private String model;
  private int cc;
  private int valve;
  private boolean auto;
  private Date createdDate;
  @Autowired private BlackBox blackBox;

  @Override
  public String toString() {
    return "Car [maker=" + maker + ", model=" + model + ", cc=" + cc + ", valve=" + valve
        + ", auto=" + auto + ", createdDate=" + createdDate + ", blackBox=" + blackBox + "]";
  }

  public Car() {
    System.out.println("Car()");
  }
  
  public Date getCreatedDate() {
    return createdDate;
  }
  
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getMaker() {
    return maker;
  }
  public void setMaker(String maker) {
    this.maker = maker;
  }
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    this.model = model;
  }
  public int getCc() {
    return cc;
  }
  public void setCc(int cc) {
    this.cc = cc;
  }
  public int getValve() {
    return valve;
  }
  public void setValve(int valve) {
    this.valve = valve;
  }
  public boolean isAuto() {
    return auto;
  }
  public void setAuto(boolean auto) {
    this.auto = auto;
  }
}
