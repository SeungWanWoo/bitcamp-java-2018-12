package bitcamp.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import bitcamp.lms.domain.Member;

public class MemberHandler {
  public static Scanner scn;
  public static int memberIdx = 0;
  public static Member[] mm = new Member[10];
  
  public static void listMember() {
    for (int i = 0; i < memberIdx; i++) {
      System.out.printf("%02d, %-5s, %-20s, %s, %s\n", mm[i].num, 
          mm[i].name, mm[i].email, mm[i].tel, mm[i].date);
    }
    System.out.println();
  }

  public static void addMember() {
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

    mm[memberIdx++] = mb;
    System.out.println("저장하였습니다.");
  }
}
