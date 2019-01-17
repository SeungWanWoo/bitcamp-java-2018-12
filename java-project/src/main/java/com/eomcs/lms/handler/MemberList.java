package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;

public class MemberList {
  static final int LENGTH = 10;
  Member[] members;
  int memberIdx = 0;
  
  public MemberList() {
    members = new Member[LENGTH];
  }
  
  public MemberList(int length) {
    if (length > LENGTH)
      members = new Member[length];
    else
      members = new Member[LENGTH];
  }
  
  public Member[] toArray() {
    Member[] copyMember = new Member[memberIdx];
    for (int i = 0; i < memberIdx; i++) {
      copyMember[i] = members[i];
    }
    return copyMember;
  }
  
  public void add(Member member) {
    if (memberIdx >= members.length) {
      int orderLength = members.length;
      int newLength = orderLength + (orderLength >> 1);
      for (int i = 0; i < newLength; i++) {
        members[i] = member;
      }
    }
    members[memberIdx++] = member;
  }
}
