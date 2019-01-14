package com.eomcs.lms;

import java.util.Scanner;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;

public class App {

  static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {
    LessonHandler lh = new LessonHandler();
    MemberHandler mh = new MemberHandler();
    BoardHandler bh = new BoardHandler();
    BoardHandler bh2 = new BoardHandler();

    mh.keyboard = keyboard;
    lh.keyboard = keyboard;
    bh.keyboard = keyboard;
    bh2.keyboard = keyboard;
    while (true) {
      String command = prompt();

      if (command.equals("/lesson/add")) {
        lh.addLesson();
      } else if (command.equals("/lesson/list")) {
        lh.listLesson();
      } else if (command.equals("/member/add")) {
        mh.addMember();
      } else if (command.equals("/member/list")) {
        mh.listMember();
      } else if (command.equals("/board/add")) {
        bh.addBoard();
      } else if (command.equals("/board/list")) {
        bh.listBoard();
      } else if (command.equals("/board2/add")) {
        bh2.addBoard();
      } else if (command.equals("/board2/list")) {
        bh2.listBoard();
      } else if (command.equals("quit")) {
        System.out.println("안녕!");
        break;
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
      System.out.println(); // 결과 출력 후 빈 줄 출력
    }
    keyboard.close();
  }
  static String prompt() {
    System.out.print("명령> ");
    String command = keyboard.nextLine().toLowerCase();
    return command;
  }
}
