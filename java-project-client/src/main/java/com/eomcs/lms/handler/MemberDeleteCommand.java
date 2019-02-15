package com.eomcs.lms.handler;
import java.util.Scanner;
import com.eomcs.lms.agent.MemberAgent;

public class MemberDeleteCommand implements Command {
  MemberAgent memberAgent;
  Scanner keyboard;
  
  public MemberDeleteCommand(Scanner keyboard,MemberAgent memberAgent) {
    this.keyboard = keyboard;
    this.memberAgent = memberAgent;
  }
  
  @Override
  public void execute() {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());

    try {
      memberAgent.delete(no);
      System.out.println("회원을 삭제했습니다.");
    } catch (Exception e) {
      System.out.printf("회원 정보 삭제 오류! : %s\n", e.getMessage());
    }
  }
}
