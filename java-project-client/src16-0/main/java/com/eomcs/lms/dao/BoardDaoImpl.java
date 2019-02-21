package com.eomcs.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardDaoImpl implements BoardDao {

  @Override
  public List<Board> findAll() {
    ArrayList<Board> list = new ArrayList<>();
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/testdb?user=test&password=1111")) {
      try (PreparedStatement pstmt = con.prepareStatement("select * from board");
          ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
          Board board = new Board();
          board.setNo(rs.getInt("no"));
          board.setContents(rs.getString("contents"));
          board.setCreatedDate(rs.getDate("created_date"));
          board.setViewCount(rs.getInt("view_count"));
          list.add(board);
        } // while
        return list;
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void insert(Board board) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/testdb?user=test&password=1111")) {
      try (PreparedStatement pstmt = con.prepareStatement(
          "insert into board(no,contents) values(?,?)")) {

        pstmt.setInt(1, board.getNo());
        pstmt.setString(2, board.getContents());
        pstmt.executeUpdate();
        System.out.println("등록되었습니다.");

      } catch (Exception e) {
        System.out.println("등록에 실패하였습니다.");
        throw new RuntimeException(e);
      } 
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  } 

  @Override
  public Board findByNo(int no) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/testdb?user=test&password=1111")) {
      try (PreparedStatement pstmt = con.prepareStatement(
          "select * from board where no = " + no);
          ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          Board board = new Board();
          board.setNo(rs.getInt("no"));
          board.setContents(rs.getString("contents"));
          board.setCreatedDate(rs.getDate("created_date"));
          board.setViewCount(rs.getInt("view_count"));
          return board;
        } else {
          System.out.println("해당 번호의 게시물이 존재하지 않습니다.");
          return null;
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Board board) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/testdb?user=test&password=1111")) {
      try (PreparedStatement pstmt = con.prepareStatement(
          "update board set contents=?, "
              + "created_date=?, view_count=? where no=?");
          ResultSet rs = pstmt.executeQuery(
              "select * from board where no = " + board.getNo())) {
        if (rs.next()) {
          pstmt.setString(1, board.getContents());
          pstmt.setDate(2, board.getCreatedDate());
          pstmt.setInt(3, board.getViewCount());
          pstmt.setInt(4,  board.getNo());
          pstmt.executeUpdate();
          System.out.println("변경하였습니다.");
          return 1;
        } else {
          System.out.println("변경에 실패하였습니다.");
          return 0;
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      } 
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/testdb?user=test&password=1111")) {
      try (PreparedStatement pstmt = con.prepareStatement(
          "delete from board where no = ?")) {
        pstmt.setInt(1, no);
        pstmt.executeUpdate();
        return 1;
      } catch (Exception e) {
        System.out.println("삭제에 실패했습니다.");
        throw new RuntimeException(e);
      }
    } catch (Exception e) {
      System.out.println("DB에 접근할 수 없습니다.");
      throw new RuntimeException(e);
    }
  }

}
