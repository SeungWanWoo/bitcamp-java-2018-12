/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package bitcamp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/helloservlet")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
      resp.setContentType("text/html;charset=UTF-8");
      
      PrintWriter out = resp.getWriter();
      out.println("<html><head><title>hello</title><head>");
      out.println("<body><h1>hello!</h1></body>");
      out.println("</html>");
    }
}