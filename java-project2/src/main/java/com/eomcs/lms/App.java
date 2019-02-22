// 15단계: 여러 클라이언트 요청을 처리할 때의 문제점과 해결책(멀티 스레드 적용)
package com.eomcs.lms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.handler.Command;

public class App {

  Scanner keyboard = new Scanner(System.in);
  Stack<String> commandHistory = new Stack<>();
  Queue<String> commandHistory2 = new LinkedList<>();

  ArrayList<ApplicationContextListener> listeners = new ArrayList<>();
  
  public void service() throws Exception {
    HashMap<String, Object> context = new HashMap<>();
    context.put("keyboard", keyboard);
    
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
    
    while (true) {
      String command = prompt();

      commandHistory.push(command);
      commandHistory2.offer(command);

      if (command.equals("quit")) {
        System.out.println("종료 합니다.");
        break;
        
      } else if (command.equals("history")) {
        printCommandHistory();
        continue;
        
      } else if (command.equals("history2")) {
        printCommandHistory2();
        continue;
      }
      
      Command commandHandler = (Command) context.get(command);
      if (commandHandler == null) {
        System.out.println("실행할 수 없는 명령입니다.");
        continue;
      } 

      // stateful을 stateless로 전환할 때 주의할 점!
      // => 가능한 서버에 요청하는 시점에 서버와 연결하라!
      // => 이 클래스에서 서버와 연결하지 않고
      //    데이터를 요청하는 일을 하는 객체(*Agent)에 서버 연결을 맡긴다.

      try {
        commandHandler.execute();
        System.out.println();
      } catch (Exception e) {
        System.out.println("명령어 실행 중 오류 발생 : " + e.toString());
      }
    } // while
    keyboard.close();
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }
  
  private void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  @SuppressWarnings("unchecked")
  private void printCommandHistory() {
    Stack<String> temp = (Stack<String>) commandHistory.clone();

    while (temp.size() > 0) {
      System.out.println(temp.pop());
    }
  }

  @SuppressWarnings("unchecked")
  private void printCommandHistory2() {
    Queue<String> temp = 
        (Queue<String>) ((LinkedList<String>) commandHistory2).clone();

    while (temp.size() > 0) {
      System.out.println(temp.poll());
    }
  }

  private String prompt() {
    System.out.print("명령> ");
    return keyboard.nextLine().toLowerCase();
  }

  public static void main(String[] args) throws Exception {
    App app = new App();
    
    app.addApplicationContextListener(new ApplicationInitializer());
    
    // App 을 실행한다.
    app.service();
  }
}
