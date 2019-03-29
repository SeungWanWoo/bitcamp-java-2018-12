package com.eomcs.lms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.ServerApp;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.PhotoBoardService;

@SuppressWarnings("serial")
@WebServlet("/photoboard/detail")
public class PhotoBoardDetailServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    PhotoBoardService photoBoardService = ServerApp.iocContainer.getBean(PhotoBoardService.class);
    response.setContentType("text/html;charset=UTF-8");
    
    int no = Integer.parseInt(request.getParameter("no"));

    PhotoBoard board = photoBoardService.get(no);
    PrintWriter out = response.getWriter();

    if (board == null) {
      out.println("<p>해당 사진 정보가 존재하지 않습니다.</p>");
      return;
    }

    out.println("<html><head><title>사진 조회</title></head>");
    out.println("<body><h1>사진 조회</h1>");
    out.println("<form action='update' method='post'>");
    out.println("<table border='1'>");
    out.println(
        String.format("<tr>"
            + "<th>번호</th>"
            + "<td><input type='text' name='no' value='%d' readonly></td>"
            + "</tr>\n", no));

    out.println(String.format("<tr> <th>내용</th> "
        + "<td><textarea name='title' rows='3' cols='50'>%s</textarea></td>"
        + "</tr>", board.getTitle()));

    out.println(String.format("<tr> <th>등록일</th> <td>%s</td> </tr>", board.getCreatedDate()));

    out.println(String.format("<tr> <th>조회수</th> <td>%d</td></tr>", board.getViewCount()));

    out.println(String.format("<tr> <th>수업 정보</th> "
        + "<td>%s(%s ~ %s)</td></tr>", board.getLesson().getTitle(), board.getLesson().getStartDate()
        ,board.getLesson().getEndDate()));

    out.println("<tr>");
    out.println(String.format("<input type='hidden' name='lessonNo' value='%d'", 
        board.getLesson().getNo()));
    out.println("</tr>");
    out.println("<tr> <td colspan='2'>최소 한 개의 사진 파일을 등록해야 합니다.</td></tr>");

    List<PhotoFile> files = board.getPhotoFiles();
    out.println(String.format("<tr> <th>사진 파일</th>"));
    out.println("   <td>");
    for (int i = 0; i < 5; i++) {
      if (i < files.size()) {
        PhotoFile file = files.get(i);
        out.println(String.format("<input type='text' name='filePath%d' value=%s><br>\n", 
            i, file.getFilePath()));
      } else
        out.println(String.format("<input type='text' name='filePath%d'><br>\n", i));
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
