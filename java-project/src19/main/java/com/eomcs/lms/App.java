package com.eomcs.lms;

import java.util.Scanner;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;

public class App {

  static Scanner keyboard = new Scanner(System.in);
  
  public static void main(String[] args) {
    LessonHandler lessonHandler = new LessonHandler(keyboard);
    MemberHandler memberHandler = new MemberHandler(keyboard);
    BoardHandler boardHandler = new BoardHandler(keyboard);
   
    while (true) {
      String command = prompt();

      if (command.equals("/lesson/add")) {
        lessonHandler.addLesson();
      } else if (command.equals("/lesson/list")) {
        lessonHandler.listLesson();
      } else if (command.equals("/lesson/detail")) {
        lessonHandler.detailLesson();
      } else if (command.equals("/lesson/delete")) {
        lessonHandler.deleteLesson();
      } else if (command.equals("/lesson/update")) {
        lessonHandler.updateLesson();
      } else if (command.equals("/member/add")) {
        memberHandler.addMember();
      } else if (command.equals("/member/list")) {
        memberHandler.listMember();
      } else if (command.equals("/member/detail")) {
        memberHandler.detailMember();
      } else if (command.equals("/member/delete")) {
        memberHandler.deleteMember();
      } else if (command.equals("/member/update")) {
        memberHandler.updateMember();
      } else if (command.equals("/board/add")) {
        boardHandler.addBoard();
      } else if (command.equals("/board/list")) {
        boardHandler.listBoard();
      } else if (command.equals("/board/detail")) {
        boardHandler.detailBoard();
      } else if (command.equals("/board/update")) {
        boardHandler.updateBoard();
      } else if (command.equals("/board/delete")) {
        boardHandler.deleteBoard();
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
