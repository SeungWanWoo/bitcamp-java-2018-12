package design_pattern.observer.after;

public class EngineOilCarObserver implements CarObserver {

  @Override
  public void carStarted() {
    System.out.println("엔진오일 유무 검사");
  }

  @Override
  public void carStopped() {
    
  }

}
