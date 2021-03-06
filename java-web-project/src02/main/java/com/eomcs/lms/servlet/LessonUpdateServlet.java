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
@WebServlet("/lesson/update")
public class LessonUpdateServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    LessonService lessonService = InitServlet.iocContainer.getBean(LessonService.class);
    response.setContentType("text/html;charset=UTF-8");
    Lesson lesson = new Lesson();
    lesson.setNo(Integer.parseInt(request.getParameter("no")));
    lesson.setTitle(request.getParameter("title"));
    lesson.setContents(request.getParameter("contents"));
    lesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
    lesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
    lesson.setTotalHours(Integer.parseInt(request.getParameter("totalHours")));
    lesson.setDayHours(Integer.parseInt(request.getParameter("dayHours")));

    if (lessonService.update(lesson) == 1) {
      response.sendRedirect("list");
      return;
    }

    response.setHeader("Refresh", "2;url=list");
    
    PrintWriter out = response.getWriter();
    out.println("<html><head>"
        + "<title>수업 변경</title>"
        + "</head>");
    out.println("<body>");
    out.println("   <h1>수업 변경</h1>");
    out.println("<p>해당 수업 존재하지 않습니다.</p>");
    out.println("</body></html>");
  }
}
