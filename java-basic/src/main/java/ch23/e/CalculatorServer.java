// Stateless 서버 만들기
package ch23.e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;

class IdList {
  private int id;
  private int value;
  Socket socket;
  BufferedReader in;
  PrintStream out;
  
  IdList () {}
  IdList (int id, int value) {
    this.id = id;
    this.value = value;
  }
  IdList (Socket socket) throws Exception {
    this.socket = socket;
    this.out = new PrintStream(socket.getOutputStream());
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getValue() {
    return value;
  }
  public void setValue(int value) {
    this.value = value;
  }
  
  private int calculator(int result) throws Exception {
    int b = 0;
    String op = null;
    String[] input = in.readLine().split(" ");
    try { 
      op = input[0];
      b = Integer.parseInt(input[1]);
    } catch (Exception e) {
      out.println("식의 형식이 바르지 않습니다.");
      out.flush();
    }

    switch (op) {
      case "+":
        result += b;
        out.printf("결과는 %d 입니다.\n", result);
        out.flush();
        return result;
      case "-": 
        result -= b;
        out.printf("결과는 %d 입니다.\n", result);
        out.flush();
        return result;
      case "*": 
        result *= b;
        out.printf("결과는 %d 입니다.\n", result);
        out.flush();
        return result;
      case "/": 
        result /= b;
        out.printf("결과는 %d 입니다.\n", result);
        out.flush();
        return result;
      case "%": 
        result %= b;
        out.printf("결과는 %d 입니다.\n", result);
        out.flush();
        return result;
      default:
        out.printf("%s 연산자를 지원하지 않습니다.\n", op);
        out.flush();
        return 0;
    }
  }
  
  public void execute(HashMap<Integer, IdList> map) throws Exception {
    try (Socket socket = this.socket;
        PrintStream out = this.out;
        BufferedReader in = this.in) {
      
      processNum(map);
      
    }
  }
  private void processNum(HashMap<Integer, IdList> map) throws Exception {
    int recvSessionNum = Integer.parseInt(in.readLine());
    System.out.printf("%d번 클라이언트와 연결됨! 요청처리 중...\n", recvSessionNum);
    
    IdList resultList = new IdList();
    
    if (recvSessionNum == 0) {
      recvSessionNum++; 
    } else {
      resultList.setValue(map.get(recvSessionNum));
    }
    calculator(result);
    
    /*if (recvSessionNum != sessionNum) {
      this.value = calculator(getValue());
      session.put(recvSessionNum, new IdList(recvSessionNum, value));
      
      System.out.println("클라이언트 번호에 맞게 데이터를 설정함...");
      sessionNum++;
      System.out.println("다음 클라이언트 번호 생성...");
    } else {
      this.setValue(calculator(getValue()));
      session.put(recvSessionNum, this);
      System.out.println("클라이언트 번호에 맞게 데이터를 설정함...");
    }*/
  }
}

public class CalculatorServer {
  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 실행 중...");
      HashMap<Integer, IdList> session = new HashMap<>();
      while (true) {
        try {
          new IdList(serverSocket.accept()).execute();
          
        } catch (Exception e) {
          e.printStackTrace();
        }
        System.out.println("클라이언트와 연결 끊음!");
      }
    } catch (Exception e) {
      System.out.println("클라이언트와 통신 중 오류 발생!");
    }
  }
  
}