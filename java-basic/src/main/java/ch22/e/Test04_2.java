// 인스턴스 읽기 - ObjectInputStream 데코레이터 사용
package ch22.e;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Test04_2 {

  public static void main(String[] args) {

    // score.data 파일에서 세 학생의 데이터를 읽어 Score 인스턴스로 생성하라.
    // => java.io.BufferedOutputStream 클래스를 사용하라.
    // => java.io.DataOutputStream 클래스를 사용하라.
    
    ArrayList<Score> students = new ArrayList<>();
    // 그리고 세 학생의 정보를 다음과 같은 형식으로 출력하라.
    // => 홍길동, 100, 100, 100, 300, 100

    // ObjectInputStream
    // => DataInputStream의 기능을 포함한다.
    // => ObjectOutputStream.writeObject()로 출력한 바이트 배열을 읽어
    //    인스턴스를 생성하는 기능이 있다.
    // => 단, java.io.Serializable 인터페이스를 구현한 클래스여야 한다.
    //    출력할 때 클래스의 버전과 읽을 때 클래스의 버전이 같아야 한다.
    //
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(
            new FileInputStream("score.data")))) {
      int length = in.readInt();

      for (int i = 0; i < length; i++) {
        Score score = (Score) in.readObject();
        // 저장한 인스턴스 필드 값을 그대로 읽을 것이기 때문에
        // sum과 aver 필드 값도 그대로 읽을 것이다.
        // 따라서 compute()는 따로 호출할 필요가 없다.
        // score.compute();
        students.add(score);
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
