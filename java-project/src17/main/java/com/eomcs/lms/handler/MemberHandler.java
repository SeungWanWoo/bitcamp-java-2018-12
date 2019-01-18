package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;

public class MemberHandler {

  Scanner keyboard;
  ArrayList ArrayList;
  
  public MemberHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    this.ArrayList = new ArrayList(5);
  }
  
  public void listMember() {
    Object[] members = ArrayList.toArray();
    for (Object member : members) {
      System.out.printf("%3d, %-4s, %-20s, %-15s, %s\n", 
          ((Member) member).getNo(), ((Member) member).getName(), ((Member) member).getEmail(), 
          ((Member) member).getTel(), ((Member) member).getRegisteredDate());
    }
  }

  public void addMember() {
    Member member = new Member();
    
    System.out.print("번호? ");
    member.setNo(Integer.parseInt(keyboard.nextLine()));
    
    System.out.print("이름? ");
    member.setName(keyboard.nextLine());
    
    System.out.print("이메일? ");
    member.setEmail(keyboard.nextLine());
    
    System.out.print("암호? ");
    member.setPassword(keyboard.nextLine());
  
    System.out.print("사진? ");
    member.setPhoto(keyboard.nextLine());
  
    System.out.print("전화? ");
    member.setTel(keyboard.nextLine());
  
    member.setRegisteredDate(new Date(System.currentTimeMillis())); 
    
    ArrayList.add(member);
    
    System.out.println("저장하였습니다.");
  }
}
