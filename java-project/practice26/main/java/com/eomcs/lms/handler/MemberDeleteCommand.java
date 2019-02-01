package com.eomcs.lms.handler;

import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;

public class MemberDeleteCommand implements Command {
  
  Scanner keyboard;
  List<Member> list;
  
  public MemberDeleteCommand(Scanner keyboard, List<Member> list) {
    this.keyboard = keyboard;
    this.list = list;
  }
  
  public void execute() {
    int no = prompt();
    int index = indexOf(no);
    if (!validate(index)) 
      return;
    
    list.remove(index);
    System.out.println("회원을 삭제했습니다.");
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
