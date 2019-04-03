package com.eomcs.lms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
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
    
    // <meta> 태그를 이용하여 리프래시 하기
    PrintWriter out = response.getWriter();
    out.println("<html><head>"
        + "<title>게시물 삭제</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>게시물 삭제</h1>");
    out.println("<p>해당 게시물이 존재하지 않습니다.</p>");
    out.println("</body></html>");
  }
}
