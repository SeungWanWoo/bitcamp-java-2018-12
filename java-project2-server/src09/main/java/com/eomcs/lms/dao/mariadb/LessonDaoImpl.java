package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDaoImpl implements LessonDao {

  Connection con;

  public LessonDaoImpl (Connection con) {
    this.con = con;
  }

  public List<Lesson> findAll() {
    try (PreparedStatement pstmt = con.prepareStatement(
        "select lesson_id, sdt, edt, tot_hr, day_hr, titl, conts"
            + " from lms_lesson order by lesson_id desc")) {

      try (ResultSet rs = pstmt.executeQuery()) {
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
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void insert(Lesson lesson) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "insert into lms_lesson (sdt, edt, tot_hr, day_hr, titl, conts) "
            + "values (?,?,?,?,?,?)")) {
      pstmt.setDate(1, lesson.getStartDate());
      pstmt.setDate(2, lesson.getEndDate());
      pstmt.setInt(3, lesson.getTotalHours());
      pstmt.setInt(4, lesson.getDayHours());
      pstmt.setString(5, lesson.getTitle());
      pstmt.setString(6, lesson.getContents());
      pstmt.executeUpdate();
    } catch (Exception e) {
      throw new RuntimeException(e); 
    }
  }

  public Lesson findByNo(int no) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "select lesson_id, sdt, edt, tot_hr, day_hr, titl, conts"
            + " from lms_lesson where lesson_id = ?")) {
      pstmt.setInt(1, no);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          Lesson lesson = new Lesson();
          lesson.setNo(rs.getInt("lesson_id"));
          lesson.setStartDate(rs.getDate("sdt"));
          lesson.setEndDate(rs.getDate("edt"));
          lesson.setTotalHours(rs.getInt("tot_hr"));
          lesson.setDayHours(rs.getInt("day_hr"));
          lesson.setTitle(rs.getString("titl"));
          lesson.setContents(rs.getString("conts"));

          return lesson;
        }
        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int update(Lesson lesson) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "update lms_lesson set sdt = ?, edt = ?, tot_hr = ?, day_hr = ?,"
            + " titl = ?, conts = ? where lesson_id = ?")) {
      pstmt.setDate(1, lesson.getStartDate());
      pstmt.setDate(2, lesson.getEndDate());
      pstmt.setInt(3, lesson.getTotalHours());
      pstmt.setInt(4, lesson.getDayHours());
      pstmt.setString(5, lesson.getTitle());
      pstmt.setString(6, lesson.getContents());
      pstmt.setInt(7, lesson.getNo());

      pstmt.executeUpdate();

      return 1;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int delete(int no) {
    try (PreparedStatement pstmt = con.prepareStatement(
        "delete from lms_lesson where lesson_id = ?")) {
      pstmt.setInt(1, no);
      pstmt.executeUpdate();

      return 1;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
