package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDaoImpl implements LessonDao {

  public List<Lesson> findAll() {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      System.out.println("DBMS에 연결됨!");

      try (PreparedStatement stmt = con.prepareStatement(
          "select lesson_id, sdt, edt, tot_hr, day_hr, titl, conts "
          + "from lms_lesson order by lesson_id desc")){
        try (ResultSet rs = stmt.executeQuery()) {

          ArrayList<Lesson> list = new ArrayList<>();
          while (rs.next()) {
            Lesson lesson = new Lesson();
            lesson.setNo(rs.getInt("lesson_id"));
            lesson.setStartDate(rs.getDate("sdt"));
            lesson.setEndDate(rs.getDate("edt"));
            lesson.setTotalHours(rs.getInt("tot_hr"));
            lesson.setDayHours(rs.getInt("day_hr"));
            lesson.setTitle(rs.getString("titl"));
            lesson.setContents(rs.getString("conts"));
            
            list.add(lesson);
          }
          return list;
        }
      } 
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void insert(Lesson lesson) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      System.out.println("DBMS에 연결됨!");

      try (PreparedStatement stmt = con.prepareStatement(
          "insert into lms_lesson(sdt, edt, tot_hr, day_hr, titl, conts) "
          + "values(?,?,?,?,?,?)")){
        stmt.setDate(1, lesson.getStartDate());
        stmt.setDate(2, lesson.getEndDate());
        stmt.setInt(3, lesson.getTotalHours());
        stmt.setInt(4, lesson.getDayHours());
        stmt.setString(5, lesson.getTitle());
        stmt.setString(6, lesson.getContents());
        stmt.executeUpdate();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Lesson findByNo(int no) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      System.out.println("DBMS에 연결됨!");

      try (PreparedStatement stmt = con.prepareStatement(
          "select lesson_id, sdt, edt, tot_hr, day_hr, titl, conts "
          + "from lms_lesson where lesson_id = ?")){
        
        stmt.setInt(1, no);

        try (ResultSet rs = stmt.executeQuery()) {
          if (rs.next()) {
            Lesson lesson = new Lesson();
            lesson.setNo(rs.getInt("lesson_id"));
            lesson.setTitle(rs.getString("titl"));
            lesson.setContents(rs.getString("conts"));
            lesson.setStartDate(rs.getDate("sdt"));
            lesson.setEndDate(rs.getDate("edt"));
            lesson.setTotalHours(rs.getInt("tot_hr"));
            lesson.setDayHours(rs.getInt("day_hr"));
            
            return lesson;
          } else {
            return null;
          }
        } 
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int update(Lesson lesson) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      System.out.println("DBMS에 연결됨!");

      try (PreparedStatement stmt = con.prepareStatement(
          "update lms_lesson set sdt = ?, edt = ?, tot_hr = ?, "
          + "day_hr = ?, titl = ?, conts = ? where lesson_id = ?")) {

        stmt.setDate(1, lesson.getStartDate());
        stmt.setDate(2, lesson.getEndDate());
        stmt.setInt(3, lesson.getTotalHours());
        stmt.setInt(4,  lesson.getDayHours());
        stmt.setString(5, lesson.getTitle());
        stmt.setString(6, lesson.getTitle());
        stmt.setInt(7, lesson.getNo());
        
        return stmt.executeUpdate();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int delete(int no) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      try (PreparedStatement stmt = con.prepareStatement(
          "delete from lms_lesson where lesson_id = ?")) {
        stmt.setInt(1, no);

        return stmt.executeUpdate();
      } 
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
