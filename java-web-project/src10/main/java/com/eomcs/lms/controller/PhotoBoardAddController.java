package com.eomcs.lms.controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;

@Controller("/photoboard/add")
public class PhotoBoardAddController implements PageController {
  String uploadDir;

  @Autowired PhotoBoardService photoBoardService;
  @Autowired LessonService lessonService;
  
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    this.uploadDir = request.getServletContext().getRealPath("/upload/photoboard");
    if (request.getMethod().equals("GET")) {
      List<Lesson> lessons = lessonService.list();
      request.setAttribute("lessons", lessons);
      return "/photoboard/form.jsp";
    }

    PhotoBoard board = new PhotoBoard();
    board.setTitle(request.getParameter("title"));
    board.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));

    ArrayList<PhotoFile> files = new ArrayList<>();
    Collection<Part> photos = request.getParts();
    for (Part photo : photos) {
      if (photo.getSize() == 0 || !photo.getName().equals("photo"))
        continue;

      String filename = UUID.randomUUID().toString();
      photo.write(uploadDir + "/" + filename);
      
      PhotoFile file = new PhotoFile();
      file.setFilePath(filename);
      files.add(file);
    }
    board.setPhotoFiles(files);
    
    if (board.getLessonNo() == 0) {
      throw new Exception("사진 또는 파일을 등록할 수업을 선택하세요.");
      
    } else if (files.size() == 0) {
      throw new Exception("최소 한개 사진 파일을 등록해야 합니다.");
      
    } 
    photoBoardService.add(board);
    return "redirect:list";
  }
}
