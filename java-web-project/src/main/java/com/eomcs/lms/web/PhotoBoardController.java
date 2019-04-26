package com.eomcs.lms.web;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.LessonService;
import com.eomcs.lms.service.PhotoBoardService;

@Controller
@RequestMapping("/photoboard")
public class PhotoBoardController {
  String uploadDir;

  @Autowired PhotoBoardService photoBoardService;
  @Autowired LessonService lessonService;
  @Autowired ServletContext servletContext;
  
  @PostMapping("add")
  public String add(
      HttpServletRequest request,
      PhotoBoard board,
      Part[] photoFile) throws Exception {
    this.uploadDir = servletContext.getRealPath("/upload/photoboard");

    ArrayList<PhotoFile> files = new ArrayList<>();
    for (Part photo : photoFile) {
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
      throw new RuntimeException("사진 또는 파일을 등록할 수업을 선택하세요.");
      
    } else if (files.size() == 0) {
      throw new RuntimeException("최소 한개 사진 파일을 등록해야 합니다.");
      
    } else if (board.getTitle().length() == 0) {
      throw new RuntimeException("사진 게시물의 제목을 입력해주세요.");
    } else {
      photoBoardService.add(board);
      return "redirect:.";
    }
  }
  
  @GetMapping("form")
  public String form(Model model) {
    List<Lesson> lessons = lessonService.list(0, 3);
    model.addAttribute("lessons", lessons);
    
    return "photoboard/form";
  }
  
  @GetMapping("delete/{no}")
  public String delete(@PathVariable int no) throws Exception {

    if (photoBoardService.delete(no) == 0) 
      throw new Exception("해당 번호의 사진이 존재하지 않습니다.");

    return "redirect:../";
  }
  
  @GetMapping("{no}")
  public String detail(@PathVariable int no, Model model) throws Exception {
    
    PhotoBoard board = photoBoardService.get(no);
    List<Lesson> lessons = lessonService.list(0, 0);
    
    model.addAttribute("photoboard", board);
    model.addAttribute("lessons", lessons);
    model.addAttribute("files", board.getPhotoFiles());
    
    return "photoboard/detail";
  }
  
  @GetMapping
  public String list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="3") int pageSize,
      Model model) {
    
    if (pageSize < 3 || pageSize > 8)
      pageSize = 3;
    
    int rowCount = photoBoardService.size();
    int totalPage = rowCount / pageSize;
    
    if (rowCount % pageSize > 0)
      totalPage++;
    
    if (pageNo < 1)
      pageNo = 1;
    else if (pageNo > totalPage)
      pageNo = totalPage;
    
    List<PhotoBoard> photoBoards = photoBoardService.list(0, null, pageNo, pageSize);
    model.addAttribute("photoboards", photoBoards);
    model.addAttribute("pageNo", pageNo);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("totalPage", totalPage);
    
    return "photoboard/list";
  }
  
  @GetMapping("search")
  public void search(
      String lessonNo, 
      String searchWord, Model model) {
    
    int num = 0;
    if (!lessonNo.equals("")) {
      num = Integer.parseInt(lessonNo);
    }
    String sWord = null;
    if (searchWord.length() > 0)
      sWord = searchWord;
    
    List<PhotoBoard> photoBoards = photoBoardService.list(num, sWord, 1, 3);
    model.addAttribute("photoboard", photoBoards);
    
  }
  
  @PostMapping("update")
  public String update(
      HttpServletRequest request,
      PhotoBoard photoBoard,
      Part[] photoFile) throws Exception {
    this.uploadDir = 
        servletContext.getRealPath("/upload/photoboard");
    
    ArrayList<PhotoFile> files = new ArrayList<>();
    
    for (Part photo : photoFile) {
      if (photo.getSize() == 0 || !photo.getName().equals("photoFile"))
        continue;

      String filename = UUID.randomUUID().toString();
      photo.write(uploadDir + "/" + filename);
      System.out.println(photoBoard.getLessonNo());
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
    return "redirect:.";
  }
  
}
