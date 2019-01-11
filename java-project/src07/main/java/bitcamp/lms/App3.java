package bitcamp.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);

    int index = 0;
    Board[] b = new Board[10];

    while (index < 10) {
      Board bd = new Board();
      System.out.print("번호? ");
      bd.num = Integer.parseInt(scn.nextLine());

      System.out.print("내용? ");
      bd.content = scn.nextLine();

      bd.date = new Date(System.currentTimeMillis());

      System.out.println();

      b[index++] = bd;
      
      if (index == 10) {
        System.out.println("저장할 수 있는 영역을 모두 사용하셨습니다.");
        break;
      }
      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      String answ = scn.nextLine();

      System.out.println();
      
      if (!answ.equalsIgnoreCase("y") && !answ.equalsIgnoreCase(""))
        break;
    }
    
    scn.close();
    
    for (int i = 0; i < index; i++) {
      System.out.printf("%02d, %-20s, %s, %d\n", b[i].num, b[i].content, 
          b[i].date, b[i].viewCount);
    }
}
