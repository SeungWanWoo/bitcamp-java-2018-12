package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberDaoImpl implements MemberDao {

  Connection con;

  public MemberDaoImpl(Connection con) {
    this.con = con;
  }

  public List<Member> findAll() {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_id, name, email, pwd, cdt, tel, photo"
            + " from lms_member order by member_id desc")){
      try (ResultSet rs = stmt.executeQuery()) {

        ArrayList<Member> list = new ArrayList<>();
        while (rs.next()) {
          Member member = new Member();
          member.setNo(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setPassword(rs.getString("pwd"));
          member.setRegisteredDate(rs.getDate("cdt"));
          member.setTel(rs.getString("tel"));
          member.setPhoto(rs.getString("photo"));

          list.add(member);
        }
        return list;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void insert (Member member) {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into lms_member(name, email, pwd, cdt, tel, photo) "
            + "values(?,?,?,?,?,?)")){
      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setDate(4, member.getRegisteredDate());
      stmt.setString(5, member.getTel());
      stmt.setString(6, member.getPhoto());
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Member findByNo(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_id, name, email, pwd, cdt, tel, photo"
            + " from lms_member where member_id = ?")){
      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Member member = new Member();
          member.setNo(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setPassword(rs.getString("pwd"));
          member.setRegisteredDate(rs.getDate("cdt"));
          member.setTel(rs.getString("tel"));
          member.setPhoto(rs.getString("photo"));

          return member;
        } else {
          return null;
        }
      } 
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int update(Member member) {
    try (PreparedStatement stmt = con.prepareStatement(
        "update lms_member set name = ?, email = ?, pwd = ?, cdt = ?, "
            + "tel = ?, photo = ? where member_id = ?")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setDate(4, member.getRegisteredDate());
      stmt.setString(5, member.getTel());
      stmt.setString(6, member.getPhoto());
      stmt.setInt(7, member.getNo());

      return stmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int delete(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from lms_member where member_id = ?")) {
      stmt.setInt(1, no);

      return stmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
