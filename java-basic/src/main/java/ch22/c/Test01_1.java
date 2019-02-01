// 바이너리 데이터 입출력 - 버퍼 사용 전
package ch22.c;

import java.io.FileInputStream;

public class Test01_1 {

  public static void main(String[] args) {
    try {
      FileInputStream in = new FileInputStream("jls11.pdf");
      
      long start = System.currentTimeMillis();
      System.out.println("데이터 읽는 중...");
      int b;
      while ((b = in.read()) != -1) {
      }
      System.out.println();
      
      long end = System.currentTimeMillis();
      System.out.println(end - start);
      in.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("출력 완료!");
  }
}
