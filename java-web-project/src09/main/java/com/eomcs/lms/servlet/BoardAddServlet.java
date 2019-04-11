package com.eomcs.lms.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@SuppressWarnings("serial")
@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setAttribute("viewUrl", "/board/form.jsp");
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    BoardService boardService = 
        ((ApplicationContext) this.getServletContext()
            .getAttribute("iocContainer")).getBean(BoardService.class);
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    Board board = new Board();
    board.setContents(request.getParameter("contents") + ":" + request.getRemoteAddr());
    boardService.add(board);

    request.setAttribute("viewUrl", "redirect:list");
  }
}
