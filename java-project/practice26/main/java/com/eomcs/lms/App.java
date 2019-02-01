package com.eomcs.lms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.LessonHandler;
import com.eomcs.lms.handler.MemberAddCommand;
import com.eomcs.lms.handler.MemberDeleteCommand;
import com.eomcs.lms.handler.MemberDetailCommand;
import com.eomcs.lms.handler.MemberListCommand;
import com.eomcs.lms.handler.MemberUpdateCommand;

public class App {

  static Scanner keyboard = new Scanner(System.in);
  static Stack<String> commandHistory = new Stack<>();
  static ArrayDeque<String> commandHistory2 = new ArrayDeque<>();
  public static void main(String[] args) {
    
    HashMap<String, Command> commandMap = new HashMap<>();
    
    commandMap.put("/board/add", new BoardAddCommand(keyboard, new LinkedList<>()));
    commandMap.put("/board/list", new BoardListCommand(keyboard, new LinkedList<>()));
    commandMap.put("/board/detail", new BoardDetailCommand(keyboard, new LinkedList<>()));
    commandMap.put("/board/update", new BoardUpdateCommand(keyboard, new LinkedList<>()));
    commandMap.put("/board/delete", new BoardDeleteCommand(keyboard, new LinkedList<>()));
    
    commandMap.put("/member/add", new MemberAddCommand(keyboard, new ArrayList<>()));
    commandMap.put("/member/list", new MemberListCommand(keyboard, new ArrayList<>()));
    commandMap.put("/member/detail", new MemberDetailCommand(keyboard, new ArrayList<>()));
    commandMap.put("/member/update", new MemberUpdateCommand(keyboard, new ArrayList<>()));
    commandMap.put("/member/delete", new MemberDeleteCommand(keyboard, new ArrayList<>()));
    
    LessonHandler lessonHandler = new LessonHandler(keyboard, new ArrayList<>());
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

      } else if (command.equals("history")) {
        printCommandHistory(commandHistory.iterator());

      } else if (command.equals("history2")) {
        printCommandHistory(commandHistory2.iterator());

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

  private static String prompt() {
    System.out.print("명령> ");
    return keyboard.nextLine().toLowerCase();
  }

  private static void printCommandHistory(Iterator<String> iterator) {
    try {
      int count = 1;
      while(iterator.hasNext()) {
        System.out.println(iterator.next());
        if (++count % 5 == 0) {
          System.out.print(":");
          String input = keyboard.nextLine();
          if (input.equalsIgnoreCase("q")) {
            break;
          }
        }
      }
    } catch (Exception e) {
        System.out.println("명령어를 읽어오는데 실패하였습니다.");
    }
  }
}
