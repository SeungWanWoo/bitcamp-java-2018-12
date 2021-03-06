
package com.eomcs.lms.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@Controller
public class MemberSearchController {
  
  @Autowired MemberService memberService;
  
  @RequestMapping("/member/search")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    String keyword = request.getParameter("name");
    List<Member> members = memberService.list(keyword);
    request.setAttribute("members", members);
    return "/member/search.jsp";
  }
}
