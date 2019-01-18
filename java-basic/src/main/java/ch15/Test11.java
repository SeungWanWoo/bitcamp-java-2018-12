// 해시코드의 응용 V - 사용자가 만든 클래스를 key로 사용하기 위해 
// hashCode()와 equals()를 오버라이딩 하기
package ch15;

import java.util.HashMap;

class Key2 {
  String contents;
  
  public Key2(String contents) {
    this.contents = contents;
  }

  @Override
  public String toString() {
    return "Key2 [contents=" + contents + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((contents == null) ? 0 : contents.hashCode());
    return result;
  }

 @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof Key2))
      return false;
    Key2 other = (Key2) obj;
    if (contents == null) {
      if (other.contents != null)
        return false;
    } else if (!contents.equals(other.contents))
      return false;
    return true;
  }
}

public class Test11 {

  public static void main(String[] args) {
    HashMap map = new HashMap();
    
    // String을 key로 사용해보자.
    Key2 k1 = new Key2("ok");
    Key2 k2 = new Key2("no");
    Key2 k3 = new Key2("haha");
    Key2 k4 = new Key2("ohora");
    Key2 k5 = new Key2("hul");

    map.put(k1,  new Student("홍길동", 20, false));
    map.put(k2,  new Student("임꺽정", 30, true));
    map.put(k3,  new Student("유관순", 17, true));
    map.put(k4,  new Student("안중근", 24, true));
    map.put(k5,  new Student("윤봉길", 22, false));
   
    System.out.println(map.get(k3));
    // key를 사용하여 값을 꺼내보자.
    Key2 k6 = new Key2("haha");
    
    // hashCode()를 재정의해주었기 때문에 hashCode() 값이 같게 나온다.
    // 그러나 equals()를 재정의해주지 않았기 때문에 아래 equals의 비교 결과는 다르게 나온다.
    // 만약 equals()를 재정의 해준다면 true 값을 얻을 것이다.
    // 그래서 hashCode()를 오버라이딩 할 때 equals()도 같이 재정의 해주는 것이다.
    // 만약 한 개라도 오버라이딩해주지 않는다면 우리는 원하는 값을 얻을 수 없게 되기 때문이다. 
    System.out.println(k3 == k6);   // 인스턴스는 다르지만, 
    System.out.println(k3.hashCode());  // hashCode는 같다
    System.out.println(k6.hashCode());  // hashCode는 같다.
    System.out.println(k3.equals(k6)); // equals()의 비교 결과도 같다.
    
    System.out.println(map.get(k6));
    Key2 k7 = new Key2("Haha");
    System.out.println(map.get(k7));
    
    // String 클래스는 equals()와 hashCode() 뿐만 아니라
    // toString() 까지 모두 오버라이딩 했다.
    
    
    // 최종 결론!
    // 모든 클래스는 Object로부터 상속받는다.
    // 그러나 Object로부터 그대로 상속받은 메서드가 마음에 들지 않는다면
    // 새롭게 만든 클래스에서 오버라이딩 해주면 된다.
    // 또한 값이 같게 나오기를 원한다면 equals()를 오버라이딩 해주며
    // 여기서 hashCode()도 동시에 해주는게 맞다.
  }
}
