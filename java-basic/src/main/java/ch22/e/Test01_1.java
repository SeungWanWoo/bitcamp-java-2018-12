// 인스턴스 출력하기
package ch22.e;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

public class Test01_1 {

  public static void main(String[] args) {
    
    // 다음 세 학생의 성적 정보를 score.data 파일에 바이너리 형식으로 저장하라!
    // => java.io.BufferedOutputStream 클래스를 사용하라.
    // => java.io.DataOutputStream 클래스를 사용하라.
    Score s1 = new Score("홍길동", 100, 100, 100);
    Score s2 = new Score("임꺽정", 90, 90, 90);
    Score s3 = new Score("유관순", 80, 80, 80);
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
      
      out.writeUTF(s1.getName());
      out.writeInt(s1.getKor());
      out.writeInt(s1.getEng());
      out.writeInt(s1.getMath());
      
      out.writeUTF(s2.getName());
      out.writeInt(s2.getKor());
      out.writeInt(s2.getEng());
      out.writeInt(s2.getMath());
      
      out.writeUTF(s3.getName());
      out.writeInt(s3.getKor());
      out.writeInt(s3.getEng());
      out.writeInt(s3.getMath());
      
      out.flush();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("출력 완료!");
  }

}
