// 인스턴스 읽기 - 배열 활용
package ch22.e;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class Test02_2 {

  public static void main(String[] args) {

    // score.data 파일에서 세 학생의 데이터를 읽어 Score 인스턴스로 생성하라.
    // => java.io.BufferedOutputStream 클래스를 사용하라.
    // => java.io.DataOutputStream 클래스를 사용하라.
    Score[] students = null;
    // 그리고 세 학생의 정보를 다음과 같은 형식으로 출력하라.
    // => 홍길동, 100, 100, 100, 300, 100

    try (DataInputStream in = new DataInputStream(
        new BufferedInputStream(
            new FileInputStream("score.data")))) {
      int length = in.readInt();
      students = new Score[length];

      for (int i = 0; i < length; i++) {
        Score s = new Score();
        s.setName(in.readUTF());
        s.setKor(in.readInt());
        s.setEng(in.readInt());
        s.setMath(in.readInt());
        students[i] = s;
      }

      for (Score s : students) {
        System.out.printf("%s, %d, %d, %d, %d, %.1f\n", 
            s.getName(), s.getKor(), s.getEng(), s.getMath(), 
            s.getSum(), s.getAver());
      }

      /*String sc1 = in2.readUTF();
      String sc2 = in2.readUTF();
      String sc3 = in2.readUTF();
      String[] ss1 = sc1.split(",");
      s1.setName(ss1[0]);
      s1.setKor(Integer.parseInt(ss1[1]));
      s1.setEng(Integer.parseInt(ss1[2]));
      s1.setMath(Integer.parseInt(ss1[3]));*/

      //System.out.print(sc1);
      //System.out.print(sc2);
      //System.out.print(sc3);
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("출력 완료!");
  }

}
