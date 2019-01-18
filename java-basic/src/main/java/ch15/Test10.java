// 해시코드의 응용 IV - 사용자가 만든 클래스를 key로 사용하기
package ch15;

import java.util.HashMap;

class Key {
  String contents;
  
  public Key(String contents) {
    this.contents = contents;
  }

  @Override
  public String toString() {
    return "Key [contents=" + contents + "]";
  }
}

public class Test10 {

  public static void main(String[] args) {
    HashMap map = new HashMap();
    
    // String을 key로 사용해보자.
    Key k1 = new Key("ok");
    Key k2 = new Key("no");
    Key k3 = new Key("haha");
    Key k4 = new Key("ohora");
    Key k5 = new Key("hul");

    map.put(k1,  new Student("홍길동", 20, false));
    map.put(k2,  new Student("임꺽정", 30, true));
    map.put(k3,  new Student("유관순", 17, true));
    map.put(k4,  new Student("안중근", 24, true));
    map.put(k5,  new Student("윤봉길", 22, false));
   
    System.out.println(map.get(k3));
    // key를 사용하여 값을 꺼내보자.
    Key k6 = new Key("haha");
    
    System.out.println(k3 == k6);   // 인스턴스는 다르지만, 
    System.out.println(k3.hashCode());  // hashCode는 다르다.
    System.out.println(k6.hashCode());  // hashCode는 다르다.
    System.out.println(k3.equals(k6)); // equals()의 비교 결과도 같다.
    
    // Object로 상속받은 equals()와 hashCode()를 오버라이딩 하지 않으면
    // key 값이 같다 하더라도 hashCode() 값은 서로 다르게 나온다.
    // key로 사용하지 않으려 한다면 굳이 메서드를 재정의 할 필요가 없다.
    // 그러나 새롭게 클래스를 생성해서 그 생성한 인스턴스 필드를 key값으로 사용하고 싶다면
    // 재정의해주어야 한다.
    // k3와 k6의 내용물이 "haha"로 같다 하더라도, 
    // hashCode()의 리턴 값이 다르고, equals()의 비교 결과도 false 이기 때문에
    // HashMap 클래스에서는 서로 다른 key라고 간주한다.
    //
    System.out.println(map.get(k6));
    Key k7 = new Key("Haha");
    System.out.println(map.get(k7));
  }
}
