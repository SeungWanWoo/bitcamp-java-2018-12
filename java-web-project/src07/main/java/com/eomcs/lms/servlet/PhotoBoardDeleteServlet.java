package com.eomcs.lms.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.service.PhotoBoardService;

@SuppressWarnings("serial")
@WebServlet("/photoboard/delete")
public class PhotoBoardDeleteServlet extends HttpServlet {
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    PhotoBoardService photoBoardService = ((ApplicationContext) this.getServletContext()
        .getAttribute("iocContainer")).getBean(PhotoBoardService.class);
    response.setContentType("text/html;charset=UTF-8");
    
    int no = Integer.parseInt(request.getParameter("no"));

    if (photoBoardService.delete(no) == 1) {
      response.sendRedirect("list");
      return;
    }
    
    request.setAttribute("error.title", "사진 변경 오류");
    request.setAttribute("error.content", "해당 번호의 사진이 존재하지 않습니다.");
    request.getRequestDispatcher("/error.jsp").include(request, response);
  }
}
