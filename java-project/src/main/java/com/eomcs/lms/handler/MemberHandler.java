package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.ArrayList;

public class MemberHandler {

  Scanner keyboard;
  ArrayList<Member> list;

  public MemberHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    this.list = new ArrayList<>();
  }

  public void listMember() {
    Member[] members = list.toArray(new Member[0]);
    for (Member member : members) {
      System.out.printf("%3d, %-4s, %-20s, %-15s, %s\n", 
          member.getNo(), member.getName(), member.getEmail(), 
          member.getTel(), member.getRegisteredDate());
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

    list.add(member);

    System.out.println("저장하였습니다.");
  }

  public void detailMember() {
    int no = promptNo();
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

  public void deleteMember() {
    int no = promptNo();
    int index = indexOf(no);
    if (!validate(index))
      return;

    list.remove(index);
    System.out.println("회원을 삭제했습니다.");
  }

  private int promptNo() {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());
    return no;
  }

  public void updateMember() {
    int no = promptNo();

    int index = indexOf(no);
    if (!validate(index))
      return;

    Member member = list.get(index);
    Member temp = new Member();
    
    temp.setNo(member.getNo());

    System.out.printf("이름(%s)? ", member.getName());
    String input = keyboard.nextLine();
    temp.setName((input.length() > 0 ? input : member.getName()));

    System.out.printf("이메일(%s)? ", member.getEmail());
    input = keyboard.nextLine();
    temp.setEmail((input.length() > 0 ? input : member.getEmail()));

    System.out.printf("암호(%s)? ", member.getPassword());
    input = keyboard.nextLine();
    temp.setPassword((input.length() > 0 ? input : member.getPassword()));

    System.out.printf("사진(%s)? ", member.getPhoto());
    input = keyboard.nextLine();
    temp.setPhoto((input.length() > 0 ? input : member.getPhoto()));

    System.out.printf("전화(%s)? ", member.getTel());
    input = keyboard.nextLine();
    temp.setTel((input.length() > 0 ? input : member.getTel()));

    temp.setRegisteredDate(new Date(System.currentTimeMillis()));

    list.set(index, temp);
    System.out.println("회원을 변경했습니다.");
  }

  private int indexOf(int index) {
    for (int i = 0; i < list.size(); i++) {
      Member item = list.get(i);
      if(item.getNo() == index) {
        index = i;
        return i;
      }
    }
    return -1;
  }

  private boolean validate(int index) {
    if (index == -1) {
      System.out.println("해당 회원을 찾을 수 없습니다.");
      return false;
    }
    return true;
  }
}
