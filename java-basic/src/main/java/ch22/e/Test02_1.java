// 인스턴스 출력하기 - 배열 활용
package ch22.e;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

public class Test02_1 {

  public static void main(String[] args) {
    
    // 다음 세 학생의 성적 정보를 score.data 파일에 바이너리 형식으로 저장하라!
    // => java.io.BufferedOutputStream 클래스를 사용하라.
    // => java.io.DataOutputStream 클래스를 사용하라.
    
    Score[] students = {
        new Score("홍길동", 100, 100, 100),
        new Score("임꺽정", 90, 90, 90),
        new Score("유관순", 80, 80, 80)
    };
    /*try (FileOutputStream out = new FileOutputStream("score.data");
        BufferedOutputStream out1 = new BufferedOutputStream(out);
        DataOutputStream out2 = new DataOutputStream(out1)) */
    // 실무에서 볼수 있는 모습
    try (DataOutputStream out = new DataOutputStream(
            new BufferedOutputStream(
                new FileOutputStream("score.data")))) {
      /*String sc1 = s1.toString();
      String sc2 = s2.toString();
      String sc3 = s3.toString();
      out.writeUTF(sc1);
      out.writeUTF(sc2);
      out.writeUTF(sc3);*/
      // 먼저 배열의 개수를 알려서 읽는 쪽에서 알려준 만큼 반복문을 돌리기 위해서
      out.writeInt(students.length); 
      for (Score s : students) {
        out.writeUTF(s.getName());
        out.writeInt(s.getKor());
        out.writeInt(s.getEng());
        out.writeInt(s.getMath());
      }
      
      out.flush();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("출력 완료!");
  }

}
