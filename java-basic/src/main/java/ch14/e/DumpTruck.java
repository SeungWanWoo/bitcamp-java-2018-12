package ch14.e;

public class DumpTruck extends Truck{
  boolean dump;
  @Override
  public void run() {
    System.out.println("꽈당꽈당 ~~ 달린다!");
  }

}
