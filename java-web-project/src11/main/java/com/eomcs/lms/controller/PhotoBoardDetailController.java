package com.eomcs.lms.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;

@Controller
public class PhotoBoardDetailController {
  
  @Autowired PhotoBoardService photoBoardService;
  @Autowired LessonService lessonService;
  
  @RequestMapping("/photoboard/detail")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    int no = Integer.parseInt(request.getParameter("no"));

    PhotoBoard board = photoBoardService.get(no);
    List<Lesson> lessons = lessonService.list();
    
    request.setAttribute("photoboard", board);
    request.setAttribute("lessons", lessons);
    request.setAttribute("files", board.getPhotoFiles());
    return "/photoboard/detail.jsp";
  }
}
