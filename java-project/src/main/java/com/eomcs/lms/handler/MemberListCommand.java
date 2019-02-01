package com.eomcs.lms.handler;

import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;

public class MemberListCommand implements Command{
  
  Scanner keyboard;
  List<Member> list;
  
  public MemberListCommand(Scanner keyboard, List<Member> list) {
    this.keyboard = keyboard;
    this.list = list;
  }
  
  public void execute() {
    Member[] lists = list.toArray(new Member[0]);
    for (Member temp : lists) {
      System.out.printf("%3d, %-4s, %-20s, %-15s, %s\n", 
          temp.getNo(), temp.getName(), temp.getEmail(), 
          temp.getTel(), temp.getRegisteredDate());
    }
  }
}
