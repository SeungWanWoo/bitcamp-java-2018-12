package com.eomcs.lms.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.context.RequestParam;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;

@Controller
public class PhotoBoardController {
  String uploadDir;

  @Autowired PhotoBoardService photoBoardService;
  @Autowired LessonService lessonService;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("/photoboard/add")
  public String add(
      HttpServletRequest request,
     PhotoBoard board,
      @RequestParam("photoFile") Part[] photoFiles) throws Exception {
    this.uploadDir = servletContext.getRealPath("/upload/photoboard");

    ArrayList<PhotoFile> files = new ArrayList<>();
    for (Part photo : photoFiles) {
      if (photo.getSize() == 0 || !photo.getName().equals("photoFile"))
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
      
    } else if (board.getTitle().length() == 0) {
      throw new Exception("사진 게시물의 제목을 입력해주세요.");
    } else {
      photoBoardService.add(board);
      return "redirect:list";
    }
  }
  
  @RequestMapping("/photoboard/form")
  public String form(Map<String, Object> map) {
    List<Lesson> lessons = lessonService.list();
    map.put("lessons", lessons);
    return "/photoboard/form.jsp";
  }
  
  @RequestMapping("/photoboard/delete")
  public String delete(@RequestParam("no") int no) throws Exception {

    if (photoBoardService.delete(no) == 0) 
      throw new Exception("해당 번호의 사진이 존재하지 않습니다.");

    return "redirect:list";
  }
  
  @RequestMapping("/photoboard/detail")
  public String detail(@RequestParam("no") int no,
      Map<String,Object> map) throws Exception {
    
    PhotoBoard board = photoBoardService.get(no);
    List<Lesson> lessons = lessonService.list();
    
    map.put("photoboard", board);
    map.put("lessons", lessons);
    map.put("files", board.getPhotoFiles());
    return "/photoboard/detail.jsp";
  }
  
  @RequestMapping("/photoboard/list")
  public String list(Map<String,Object> map) throws Exception {
    
    List<PhotoBoard> photoBoards = photoBoardService.list(0, null);
    
    map.put("photoboards", photoBoards);
    return "/photoboard/list.jsp";
  }
  
  @RequestMapping("/photoboard/search")
  public String search(
      @RequestParam("lessonNo") int lessonNo,
      @RequestParam("searchWord") String keyword,
      Map<String,Object> map) throws Exception {
    String searchWord = null;
    if (keyword.length() > 0)
      searchWord = keyword;
    List<PhotoBoard> photoBoards = photoBoardService.list(lessonNo, searchWord);
    map.put("photoboard", photoBoards);
    return "/photoboard/search.jsp";
  }
  
  @RequestMapping("/photoboard/update")
  public String update(
      HttpServletRequest request,
      PhotoBoard photoBoard,
      @RequestParam("photoFile") Part[] photoFiles) throws Exception {
    this.uploadDir = 
        servletContext.getRealPath("/upload/photoboard");
    
    ArrayList<PhotoFile> files = new ArrayList<>();
    
    for (Part photo : photoFiles) {
      if (photo.getSize() == 0 || !photo.getName().equals("photoFile"))
        continue;

      String filename = UUID.randomUUID().toString();
      photo.write(uploadDir + "/" + filename);
      
      PhotoFile file = new PhotoFile();
      file.setFilePath(filename);
      file.setPhotoBoardNo(photoBoard.getNo());
      
      files.add(file);
    }
    photoBoard.setPhotoFiles(files);

    if (files.size() == 0) {
      throw new Exception("최소 한개 사진 파일을 등록해야 합니다.");
    } 
    photoBoardService.update(photoBoard);
    return "redirect:list";
  }
}
