package bitcamp.lms;

import java.sql.Date;
import java.util.Scanner;

public class BoardHandler {
  static Scanner scn;
  static int boardIdx = 0;
  static Board[] b = new Board[10];
  
  static void listBoard() {
    for (int i = 0; i < boardIdx; i++) {
      System.out.printf("%02d, %-20s, %s, %d\n", b[i].num, b[i].content, 
          b[i].date, b[i].viewCount);
    }
    System.out.println();
  }

  static void addBoard() {
    Board bd = new Board();
    System.out.print("번호? ");
    bd.num = Integer.parseInt(scn.nextLine());

    System.out.print("내용? ");
    bd.content = scn.nextLine();

    bd.date = new Date(System.currentTimeMillis());

    System.out.println();

    b[boardIdx++] = bd;
  }
}
