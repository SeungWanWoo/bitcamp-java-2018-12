// Serialize - serialVersionUID 스태틱 필드 사용하기

package ch22.f;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Test03_1 {
  
  public static void main(String[] args) {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("score3.data"))) {
      
      // 인스턴스 출력하기
      
      // Score3 클래스는 java.io.Serializable을 구현하였다.
      // serialVersionUID의 값은 100으로 설정하였다.
      //
      out.writeObject(new Score3("홍길동", 100, 100, 100));
      
      System.out.println("출력 완료!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
