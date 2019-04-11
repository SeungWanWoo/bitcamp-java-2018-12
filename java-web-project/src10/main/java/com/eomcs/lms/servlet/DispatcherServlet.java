package com.eomcs.lms.servlet;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.controller.PageController;

@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@SuppressWarnings("serial")
@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String pageControllerPath = request.getPathInfo();

    //클라이언트가 요청한 페이지 컨트롤러를 실행한다.
    response.setContentType("text/html;charset=UTF-8");

    // 1) Spring IoC 컨테이너를 꺼낸다.
    ServletContext sc = this.getServletContext();
    ApplicationContext iocContainer = (ApplicationContext) sc.getAttribute("iocContainer");

    try {
      // 2) Spring IoC 컨테이너에서 클라이언트가 요청한 URL을 처리할 페이지 컨트롤러를 꺼낸다.
      PageController pageController = (PageController) iocContainer.getBean(pageControllerPath);

      // 3) 페이지 컨트롤러 실행
      String viewUrl = pageController.execute(request, response);

      // ServletRequest 보관소에 저장된 view 컴포넌트 URL을 꺼낸다.
      // 요청을 처리한 후 오류가 있다면 오류 출력 페이지로 보낸다.
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9)); // redirect:list
          
      } else {
        // 페이지 컨트롤러가 알려준 JSP를 실행한다.
        request.getRequestDispatcher(viewUrl).include(request, response);
      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("error.title", "요청 처리 오류");
      request.setAttribute("error.content", e.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
