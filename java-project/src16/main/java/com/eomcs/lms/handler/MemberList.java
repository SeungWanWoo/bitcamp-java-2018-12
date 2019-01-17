package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;

public class MemberList {
  static final int LENGTH = 2;
  Member[] list;
  int size = 0;
  
  public MemberList() {
    list = new Member[LENGTH];
  }
  
  public MemberList(int length) {
    if (length > LENGTH)
      list = new Member[length];
    else
      list = new Member[LENGTH];
  }
  
  public Member[] toArray() {
    Member[] copyMember = new Member[size];
    for (int i = 0; i < size; i++) {
      copyMember[i] = list[i];
    }
    return copyMember;
  }
  
  public void add(Member member) {
    if (size >= list.length) {
      int orderLength = list.length;
      int newLength = orderLength + (orderLength >> 1);
      Member[] members = new Member[newLength];
      for (int i = 0; i < list.length; i++) {
        members[i] = list[i];
      }
      list = members;
    }
    list[size++] = member;
  }
}
