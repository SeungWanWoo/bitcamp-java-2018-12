
package com.eomcs.lms.handler;
import java.util.List;
import org.springframework.stereotype.Component;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@Component
public class MemberCommand {
  MemberService memberService;

  public MemberCommand(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/list")
  public void list(Response response) {
    List<Member> members = memberService.list(null);
    for (Member member : members) {
      response.println(
          String.format("%3d, %-4s, %-20s, %-15s, %s", 
              member.getNo(), member.getName(), 
              member.getEmail(), member.getTel(), member.getRegisteredDate()));
    }
  }
  
  @RequestMapping("/member/add")
  public void add(Response response) throws Exception {
    Member member = new Member();

    member.setName(response.requestString("이름? "));
    member.setEmail(response.requestString("이메일? "));
    member.setPassword(response.requestString("암호? "));
    member.setPhoto(response.requestString("사진? "));
    member.setTel(response.requestString("전화? "));
    memberService.add(member);
    response.println("저장하였습니다.");
  }
  
  @RequestMapping("/member/detail")
  public void detail(Response response) throws Exception {
    int no = response.requestInt("번호? ");
    Member member = memberService.get(no);
    if (member == null) {
      response.println("해당 회원 정보가 존재하지 않습니다.");
      return;
    }
    response.println(String.format("이름: %s", member.getName()));
    response.println(String.format("이메일: %s", member.getEmail()));
    response.println(String.format("암호: %s", member.getPassword()));
    response.println(String.format("사진: %s", member.getPhoto()));
    response.println(String.format("전화: %s", member.getTel()));
    response.println(String.format("가입일: %s", member.getRegisteredDate()));
  }
  
  @RequestMapping("/member/update")
  public void update(Response response) throws Exception {
    int no = response.requestInt("번호? ");
    Member member = memberService.get(no);
    
    if (member == null) {
      response.println("해당 회원이 존재하지 않습니다.");
      return;
    }
    
    Member temp = new Member();
    temp.setNo(member.getNo());
    
    String input = response.requestString(
        String.format("이름(%s)? ", member.getName()));
    if (input.length() > 0) 
      temp.setName(input);

    if ((input = response.requestString(
        String.format("이메일(%s)? ", member.getEmail()))).length() > 0)
      temp.setEmail(input);

    if ((input = response.requestString(
        "암호(새 암호를 입력하세요)? ")).length() > 0)
      temp.setPassword(input);

    if ((input = response.requestString(
        String.format("사진(%s)? ", member.getPhoto()))).length() > 0)
      temp.setPhoto(input);

    if ((input = response.requestString(
        String.format("전화(%s)? ", member.getTel()))).length() > 0)
      temp.setTel(input);
    
    if (memberService.update(temp) != 0)
      response.println("회원을 변경했습니다.");
    else
      response.println("변경을 취소하였습니다.");
  }
  
  @RequestMapping("/member/delete")
  public void delete(Response response) throws Exception {

    int no = response.requestInt("번호? ");
    if (memberService.delete(no) == 0) {
      response.println("해당 번호의 회원이 없습니다.");
      return;
    }
    response.println("회원을 삭제했습니다.");
  }
  
  @RequestMapping("/member/search")
  public void search(Response response) throws Exception {
    String keyword = response.requestString("검색어? ");
    List<Member> members = memberService.list(keyword);
    for (Member member : members) {
      response.println(
          String.format("%3d, %-4s, %-20s, %-15s, %s", 
              member.getNo(), member.getName(), 
              member.getEmail(), member.getTel(), member.getRegisteredDate()));
    }
  }
}
