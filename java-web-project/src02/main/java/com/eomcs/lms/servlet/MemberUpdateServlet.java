
package com.eomcs.lms.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.eomcs.lms.InitServlet;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    MemberService memberService = InitServlet.iocContainer.getBean(MemberService.class);
    response.setContentType("text/html;charset=UTF-8");
    
    Member member = new Member();
    member.setNo(Integer.parseInt(request.getParameter("no")));
    member.setName(request.getParameter("name"));
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));
    member.setTel(request.getParameter("tel"));
    
    Part photo = request.getPart("photo");
    if (photo.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String uploadDir = this.getServletContext().getRealPath("/upload/member");
      photo.write(uploadDir + "/" + filename);
      member.setPhoto(filename);
    }
    
    if (memberService.update(member) == 1) {
      response.sendRedirect("list");
      return;
    }
    
    response.setHeader("Refresh", "2;url=list");
    
    PrintWriter out = response.getWriter();
    out.println("<html><head>"
        + "<title>회원 변경</title>"
        + "</head>");
    out.println("<body><h1>회원 변경</h1>");
    out.println("<p>해당 게시물이 존재하지 않습니다.</p>");
    out.println("</body></html>");
  }
}
