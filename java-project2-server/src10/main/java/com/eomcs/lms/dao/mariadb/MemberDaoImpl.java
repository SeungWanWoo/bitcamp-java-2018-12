package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.ConnectionFactory;

public class MemberDaoImpl implements MemberDao {

  public List<Member> findAll() {
    try (Connection con = ConnectionFactory.create();
        PreparedStatement pstmt = con.prepareStatement(
        "select member_id, name, email, pwd, cdt, tel, photo"
            + " from lms_member order by member_id desc")) {

      try (ResultSet rs = pstmt.executeQuery()) {
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
  
  @Override
  public List<Member> findByKeyword(String keyword) {
    try (Connection con = ConnectionFactory.create();
        PreparedStatement pstmt = con.prepareStatement(
        "select member_id, name, email, pwd, cdt, tel, photo from lms_member "
        + "where name like concat('%', ?, '%') "
        + "or email like concat('%', ?, '%') "
        + "or tel like concat('%', ?, '%') "
        + "order by member_id desc")) {
      pstmt.setString(1, keyword);
      pstmt.setString(2, keyword);
      pstmt.setString(3, keyword);
      
      
      try (ResultSet rs = pstmt.executeQuery()) {
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
    try (Connection con = ConnectionFactory.create();
        PreparedStatement pstmt = con.prepareStatement(
        "insert into lms_member (name, email, pwd, cdt, tel, photo) "
            + "values (?,?,password(?),now(),?,?)")) {
      pstmt.setString(1, member.getName());
      pstmt.setString(2, member.getEmail());
      pstmt.setString(3, member.getPassword());
      pstmt.setString(4, member.getTel());
      pstmt.setString(5, member.getPhoto());
      pstmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Member findByNo(int no) {
    try (Connection con = ConnectionFactory.create();
        PreparedStatement pstmt = con.prepareStatement(
        "select member_id, name, email, pwd, cdt, tel, photo"
            + " from lms_member where member_id = ?")) {
      pstmt.setInt(1, no);

      try (ResultSet rs = pstmt.executeQuery()) {
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
        }
        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int update(Member member) {
    try (Connection con = ConnectionFactory.create();
        PreparedStatement pstmt = con.prepareStatement(
        "update lms_member set name = ?, email = ?, pwd = password(?), cdt = now(), "
            + "tel = ?, photo = ? where member_id = ?")) {
      pstmt.setString(1, member.getName());
      pstmt.setString(2, member.getEmail());
      pstmt.setString(3, member.getPassword());
      pstmt.setString(4, member.getTel());
      pstmt.setString(5, member.getPhoto());
      pstmt.setInt(6, member.getNo());

      pstmt.executeUpdate();

      return 1;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int delete(int no) {
    try (Connection con = ConnectionFactory.create();
        PreparedStatement pstmt = con.prepareStatement(
        "delete from lms_member where member_id = ?")) {
      pstmt.setInt(1, no);
      pstmt.executeUpdate();

      return 1;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
