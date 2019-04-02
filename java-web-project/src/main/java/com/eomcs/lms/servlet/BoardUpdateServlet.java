package com.eomcs.lms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@SuppressWarnings("serial")
@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    BoardService boardService = ((ApplicationContext) this.getServletContext()
        .getAttribute("iocContainer")).getBean(BoardService.class);
    
    Board board = new Board();
    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setContents(request.getParameter("contents"));
    
    if (boardService.update(board) == 1) {
      response.sendRedirect("list");
      return;
    }
    response.setHeader("Refresh", "2;url=list");
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<html><head>"
        + "<title>게시물 변경</title>"
        + "</head>");
    out.println("<body><h1>게시물 변경</h1>");
    out.println("<p>해당 게시물이 존재하지 않습니다.</p>");
    out.println("</body></html>");
  }
}
