package com.eomcs.lms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet("/photoboard/add")
public class PhotoBoardAddServlet extends HttpServlet {
 
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    PhotoBoardService photoBoardService = ServerApp.iocContainer.getBean(PhotoBoardService.class);
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");
    
    PhotoBoard board = new PhotoBoard();
    board.setTitle(request.getParameter("title"));
    board.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));

    PrintWriter out = response.getWriter();

    ArrayList<PhotoFile> files = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      String filePath = request.getParameter(String.format("filePath%d", i));
      if (filePath.length() == 0) {
        continue;
      } else {
        PhotoFile file = new PhotoFile();
        file.setFilePath(filePath);

        files.add(file);
      }
    }
    board.setPhotoFiles(files);
    if (files.size() == 0) {
      out.println("<p>최소 한개 사진 파일을 등록해야합니다.</p>");
    } else { 
      photoBoardService.add(board);
    }
    out.println("<html><head>"
        + "<title>사진 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>사진 등록</h1>");
    out.println("<p>저장하였습니다.</p>");
    out.println("</body></html>");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head><title>사진 추가</title></head>");
    out.println("<body>");
    out.println("<h1>사진 추가</h1>");
    out.println("<form action='add' method='post'>");
    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("   <th>사진 제목</th>");
    out.println("   <td><input name='title'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("   <th>수업</th>");
    out.println("   <td><input type='number' name='lessonNo'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("   <th><td colspan='2'>최소 한 개의 사진 파일을 등록해야 합니다.</td></tr>");
    out.println("<tr>");
    out.println("<th rowspan='5'>사진 이름</th>");
    for (int i = 0; i < 5; i++) {
      out.println(" <td><input type='text' name='filePath" + i +"'></td>");
      out.println("</tr>");
    }
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
