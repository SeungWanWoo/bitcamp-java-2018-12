
package com.eomcs.lms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.lms.ServerApp;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/member/search")
public class MemberSearchServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    MemberService memberService = ServerApp.iocContainer.getBean(MemberService.class);
    response.setContentType("text/html;charset=UTF-8");
    
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head><title>회원 검색</title></head>");
    out.println("<body>");
    out.println("<h1>회원 검색</h1>");
    out.println("<table border='1'>");
    out.println("<tr> <th>번호</th> <th>이름</th> <th>이메일</th> <th>전화번호</th> "
        + "<th>등록일</th> </tr>");

    String keyword = request.getParameter("name");
    List<Member> members = memberService.list(keyword);
    for (Member member : members) {
      out.println(String.format("<tr><td>%d</td> "
          + "<td><a href='detail?no=%1$d'>%s</a></td> "
          + "<td>%s</td> "
          + "<td>%s</td> "
          + "<td>%s</td> </tr>", 
          member.getNo(), member.getName(), 
          member.getEmail(), member.getTel(), member.getRegisteredDate()));
    }
    out.println("<a href='list'>목록</a>");
    out.println("</table></body></html>");
  }
}
