package bitcamp.lms;

import java.util.Scanner;

public class App {
  static Scanner scn = new Scanner(System.in);
  static final int length = 10;
  
  public static void main(String[] args) {
    LessonHandler.scn = scn;
    MemberHandler.scn = scn;
    BoardHandler.scn = scn;
    
    while(true) {
      String input = prompt();
      
      if (input.equals("/lesson/add")) {
        if (LessonHandler.lessonIdx == length) {
          System.out.println("저장할 수 있는 영역을 모두 사용하셨습니다.");
          System.out.println("리스트를 출력합니다.");
          LessonHandler.listLesson();
          continue;
        }
        LessonHandler.addLesson();
      } else if (input.equals("/lesson/list")) {
        LessonHandler.listLesson();
      } else if (input.equals("/member/add")) {
        if (MemberHandler.memberIdx == length) {
          System.out.println("저장할 수 있는 영역을 모두 사용하셨습니다.");
          System.out.println("리스트를 출력합니다.");
          MemberHandler.listMember();
          continue;
        }
        MemberHandler.addMember();
      } else if (input.equals("/member/list")) {
        MemberHandler.listMember();
      } else if (input.equals("/board/add")) {
        if (BoardHandler.boardIdx == length) {
          System.out.println("저장할 수 있는 영역을 모두 사용하셨습니다.");
          System.out.println("리스트를 출력합니다.");
          BoardHandler.listBoard();
          continue;
          }
        BoardHandler.addBoard();
      } else if (input.equals("/board/list")) {
        BoardHandler.listBoard();
      } else if (input.equals("quit")) {
        System.out.println("안녕!");
        break;
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
        System.out.println();
      }
    }
    scn.close();
  }
  
  static String prompt() {
    System.out.print("명령> ");
    String input = scn.nextLine();
    return input;
  }
}