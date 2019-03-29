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
@WebServlet("/photoboard/update")
public class PhotoBoardUpdateServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    PhotoBoardService photoBoardService = ServerApp.iocContainer.getBean(PhotoBoardService.class);
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    
    PhotoBoard board = new PhotoBoard();
    PrintWriter out = response.getWriter();

    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setTitle(request.getParameter("title"));

    ArrayList<PhotoFile> photoFiles = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      String filePath = request.getParameter(String.format("filePath%d", i));
      if (filePath.length() == 0) {
        continue;
      } 
      PhotoFile file = new PhotoFile();
      file.setFilePath(filePath);
      file.setPhotoBoardNo(board.getNo());
      photoFiles.add(file);
    }

    board.setPhotoFiles(photoFiles);
    photoBoardService.update(board);

    out.println("<html><head>"
        + "<title>사진 내용 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>사진 내용 변경</h1>");
    out.println("<p>변경하였습니다.</p>");
    out.println("</body></html>");
  }
}
