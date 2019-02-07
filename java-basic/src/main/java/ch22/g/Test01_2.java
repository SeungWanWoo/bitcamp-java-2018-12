// Serialize - 인스턴스 필드의 값 읽기
package ch22.g;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Test01_2 {
  
  public static void main(String[] args) {
    try (ObjectInputStream in = new ObjectInputStream(
        new FileInputStream("score1.data"))) {
      
      Score1 score = (Score1) in.readObject();
      System.out.println(score);
      
      System.out.println("읽기 완료!");
      // getter/setter 없어도 필드 값을 읽는데 문제 없다.
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
