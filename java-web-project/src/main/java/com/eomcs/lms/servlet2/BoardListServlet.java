package com.eomcs.lms.servlet2;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.InitServlet;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

@SuppressWarnings("serial")
@WebServlet("/board2/list")
public class BoardListServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    // Spring IoC 컨테이너에서 BoardSevice 객체를 꺼낸다.
    BoardService boardService = InitServlet.iocContainer.getBean(BoardService.class);
    PrintWriter out = response.getWriter();
    List<Board> boards = boardService.list();

    out.println("<hr>");
    out.println("<h1>게시물 목록</h1>");
    out.println("<p><a href='board2?command=form'>새글</a></p>");
    out.println("<table border='1'>");
    out.println("<tr> <th>번호</th> <th>제목</th> <th>등록일</th> <th>조회수</th> </tr>");

    for (Board board : boards) {
      out.println(String.format("<tr><td>%d</td> "
          + "<td><a href='board2?command=detail&no=%1$d'>%s</a></td> "
          + "<td>%s</td> "
          + "<td>%d</td></tr>", 
          board.getNo(),
          board.getContents(),
          board.getCreatedDate(),
          board.getViewCount()));
    }
    out.println("</table><a href='index.html'>처음화면</a>");
  }
}
