package com.eomcs.lms;

import java.util.Scanner;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;

public class App {

  static Scanner keyboard = new Scanner(System.in);
  
  public static void main(String[] args) {
    LessonHandler lessonHandler = new LessonHandler();
    MemberHandler memberHandler = new MemberHandler();
    BoardHandler boardHandler = new BoardHandler();
    BoardHandler boardHandler2 = new BoardHandler();
    
    lessonHandler.keyboard = keyboard;
    memberHandler.keyboard = keyboard;
    boardHandler.keyboard = keyboard;
    boardHandler2.keyboard = keyboard;
    while (true) {
      String command = prompt();

      if (command.equals("/lesson/add")) {
        lessonHandler.addLesson();
      } else if (command.equals("/lesson/list")) {
        lessonHandler.listLesson();
      } else if (command.equals("/member/add")) {
        memberHandler.addMember();
      } else if (command.equals("/member/list")) {
        memberHandler.listMember();
      } else if (command.equals("/board/add")) {
        boardHandler.addBoard();
      } else if (command.equals("/board/list")) {
        boardHandler.listBoard();
      } else if (command.equals("/board2/add")) {
        boardHandler2.addBoard();
      } else if (command.equals("/board2/list")) {
        boardHandler2.listBoard();
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
