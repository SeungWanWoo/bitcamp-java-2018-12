// 요청 핸들러의 아규먼트 - @RequestHeader
package bitcamp.app1; 

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/c04_6") // 컨트롤러에 URL을 매핑한다.
public class Controller04_6 {
  
  // 클라이언트의 HTTP 요청 헤더를 받고 싶으면
  // request handler의 아규먼트 앞에 @RequestHeader(헤더명) 애노테이션을 붙여라.
  // 테스트:
  // .../c04_6/h1
  @GetMapping("h1")
  @ResponseBody
  public void handler1(
      PrintWriter out,
      @RequestHeader("Accept") String accept,
      @RequestHeader("User-Agent") String userAgent
      ) {
    out.printf("Accept=%s\n", accept);
    out.printf("User-Agent=%s\n", userAgent);
    
    if (userAgent.matches(".*Chrome.*")) {
      out.println("Chrome Browser.");
    } else if (userAgent.matches(".*Safari.*")) {
      out.println("Safari Browser");
    } else if (userAgent.matches(".*Firefox.*")) {
      out.println("FireFox Browser");
    } else {
      out.println("Others");
    }
  }
  /*
  public static void main(String[] args) {
    String str = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36";
    // String str = "AA BB Aa Bb $12,000";
    // 정규 표현식으로 패턴을 정의한다.
    // String regex = "Chrome";
    String regex = "Linux.*Chrome";
    Pattern pattern = Pattern.compile(regex);
    
    // 주어진 문자열에서 패턴과 일치하는 정보를 찾는다.
    Matcher matcher = pattern.matcher(str);
    
    // 일치 여부를 확인한다.
    if (matcher.find()) {
      System.out.println("ok");
      // 문자열을 꺼낸다.d
      //for (int i = 1; i < matcher.groupCount(); i++) {
      //System.out.println(matcher.group(i));
      //}
    }
  }
  */
}