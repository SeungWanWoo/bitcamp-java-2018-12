package com.eomcs.lms;

import java.util.Scanner;
import com.eomcs.lms.handler.BoardHandler;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberHandler;
import com.eomcs.util.Queue;
import com.eomcs.util.Stack;

public class App {

  static Scanner keyboard = new Scanner(System.in);
  static Stack<String> commandHistory = new Stack<>();
  static Queue<String> commandHistory2 = new Queue<>();
  public static void main(String[] args) {
    LessonHandler lessonHandler = new LessonHandler(keyboard);
    MemberHandler memberHandler = new MemberHandler(keyboard);
    BoardHandler boardHandler = new BoardHandler(keyboard);

    while (true) {
      String command = prompt();
      commandHistory.push(command);
      commandHistory2.offer(command);
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
      } else if (command.equals("history")) {
        printCommandHistory(command);
      } else if (command.equals("history2")) {
        printCommandHistory2(command);
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
  private static void printCommandHistory(String value) {
    try {
      Stack<String> history = commandHistory.clone();
      int count = 1;
      while (!history.empty()) {
        System.out.println(history.pop());
        if (++count % 5 == 0){
          System.out.println(":");
          String judge = keyboard.nextLine();
          if (judge.equalsIgnoreCase("q"))
            break;
        }
      }
    } catch (Exception e) {
      System.out.println("오류가 발생하였습니다.");
      e.printStackTrace();
    }

  }
  
  private static void printCommandHistory2(String value) {
    try {
      Queue<String> history = commandHistory2.clone();
      int count = 1;
      while (!history.empty()) {
        System.out.println(history.poll());
        if (++count % 5 == 0){
          System.out.println(":");
          String judge = keyboard.nextLine();
          if (judge.equalsIgnoreCase("q"))
            break;
        }
      }
    } catch (Exception e) {
      System.out.println("오류가 발생하였습니다.");
      e.printStackTrace();
    }

  }
  static String prompt() {
    System.out.print("명령> ");
    String command = keyboard.nextLine().toLowerCase();
    return command;
  }
}
