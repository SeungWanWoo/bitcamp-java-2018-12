package com.eomcs.lms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;

@SuppressWarnings("serial")
@WebServlet("/photoboard/detail")
public class PhotoBoardDetailServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = (ApplicationContext) sc.getAttribute("iocContainer");
    
    LessonService lessonService = iocContainer.getBean(LessonService.class);
    PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

    response.setContentType("text/html;charset=UTF-8");

    int no = Integer.parseInt(request.getParameter("no"));

    PhotoBoard board = photoBoardService.get(no);
    PrintWriter out = response.getWriter();


    out.println("<html><head><title>사진 조회</title></head>");
    out.println("<body><h1>사진 조회</h1>");
    if (board == null) {
      out.println("<p>해당 사진 정보가 존재하지 않습니다.</p>");
      return;
    } else {
      out.println("<form action='update' method='post' enctype='multipart/form-data'>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("<th>번호</th>");
      out.println(
          String.format("<td><input type='text' name='no' value='%d' readonly></td>"
              + "</tr>\n", board.getNo()));

      out.println(String.format("<tr> <th>내용</th> "
          + "<td><textarea name='title' rows='3' cols='50'>%s</textarea></td>"
          + "</tr>", board.getTitle()));

      out.println(String.format("<tr> <th>등록일</th> <td>%s</td> </tr>", board.getCreatedDate()));

      out.println(String.format("<tr> <th>조회수</th> <td>%d</td></tr>", board.getViewCount()));

      out.println("<tr>");
      out.println("   <th>수업</th>");
      out.println("   <td><select name='lessonNo'>");
      List<Lesson> lessons = lessonService.list();
      for (Lesson lesson : lessons) {
        out.printf("       <option value='%d' %s>%s(%s ~ %s)</option>\n", 
            lesson.getNo(), 
            board.getLessonNo() == lesson.getNo() ? "selected" : "",
            lesson.getTitle(),
            lesson.getStartDate(), 
            lesson.getEndDate());
      }
      out.println("   </select></td>");
      out.println("</tr>");
      out.println("</tr>");
      out.println("<tr> <td colspan='2'>최소 한 개의 사진 파일을 등록해야 합니다.</td></tr>");
      for (int i = 0; i < 5; i++) {
        out.println(String.format("<th>사진%d</th>", i + 1));
        out.println(String.format(" <td><input type='file' name='photo'></td>",
            i));
        out.println("</tr>");
      }
      out.println("<tr>");
      out.println("   <th>사진</th>");
      out.println("<td>");
      List<PhotoFile> files = board.getPhotoFiles();
      for (PhotoFile file : files) {
        out.println(String.format("<img src='../upload/photoboard/%s' style='height:80px'><br>\n", 
            file.getFilePath()));
      }
      out.println("</td></tr>");
      out.println("</table>");

      out.println("<p><a href='list'>목록</a>"
          + " <a href='delete?no=" + board.getNo() + "'>삭제</a>"
          + " <button type='submit'>변경</button>"
          + "</p>"); // p = 한 문단
      out.println("</form>");
      out.println("</body></html>");
    }
  }
}
