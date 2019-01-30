package design_pattern.decorator.before;

public class Test01 {
  
  public static void main(String[] args) {

    // 전기차 트럭을 만들어 보자!
    // => Hybrid를 상속 받고 트럭 기능을 덧붙인다.
    // => 트럭에는 Sedan 기능이 필요없지만,
    //    상속은 중간의 특정 기능을 뺄 수 없다.
    //    무조건 상속 받을 수 밖에 없다.
    HybridTruck h1 = new HybridTruck();
    h1.dump();
    
    System.out.println("----------------------------");
    
    // 만약 Hybrid 이면서 Convertible 기능을 갖는 자동차를 만들고 싶다면?
    // => Hybrid나 Convertible 둘 중 한개를 상속 받아서 구현해야 한다.
    // => 하위 클래스는 다른 클래스의 기능을 중복해서 개발해야 한다.
    //
    
    HybridConvertible h2 = new HybridConvertible();
    h2.open(true);
    h2.run2();
    
    // Convertible 자동차에 간단히 물건을 내리는 덤프 기능을 포함한다면?
    // 위의 경우와 마찬가지로 Truck 클래스나 Convertible 클래스 둘 중 하나를 상속 받아야
    // 하고, 둘 중 하나의 기능을 중복 작성해야 한다.
    // => 이것이 상속의 한계다.
    
  }
}
