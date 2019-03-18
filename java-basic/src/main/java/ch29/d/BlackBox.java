package ch29.d;

public class BlackBox {
  private String maker;
  private String model;
  
  public BlackBox() {
    System.out.println("BlackBox()");
  }
  
  @Override
  public String toString() {
    return "BlackBox [maker=" + maker + ", model=" + model + "]";
  }
  public String getMaker() {
    return maker;
  }
  public void setMaker(String maker) {
    this.maker = maker;
    System.out.println("BlackBox.setMaker()");
  }
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    this.model = model;
    System.out.println("BlackBox.setModel()");
  }
  
  
}
