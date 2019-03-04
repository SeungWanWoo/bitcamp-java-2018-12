package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {
  Connection con;

  public PhotoFileDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public List<PhotoFile> findByPhotoBoardNo(int photoBoardNo) {

    try (PreparedStatement pstmt = con.prepareStatement(
        "select photo_file_id, photo_id, file_path from lms_photo_file"
            + " where photo_id = ?"
            + " order by photo_file_id desc")) {
      pstmt.setInt(1, photoBoardNo);
      
      try (ResultSet rs = pstmt.executeQuery()) {
        ArrayList<PhotoFile> list = new ArrayList<>();
        while (rs.next()) {
          PhotoFile photo = new PhotoFile();
          photo.setNo(rs.getInt("photo_file_id"));
          photo.setPhotoBoardNo(rs.getInt("photo_id"));
          photo.setFilePath(rs.getString("file_path"));

          list.add(photo);
        }
        return list;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public void insert(PhotoFile photoFile) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "insert into lms_photo_file (file_path, photo_id) values (?,?)")) {
      pstmt.setString(1, photoFile.getFilePath());
      pstmt.setInt(2, photoFile.getPhotoBoardNo());
      pstmt.executeUpdate();
      
      
      
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
/*
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
            PhotoBoard photo = new PhotoBoard();
            photo.setNo(rs.getInt("photo_id"));
            photo.setTitle(rs.getString("titl"));
            photo.setCreatedDate(rs.getDate("cdt"));
            photo.setViewCount(rs.getInt("vw_cnt"));
            photo.setLessonNo(rs.getInt("lesson_id"));

            return photo;
          }
          return null;
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int update(PhotoBoard photo) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "update lms_photo set titl = ? where photo_id = ?")) {
      pstmt.setString(1, photo.getTitle());
      pstmt.setInt(2, photo.getNo());
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
  */
}
