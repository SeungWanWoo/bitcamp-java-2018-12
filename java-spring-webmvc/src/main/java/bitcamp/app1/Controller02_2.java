// GET, POST 구분하기
package bitcamp.app1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 이 애노테이션을 붙인다.
@RequestMapping("/c02_2") // 컨트롤러에 URL을 매핑한다.
public class Controller02_2 {
  
  // 테스트 방법:
  // => http://localhost:8080/java-spring-webmvc/html/app1/c02_2.html
  
  @GetMapping // GET 요청일 때만 호출된다.
  @ResponseBody
  public String handler1() {
    return "GET";
  }
  
  @PostMapping // POST 요청일 때만 호출된다.
  @ResponseBody
  public String handler2() {
    return "POST";
  }
}
