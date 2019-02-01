package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;

public class MemberDetailCommand implements Command {
  
  Scanner keyboard;
  List<Member> list;
  
  public MemberDetailCommand(Scanner keyboard, List<Member> list) {
    this.keyboard = keyboard;
    this.list = list;
  }
  
  public void execute() {
    int no = prompt();
    int index = indexOf(no);
    if (!validate(index)) 
      return;
    
    Member member = list.get(index);
    
    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("암호: %s\n", member.getPassword());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getTel());
    System.out.printf("가입일: %s\n", member.getRegisteredDate());
  }
  
  private boolean validate(int index) {
    if (index == -1)
      return false;
    
    return true;
  }

  private int indexOf(int index) {
    Member[] member = list.toArray(new Member[0]);
    for (int i = 0; i < list.size(); i++) {
      if (member[i].getNo() == index)
        return i;
    }
    return -1;
  }

  private int prompt() {
    System.out.print("번호? ");
    return Integer.parseInt(keyboard.nextLine());
  }


}
