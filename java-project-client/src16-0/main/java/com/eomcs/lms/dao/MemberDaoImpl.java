package com.eomcs.lms.dao;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class MemberDaoImpl implements MemberDao {
  
  @SuppressWarnings("unchecked")
  public List<Member> findAll() {
    try (Socket socket = new Socket(this.serverAddr, this.port);
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(
            socket.getInputStream())) {
      out.writeUTF(rootPath + "/list");
      out.flush();

      if (!in.readUTF().equals("OK"))
        throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");

      String status = in.readUTF();
      if (!status.equals("OK")) 
        throw new Exception("회원 정보 목록 출력 실패!");

      return (List<Member>) in.readObject();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void insert (Member member) {
    try (Socket socket = new Socket(this.serverAddr, this.port);
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(
            socket.getInputStream())) {
      out.writeUTF(rootPath + "/add");
      out.flush();

      if (!in.readUTF().equals("OK"))
        throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");

      out.writeObject(member);
      out.flush();

      String status = in.readUTF();
      if (!status.equals("OK")) 
        throw new Exception("회원 정보 저장 실패!");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Member findByNo(int no) {
    try (Socket socket = new Socket(this.serverAddr, this.port);
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(
            socket.getInputStream())) {
      out.writeUTF(rootPath + "/detail");
      out.flush();

      if (!in.readUTF().equals("OK"))
        throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");

      out.writeInt(no);
      out.flush();

      String status = in.readUTF();
      if (!status.equals("OK"))
        throw new Exception("회원 상세 정보 출력 실패!");

      return (Member) in.readObject();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int update(Member member) {
    try (Socket socket = new Socket(this.serverAddr, this.port);
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(
            socket.getInputStream())) {
      out.writeUTF(rootPath + "/update");
      out.flush();

      if (!in.readUTF().equals("OK"))
        throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");

      out.writeObject(member);
      out.flush();

      String status = in.readUTF();
      if (!status.equals("OK")) 
        throw new Exception("회원 정보 변경 실패!");
      
      return 1;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int delete(int no) {
    try (Socket socket = new Socket(this.serverAddr, this.port);
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(
            socket.getInputStream())) {
      out.writeUTF(rootPath + "/delete");
      out.flush();
      if (!in.readUTF().equals("OK"))
        throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");

      out.writeInt(no);
      out.flush();

      String status = in.readUTF();
      if (!status.equals("OK"))
        throw new Exception("회원 정보 삭제 실패!");
      
      return 1;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
