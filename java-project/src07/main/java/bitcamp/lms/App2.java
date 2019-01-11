package bitcamp.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);

    int index = 0;
    Member[] mm = new Member[10];

    while (index < 10) {
      Member mb = new Member();
      System.out.print("번호? ");
      mb.num = Integer.parseInt(scn.nextLine());

      System.out.print("이름? ");
      mb.name = scn.nextLine();

      System.out.print("이메일? ");
      mb.email = scn.nextLine();

      System.out.print("암호? ");
      mb.passwd = Integer.parseInt(scn.nextLine());

      System.out.print("사진? ");
      mb.pic = scn.nextLine();

      System.out.print("전화? ");
      mb.tel = scn.nextLine();

      mb.date = new Date(System.currentTimeMillis());
      System.out.println();

      mm[index++] = mb;

      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      String answ = scn.nextLine();

      System.out.println();

      if (index== 10) {
        System.out.println("저장할 수 있는 영역을 모두 사용하셨습니다.");
        break;
      } 
      if (!answ.equalsIgnoreCase("y") && !answ.equalsIgnoreCase("")) 
        break;
    }

    scn.close();

    for (int i = 0; i < index; i++) {
      System.out.printf("%02d, %-5s, %-20s, %s, %s\n", 
          mm[i].num, mm[i].name, mm[i].email, mm[i].tel, mm[i].date);
    }
  }
}