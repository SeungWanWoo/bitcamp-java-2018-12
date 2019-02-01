// 인스턴스 읽기
package ch22.e;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class Test01_2 {

  public static void main(String[] args) {
    
    // score.data 파일에서 세 학생의 데이터를 읽어 Score 인스턴스로 생성하라.
    // => java.io.BufferedOutputStream 클래스를 사용하라.
    // => java.io.DataOutputStream 클래스를 사용하라.
    Score s1 = null;
    Score s2 = null;
    Score s3 = null;
    // 그리고 세 학생의 정보를 다음과 같은 형식으로 출력하라.
    // => 홍길동, 100, 100, 100, 300, 100
    
    try (FileInputStream in = new FileInputStream("score.data");
        BufferedInputStream in1 = new BufferedInputStream(in);
        DataInputStream in2 = new DataInputStream(in1)) {
      String sc1 = in2.readUTF();
      String sc2 = in2.readUTF();
      String sc3 = in2.readUTF();
      System.out.print(sc1);
      System.out.print(sc2);
      System.out.print(sc3);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("출력 완료!");
  }

}
