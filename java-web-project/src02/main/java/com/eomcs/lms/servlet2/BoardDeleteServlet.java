package com.eomcs.lms.servlet2;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.InitServlet;
import com.eomcs.lms.service.BoardService;

@SuppressWarnings("serial")
@WebServlet("/board2/delete")
public class BoardDeleteServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int no = Integer.parseInt(request.getParameter("no"));
    
    BoardService boardService = InitServlet.iocContainer.getBean(BoardService.class);
    PrintWriter out = response.getWriter();
    out.println("<h1>게시물 삭제</h1>");
    if (boardService.delete(no) == 0) {
      out.println("<p>해당 게시물이 존재하지 않습니다.</p>");
    } else {
      out.println("<p>삭제했습니다.</p>");
    }
  }
}
