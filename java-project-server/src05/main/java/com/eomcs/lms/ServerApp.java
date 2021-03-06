// 2단계 : 클라이언트의 연결을 승인한다.
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.eomcs.lms.domain.Member;

public class ServerApp {
  
  static ObjectInputStream in;
  static ObjectOutputStream out;
  static ArrayList<Member> members = new ArrayList<>();
  
  public static void main(String[] args) {

    try (ServerSocket serverSocket = new ServerSocket(9545)) {

      System.out.println("서버 시작!");
      while (true) {
        try(Socket socket = serverSocket.accept();
            ObjectOutputStream out = new ObjectOutputStream(
                socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(
                socket.getInputStream())) {
          System.out.println("클라이언트와 연결되었음.");
          members.clear();
          ServerApp.in = in;
          ServerApp.out = out;
          
          // 클라이언트에서 serialize해서 보내온 Member객체의 내용을 출력하라.
          loop: while (true) {
            String request = in.readUTF();
            System.out.println(request);
            switch (request) {
              case "quit":
                quit();
                break loop;
              case "add":
                add();
                break;
              case "list":
                list();
                break;
              default:
                out.writeUTF("이 명령을 처리할 수 없음!");
            } // switch
            out.flush();
          } // while
        } catch (Exception e) {
          e.printStackTrace();
        }
        System.out.println("클라이언트와 연결을 끊었음.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  static void quit() throws Exception {
    out.writeUTF("종료함!");
    out.flush();
  }
  
  static void add() throws Exception {
    members.add((Member) in.readObject());
    out.writeUTF("OK");
  }
  
  static void list() throws Exception {
    out.writeObject(members);
  }
}
