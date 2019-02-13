package com.eomcs.lms.handler;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;

public class MemberUpdateCommand implements Command {
  
  Scanner keyboard;
  
  public MemberUpdateCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }
  
  @Override
  public void execute(ObjectInputStream in, ObjectOutputStream out) {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());

    try {
      out.writeUTF("/member/update");
      out.flush();
      
      if (!in.readUTF().equals("OK"))
        throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");
      
      Member temp = new Member();
      
      temp.setNo(no);
      System.out.print("이름? ");
      String input = keyboard.nextLine();
      temp.setName(input);
      
      System.out.print("이메일? ");
      input = keyboard.nextLine();
      temp.setEmail(input);
      
      System.out.print("암호(********)? ");
      input = keyboard.nextLine();
      temp.setPassword(input);
      
      System.out.print("사진? ");
      input = keyboard.nextLine();
      temp.setPhoto(input);
      
      System.out.print("전화? ");
      input = keyboard.nextLine();
      temp.setTel(input);
      
      out.writeObject(temp);
      out.flush();
      
      String status = in.readUTF();
      if (!status.equals("OK")) 
        throw new Exception("회원 정보 변경 실패!");
      
      System.out.println("회원을 변경했습니다.");
      
    } catch (Exception e) {
      System.out.printf("회원 정보 변경 오류! : %s\n", e.getMessage());
    }
  }
}
