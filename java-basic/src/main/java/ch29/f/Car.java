package ch29.f;

import java.util.Date;

public class Car {
  private String maker;
  private String model;
  private int cc;
  private int valve;
  private boolean auto;
  private Date createdDate;
  private BlackBox blackBox;

  @Override
  public String toString() {
    return "Car [maker=" + maker + ", model=" + model + ", cc=" + cc + ", valve=" + valve
        + ", auto=" + auto + ", createdDate=" + createdDate + ", blackBox=" + blackBox + "]";
  }

  public BlackBox getBlackBox() {
    return blackBox;
  }

  public void setBlackBox(BlackBox blackBox) {
    this.blackBox = blackBox;
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
