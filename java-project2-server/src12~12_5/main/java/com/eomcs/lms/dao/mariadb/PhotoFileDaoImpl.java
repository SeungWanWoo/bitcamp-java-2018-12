package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.DataSource;

public class PhotoFileDaoImpl implements PhotoFileDao {

  DataSource dataSource;
  
  public PhotoFileDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }
  
  @Override
  public List<PhotoFile> findByPhotoFileNo(int photoFileNo) {
    Connection con = dataSource.getConnection();
    try (PreparedStatement pstmt = con.prepareStatement(
        "select photo_file_id, photo_id, file_path "
            + "from lms_photo_file"
            + " where photo_id = ?"
            + " order by photo_file_id asc")) {
      
      pstmt.setInt(1, photoFileNo); // 특정 사진 게시물에 대해 첨부파일을 가져오기
      
      try (ResultSet rs = pstmt.executeQuery()) {
        ArrayList<PhotoFile> list = new ArrayList<>();
        while (rs.next()) {
          PhotoFile photoFile = new PhotoFile();
          photoFile.setNo(rs.getInt("photo_file_id"));
          photoFile.setPhotoBoardNo(rs.getInt("photo_id"));
          photoFile.setFilePath(rs.getString("file_path"));

          list.add(photoFile);
        }
        return list;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public void insert(PhotoFile photoFile) {
    Connection con = dataSource.getConnection();
    try (PreparedStatement pstmt = con.prepareStatement(
        "insert into lms_photo_file (file_path, photo_id) values (?,?)")) {
      pstmt.setString(1, photoFile.getFilePath());
      pstmt.setInt(2, photoFile.getPhotoBoardNo());

      pstmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public int deleteByPhotoBoardNo(int photoBoardNo) {
    Connection con = dataSource.getConnection();
    try (PreparedStatement pstmt = con.prepareStatement(
        "delete from lms_photo_file where photo_id = ?")) {
      pstmt.setInt(1, photoBoardNo);
      pstmt.executeUpdate();

      return 1;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
