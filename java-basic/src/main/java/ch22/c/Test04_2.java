// 바이트 데이터를 읽어 primitive data type의 값으로 바꿔주는 ㄷDecorator;
package ch22.c;

import java.io.FileInputStream;
import java.io.IOException;

public class Test04_2 {

  public static void main(String[] args) {
    try (FileInputStream in = new FileInputStream("data.bin");
        DataInputStream in2 = new DataInputStream(in)){
      int no = in2.readInt();
      String name = in2.readUTF();
      int age = in2.readInt();
      
      System.out.printf("%d, %s, %d\n", no, name, age);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("읽기 완료!");
  }
}