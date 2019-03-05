package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardDaoImpl implements BoardDao {

  Connection con;

  public BoardDaoImpl(Connection con) {
    this.con = con;
  }

  public List<Board> findAll() {

    try (PreparedStatement pstmt = con.prepareStatement(
        "select board_id, conts, cdt, vw_cnt from lms_board"
            + " order by board_id desc")) {

      try (ResultSet rs = pstmt.executeQuery()) {
        ArrayList<Board> list = new ArrayList<>();
        while (rs.next()) {
          Board board = new Board();
          board.setNo(rs.getInt("board_id"));
          board.setContents(rs.getString("conts"));
          board.setCreatedDate(rs.getDate("cdt"));
          board.setViewCount(rs.getInt("vw_cnt"));

          list.add(board);

        }
        return list;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void insert(Board board) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "insert into lms_board (conts) values (?)")) {
      pstmt.setString(1, board.getContents());
      pstmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Board findByNo(int no) {
    try {
      try (PreparedStatement pstmt = con.prepareStatement(
          "update lms_board set vw_cnt = vw_cnt + 1"
              + " where board_id = ?")) {
        pstmt.setInt(1, no);
      }

      try (PreparedStatement pstmt = con.prepareStatement(
          "select board_id, conts, cdt, vw_cnt from lms_board"
              + " where board_id = ?")) {
        pstmt.setInt(1, no);

        try (ResultSet rs = pstmt.executeQuery()) {
          if (rs.next()) {
            Board board = new Board();
            board.setNo(rs.getInt("board_id"));
            board.setContents(rs.getString("conts"));
            board.setCreatedDate(rs.getDate("cdt"));
            board.setViewCount(rs.getInt("vw_cnt"));

            return board;
          }
          return null;
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int update(Board board) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "update lms_board set conts = ? where board_id = ?")) {
      pstmt.setString(1, board.getContents());
      pstmt.setInt(2, board.getNo());
      pstmt.executeUpdate();

      return 1;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int delete(int no) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "delete from lms_board where board_id = ?")) {
      pstmt.setInt(1, no);
      pstmt.executeUpdate();

      return 1;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
