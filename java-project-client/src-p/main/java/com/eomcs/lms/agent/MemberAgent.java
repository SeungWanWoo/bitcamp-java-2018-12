package com.eomcs.lms.agent;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class MemberAgent {
  public static void add(Member member, ObjectInputStream in,
      ObjectOutputStream out) throws Exception {
    out.writeUTF("/member/add");
    out.flush();

    if (!in.readUTF().equals("OK"))
      throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");

    out.writeObject(member);
    out.flush();

    String status = in.readUTF();
    if (!status.equals("OK")) 
      throw new Exception("회원 정보 저장 실패!");
  }
  
  @SuppressWarnings("unchecked")
  public static List<Member> list(ObjectInputStream in,
      ObjectOutputStream out) throws Exception {
    out.writeUTF("/member/list");
    out.flush();
    
    if (!in.readUTF().equals("OK"))
      throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");
    
    String status = in.readUTF();
    if (!status.equals("OK")) 
      throw new Exception("회원 정보 목록 출력 실패!");

    return (List<Member>) in.readObject();
  }
  
  public static Member get(int no, ObjectInputStream in,
      ObjectOutputStream out) throws Exception {
    out.writeUTF("/member/detail");
    out.flush();
    
    if (!in.readUTF().equals("OK"))
      throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");
    
    out.writeInt(no);
    out.flush();
    
    String status = in.readUTF();
    if (!status.equals("OK"))
      throw new Exception("회원 상세 정보 출력 실패!");
    
    return (Member) in.readObject();
  }
  
  public static void update(Member member, ObjectInputStream in,
      ObjectOutputStream out) throws Exception {
    out.writeUTF("/member/update");
    out.flush();
    
    if (!in.readUTF().equals("OK"))
      throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");
    
    out.writeObject(member);
    out.flush();
    
   String status = in.readUTF();
    if (!status.equals("OK")) 
      throw new Exception("회원 정보 변경 실패!");
  }
  
  public static void delete(int no, ObjectInputStream in,
      ObjectOutputStream out) throws Exception {
    out.writeUTF("/member/delete");
    out.flush();
    if (!in.readUTF().equals("OK"))
      throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");
    
    out.writeInt(no);
    out.flush();
    
    String status = in.readUTF();
    if (!status.equals("OK"))
      throw new Exception("회원 정보 삭제 실패!");
  }
}
