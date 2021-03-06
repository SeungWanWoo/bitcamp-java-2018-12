
package com.eomcs.lms.web;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

  @Autowired MemberService memberService;
  @Autowired ServletContext servletContext;
  
  @PostMapping("add")
  public String add(Member member, Part photoFile) throws Exception {

    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String uploadDir = 
          servletContext.getRealPath("/upload/member");
      photoFile.write(uploadDir + "/" + filename);
      member.setPhoto(filename);
    }

    memberService.add(member);
    return "redirect:.";
  }
  
  @GetMapping("form")
  public void form() {}
  
  @GetMapping("delete/{no}")
  public String delete(@PathVariable int no) throws Exception {

    if (memberService.delete(no) == 0)
      throw new Exception("해당 번호의 수업이 없습니다.");

    return "redirect:../";
  }
  
  @GetMapping("{no}")
  public String detail(@PathVariable int no, Model model) throws Exception {
    
    Member member = memberService.get(no);
    model.addAttribute("member", member);
    
    return "member/detail";
  }
  
  @GetMapping
  public String list(Model model) throws Exception {
    List<Member> members = memberService.list(null);
    model.addAttribute("members", members);
    
    return "member/list";
  }
  
  @GetMapping("search")
  public void search(String name, Model model) throws Exception {
    
    List<Member> members = memberService.list(name);
    model.addAttribute("members", members);
    
  }
  
  @PostMapping("update")
  public String update(Member member, Part photoFile) throws Exception {

    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String uploadDir = 
          servletContext.getRealPath("/upload/member");
      photoFile.write(uploadDir + "/" + filename);
      member.setPhoto(filename);
    }

    if (memberService.update(member) == 0) 
      throw new Exception("해당 번호의 수업이 없습니다.");

    return "redirect:.";
  }
}
