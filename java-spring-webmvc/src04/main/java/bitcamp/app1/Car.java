package bitcamp.app1;

import java.util.Date;

public class Car {
  protected String model;
  protected String maker;
  protected boolean auto;
  protected int capacity;
  protected Engine engine;
  protected Date createdDate;

  @Override
  public String toString() {
    return "Car [model=" + model + ", maker=" + maker + ", auto=" + auto + ", capacity=" + capacity
        + ", engine=" + engine + ", createdDate=" + createdDate + "]";
  }
  public int getCapacity() {
    return capacity;
  }
  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
  
  public String getModel() {
    return model;
  }
  public Engine getEngine() {
    return engine;
  }
  public void setEngine(Engine engine) {
    this.engine = engine;
  }
  public void setModel(String model) {
    this.model = model;
  }
  public String getMaker() {
    return maker;
  }
  public void setMaker(String maker) {
    this.maker = maker;
  }
  public boolean isAuto() {
    return auto;
  }
  public void setAuto(boolean auto) {
    this.auto = auto;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
}