package com.eomcs.lms.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.service.BoardService;

@SuppressWarnings("serial")
@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int no = Integer.parseInt(request.getParameter("no"));
    
    BoardService boardService = ((ApplicationContext) this.getServletContext()
        .getAttribute("iocContainer")).getBean(BoardService.class);
    response.setContentType("text/html;charset=UTF-8");
    
    if (boardService.delete(no) == 1) {
      response.sendRedirect("list");
      return;
    };
    
    request.setAttribute("error.title", "게시물 삭제");
    request.setAttribute("error.content", "해당 번호의 게시물이 없습니다.");
    
    request.getRequestDispatcher("/error.jsp").forward(request, response);
  }
}
