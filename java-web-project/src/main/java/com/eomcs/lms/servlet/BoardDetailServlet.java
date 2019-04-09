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
@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
   
    BoardService boardService = ((ApplicationContext) this.getServletContext()
        .getAttribute("iocContainer")).getBean(BoardService.class);
    
    int no = Integer.parseInt(request.getParameter("no"));

    Board board = boardService.get(no);
    
    // JSP가 사용할 수 있도록 ServletRequest 보관소에 저장해둔다.
    request.setAttribute("board", board);

    request.setAttribute("viewUrl", "/board/detail.jsp");
  }
}
