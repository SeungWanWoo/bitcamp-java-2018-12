// Serialize - trainsient 필드
package ch22.g;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Test03_2 {
  
  public static void main(String[] args) {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("score3.data"))) {
      
      // deserialize 할 때
      // private 필드의 값도 데이터를 읽어 저장한다.
      // 생성자나 setter를 호출하지 않는다.
      // 인스턴스를 생성한 후 직접 인스턴스의 필드 값을 저장한다.
      
      Score3 score = (Score3) in.readObject();
      System.out.println(score);
      
      // transient 필드는 보안상 serialize 되지 않았기 때문에
      // deserialize 한 후 계산을 다시해야 한다.
      // => 파일을 조작해서 합계와 평균을 왜곡하는 상황을 막을 수 있다. 
      score.compute();
      System.out.println(score);
      
      System.out.println("읽기 완료!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
