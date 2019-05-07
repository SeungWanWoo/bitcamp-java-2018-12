
package com.eomcs.lms.web.json;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@RestController("json/MemberController")
@RequestMapping("/json/member")
public class MemberController {

  @Autowired MemberService memberService;
  @Autowired ServletContext servletContext;

  @PostMapping("add")
  public Object add(Member member, Part photoFile) throws Exception {
    HashMap<String,Object> content = new HashMap<>();
    try {
      if (photoFile.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        String uploadDir = 
            servletContext.getRealPath("../upload/member");
        photoFile.write(uploadDir + "/" + filename);
        member.setPhoto(filename);
      }
      memberService.add(member);
      content.put("status", "success");
      
    } catch (Exception e) {
      content.put("status", "fail");
      content.put("message", e.getMessage());

    }
    return content;

  }

  @GetMapping("delete")
  public Object delete(int no) throws Exception {

    HashMap<String,Object> map = new HashMap<>();
    try {
      if (memberService.delete(no) == 0)
        throw new RuntimeException("해당 번호의 수업이 없습니다.");
      map.put("status", "success");
    } catch (Exception e) {
      map.put("status", "fail");
      map.put("message", e.getMessage());
    }
    return map;
  }

  @GetMapping("detail")
  public Object detail(int no) throws Exception {
    Member member = memberService.get(no);
    return member;
  }

  @GetMapping("list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="3") int pageSize) throws Exception {

    if (pageSize < 3 || pageSize > 8)
      pageSize = 3;

    int rowCount = memberService.size();
    int totalPage = rowCount / pageSize;

    if (rowCount % pageSize > 0)
      totalPage++;

    if (pageNo < 1)
      pageNo = 1;
    else if (pageNo > totalPage)
      pageNo = totalPage;

    HashMap<String,Object> map = new HashMap<>();
    List<Member> members = memberService.list(pageNo, pageSize, null);
    map.put("list", members);
    map.put("pageNo", pageNo);
    map.put("pageSize", pageSize);
    map.put("totalPage", totalPage);

    return map;
  }

  @GetMapping("search")
  public Object search(String name) throws Exception {
    List<Member> members = memberService.list(100, 1, name);
    return members;
  }

  @PostMapping("update")
  public Object update(Member member, Part photoFile) throws Exception {
    
    HashMap<String,Object> content = new HashMap<>();
    try {
    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      String uploadDir = 
          servletContext.getRealPath("/upload/member");
      photoFile.write(uploadDir + "/" + filename);
      member.setPhoto(filename);
    }
    if (memberService.update(member) == 0) 
      throw new Exception("해당 번호의 수업이 없습니다.");
    
    content.put("status", "success");
    
    } catch (Exception e) {
      content.put("status", "fail");
      content.put("message", e.getMessage());
    }
    return content;
  }
}
