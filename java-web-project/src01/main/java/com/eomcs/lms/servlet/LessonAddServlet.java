package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.InitServlet;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@SuppressWarnings("serial")
@WebServlet("/lesson/add")
public class LessonAddServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    LessonService lessonService = InitServlet.iocContainer.getBean(LessonService.class);
    response.setContentType("text/html;charset=UTF-8");
    Lesson lesson = new Lesson();

    lesson.setTitle(request.getParameter("title"));
    lesson.setContents(request.getParameter("contents"));
    lesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
    lesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
    lesson.setTotalHours(Integer.parseInt(request.getParameter("totalHours")));
    lesson.setDayHours(Integer.parseInt(request.getParameter("dayHours")));

    lessonService.add(lesson);
    PrintWriter out = response.getWriter();

    out.println("<html><head>"
        + "<title>수업 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>수업 등록</h1>");
    out.println("<p>저장하였습니다.</p>");
    out.println("</body></html>");
  }
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head><title>신규 수업</title></head>");
    out.println("<body>");
    out.println("<h1>신규 수업</h1>");
    out.println("<form action='add' method='post'>");
    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("<th>제목</th>");
    out.println("<td><input type='text' name='title'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<th>내용</th>");
    out.println("<td><textarea name='contents' rows='5' cols='50'></textarea></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<th>시작일</th>");
    out.println("<td><input type='date' name='startDate'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<th>종료일</th>");
    out.println("<td><input type='date'  name='endDate'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<th>총 수업시간</th>");
    out.println("<td><input type='number'  name='totalHours'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<th>일 수업시간</th>");
    out.println("<td><input type='number'  name='dayHours'></td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("<p>");
    out.println("<button type='submit'>등록</button>");
    out.println("<a href='list'>목록</a>");
    out.println("</p>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
