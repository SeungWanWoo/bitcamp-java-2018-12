
package com.eomcs.lms.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    MemberService memberService = ((ApplicationContext) this.getServletContext()
        .getAttribute("iocContainer")).getBean(MemberService.class);
    response.setContentType("text/html;charset=UTF-8");
    int no = Integer.parseInt(request.getParameter("no"));

    if (memberService.delete(no) == 1) {
      response.sendRedirect("list");
      return;
    }
    
    request.setAttribute("error.title", "회원 삭제 오류");
    request.setAttribute("error.content", "해당 번호의 수업이 없습니다.");
    request.getRequestDispatcher("/error.jsp").include(request, response);
  }
}
