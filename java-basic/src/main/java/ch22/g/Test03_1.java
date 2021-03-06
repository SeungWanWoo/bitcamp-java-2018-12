// Serialize - trainsient 필드

package ch22.g;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Test03_1 {
  
  public static void main(String[] args) {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("score3.data"))) {

      Score3 score = new Score3();
      score.setName("홍길동");
      score.setKor(100); // setter 내부에서 compute를 호출하여 
      score.setEng(100); // sum과 aver를 자동 계산한다.
      score.setMath(100);
      
      // 인스턴스 출력하기
      // => 내부 필드의 값을 가지고 계산한 값을 보관하는 필드는
      //    가능한 serialize 대상에서 제외해야 한다.
      // => 왜?
      //    출력 후 데이터의 왜곡을 막기 위함이다.
      //    출력한 파일을 조작해서 의도와 다른 값을 가지게 할 수 있기 때문이다.
      // => 방법?
      //    필드에 serialize 대상이 아니라고 표시하라!
      //    transient 한정자[제한자; modifier]를 붙인다.
      //    transient => 일시적인
      // => Score3 클래스의 sum 필드와 aver 필드에 transient를 붙이고
      //    출력해보라!
      //    해당 필드의 값이 출력되지 않을 것이다.
      out.writeObject(score);
      
      System.out.println("출력 완료!");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
