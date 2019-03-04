package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  Connection con;

  public PhotoBoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public List<PhotoBoard> findAll() {

    try (PreparedStatement pstmt = con.prepareStatement(
        "select photo_id, titl, cdt, vw_cnt, lesson_id from lms_photo"
            + " order by photo_id desc")) {

      try (ResultSet rs = pstmt.executeQuery()) {
        ArrayList<PhotoBoard> list = new ArrayList<>();
        while (rs.next()) {
          PhotoBoard photoBoard = new PhotoBoard();
          photoBoard.setNo(rs.getInt("photo_id"));
          photoBoard.setTitle(rs.getString("titl"));
          photoBoard.setCreatedDate(rs.getDate("cdt"));
          photoBoard.setViewCount(rs.getInt("vw_cnt"));
          photoBoard.setLessonNo(rs.getInt("lesson_id"));

          list.add(photoBoard);

        }
        return list;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void insert(PhotoBoard photoBoard) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "insert into lms_photo (titl,lesson_id) values (?,?)")) {
      pstmt.setString(1, photoBoard.getTitle());
      pstmt.setInt(2, photoBoard.getLessonNo());

      pstmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public PhotoBoard findByNo(int no) {
    try {
      try (PreparedStatement pstmt = con.prepareStatement(
          "update lms_photo set vw_cnt = vw_cnt + 1"
              + " where photo_id = ?")) {
        pstmt.setInt(1, no);
        pstmt.executeUpdate();
      }
      try (PreparedStatement pstmt = con.prepareStatement(
          "select photo_id, titl, cdt, vw_cnt, lesson_id from lms_photo"
              + " where photo_id = ?")) {
        pstmt.setInt(1, no);

        try (ResultSet rs = pstmt.executeQuery()) {
          if (rs.next()) {
            PhotoBoard photoBoard = new PhotoBoard();
            photoBoard.setNo(rs.getInt("photo_id"));
            photoBoard.setTitle(rs.getString("titl"));
            photoBoard.setCreatedDate(rs.getDate("cdt"));
            photoBoard.setViewCount(rs.getInt("vw_cnt"));
            photoBoard.setLessonNo(rs.getInt("lesson_id"));

            return photoBoard;
          }
          return null;
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int update(PhotoBoard photoBoard) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "update lms_photo set titl = ? where photo_id = ?")) {
      pstmt.setString(1, photoBoard.getTitle());
      pstmt.setInt(2, photoBoard.getNo());
      pstmt.executeUpdate();

      return 1;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int delete(int no) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "delete from lms_photo where photo_id = ?")) {
      pstmt.setInt(1, no);
      pstmt.executeUpdate();

      return 1;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

