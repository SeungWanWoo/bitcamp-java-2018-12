package com.eomcs.lms.handler;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;
import com.eomcs.lms.context.RequestMapping;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.lms.service.PhotoBoardService;

@Component
public class PhotoBoardCommand {
  PhotoBoardService photoBoardService;

  public PhotoBoardCommand(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @RequestMapping("/photoboard/list")
  public void list(ServletRequest request, ServletResponse response) throws Exception  {
    PrintWriter out = response.getWriter();
    List<PhotoBoard> photoBoards = photoBoardService.list(0, null);

    out.println("<html><head><title>사진 목록</title></head>");
    out.println("<body><h1>사진 목록</h1>");
    out.println("<p><a href='form'>사진 추가</a></p>");
    out.println("<table border='1'>");
    out.println("<tr> <th>번호</th> <th>제목</th> <th>등록일</th> <th>조회수</th> "
        + "<th>수업 번호</th> </tr>");
    for (PhotoBoard photoBoard : photoBoards) {
      out.println(String.format("<tr><td>%d</td> "
          + "<td><a href='detail?no=%1$d'>%s</a></td> "
          + "<td>%s</td> "
          + "<td>%d</td> "
          + "<td>%d</td> ",
          photoBoard.getNo(), photoBoard.getTitle(), 
          photoBoard.getCreatedDate(), photoBoard.getViewCount(),
          photoBoard.getLessonNo()));
    }
    out.println("</table><form action='search'>");
    out.println("수업번호: <input type='number' name='lessonNo'>");
    out.println("검색어: <input type='text' name='searchWord'>");
    out.println("<button type='submit'>검색</button>");
    out.println("</form>");
    out.println("</body></html>");
  }

  @RequestMapping("/photoboard/add")
  public void add(ServletRequest request, ServletResponse response) throws Exception {
    PhotoBoard board = new PhotoBoard();
    board.setTitle(request.getParameter("title"));
    board.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));

    PrintWriter out = response.getWriter();

    ArrayList<PhotoFile> files = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      String filePath = request.getParameter(String.format("filePath%d", i));
      if (filePath.length() == 0) {
        out.println("<p>최소 한 개의 사진 파일을 등록해야 합니다.</p>");
        continue;
      } else {
        PhotoFile file = new PhotoFile();
        file.setFilePath(filePath);

        files.add(file);
      }
    }
    board.setPhotoFiles(files);
    if (files.size() == 0) {
      out.println("<p>최소 한개 사진 파일을 등록해야합니다.</p>");
    } else { 
      photoBoardService.add(board);
    }
    out.println("<html><head>"
        + "<title>사진 등록</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>사진 등록</h1>");
    out.println("<p>저장하였습니다.</p>");
    out.println("</body></html>");
  }

  @RequestMapping("/photoboard/detail")
  public void detail(ServletRequest request, ServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));

    PhotoBoard board = photoBoardService.get(no);
    PrintWriter out = response.getWriter();

    if (board == null) {
      out.println("<p>해당 사진 정보가 존재하지 않습니다.</p>");
      return;
    }

    out.println("<html><head><title>사진 조회</title></head>");
    out.println("<body><h1>사진 조회</h1>");
    out.println("<form action='update'>");
    out.println("<table border='1'>");
    out.println(
        String.format("<tr>"
            + "<th>번호</th>"
            + "<td><input type='text' name='no' value='%d' readonly></td>"
            + "</tr>\n", no));

    out.println(String.format("<tr> <th>내용</th> "
        + "<td><textarea name='title' rows='3' cols='50'>%s</textarea></td>"
        + "</tr>", board.getTitle()));

    out.println(String.format("<tr> <th>등록일</th> <td>%s</td> </tr>", board.getCreatedDate()));

    out.println(String.format("<tr> <th>조회수</th> <td>%d</td></tr>", board.getViewCount()));

    out.println(String.format("<tr> <th>수업 정보</th> "
        + "<td>%s(%s ~ %s)</td></tr>", board.getLesson().getTitle(), board.getLesson().getStartDate()
        ,board.getLesson().getEndDate()));

    out.println("<tr>");
    out.println(String.format("<input type='hidden' name='lessonNo' value='%d'", 
        board.getLesson().getNo()));
    out.println("</tr>");
    out.println("<tr> <td colspan='2'>최소 한 개의 사진 파일을 등록해야 합니다.</td></tr>");

    List<PhotoFile> files = board.getPhotoFiles();
    out.println(String.format("<tr> <th>사진 파일</th>"));
    out.println("   <td>");
    for (int i = 0; i < 5; i++) {
      if (i < files.size()) {
        PhotoFile file = files.get(i);
        out.println(String.format("<input type='text' name='filePath%d' value=%s><br>\n", 
            i, file.getFilePath()));
      } else
        out.println(String.format("<input type='text' name='filePath%d'><br>\n", i));
    }
    out.println("</td></tr>");
    out.println("</table>");

    out.println("<p><a href='list'>목록</a>"
        + " <a href='delete?no=" + board.getNo() + "'>삭제</a>"
        + " <button type='submit'>변경</button>"
        + "</p>"); // p = 한 문단
    out.println("</form>");
    out.println("</body></html>");
  }

  @RequestMapping("/photoboard/update")
  public void update(ServletRequest request, ServletResponse response) throws Exception {
    PhotoBoard board = new PhotoBoard();
    PrintWriter out = response.getWriter();

    board.setNo(Integer.parseInt(request.getParameter("no")));
    board.setTitle(request.getParameter("title"));

    ArrayList<PhotoFile> photoFiles = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      String filePath = request.getParameter(String.format("filePath%d", i));
      if (filePath.length() == 0) {
        continue;
      } 
      PhotoFile file = new PhotoFile();
      file.setFilePath(filePath);
      file.setPhotoBoardNo(board.getNo());
      photoFiles.add(file);
    }

    board.setPhotoFiles(photoFiles);
    photoBoardService.update(board);

    out.println("<html><head>"
        + "<title>사진 내용 변경</title>"
        + "<meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>사진 내용 변경</h1>");
    out.println("<p>변경하였습니다.</p>");
    out.println("</body></html>");
  }

  @RequestMapping("/photoboard/delete")
  public void delete(ServletRequest request, ServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));

    PrintWriter out = response.getWriter();
    out.println("<html><head>"
        + "<title>수업 사진 삭제</title>"
        + "   <meta http-equiv='Refresh' content='1;url=list'>"
        + "</head>");
    out.println("<body><h1>수업 사진 삭제</h1>");
    out.println("</body></html>");
    if (photoBoardService.delete(no) == 0) {
      out.println("<p>해당 번호의 사진이 없습니다.</p>");
    } else {
      out.println("<p>삭제했습니다.</p>");
    }
    out.println("</body></html>");
  }

  @RequestMapping("/photoboard/search")
  public void search(ServletRequest request, ServletResponse response) throws Exception  {
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head><title>사진 목록 검색</title></head>");
    out.println("<body>");
    out.println("<h1>사진 목록 검색</h1>");

    int lessonNo = 0;
    try {
      lessonNo = Integer.parseInt(request.getParameter("lessonNo"));
    } catch (Exception e) {// 수업 번호를 입력하지 않거나 정상 입력이 아닌 경우는 무시한다.
    }
    String searchWord = null;
    try {
      String keyword = request.getParameter("searchWord");
      if (keyword.length() != 0) {
        searchWord = keyword;
      }
    } catch (Exception e) { // 사용자가 검색어를 입력하지 않았으면 무시한다.
    }

    List<PhotoBoard> photoBoards = photoBoardService.list(lessonNo, searchWord);
    out.println("<table border='1'>");
    out.println("<tr> <th>번호</th> <th>사진 제목</th> <th>등록일</th> <th>조회수</th> "
        + "<th>사진 번호</th> </tr>");

    out.println("[검색 결과]");
    for (PhotoBoard photoBoard : photoBoards) {
      out.println(
          String.format(
              "<tr><td>%d</td> "
                  +"<td><a href='detail?no=%1$d'>%s</a></td> "
                  + "<td>%s</td> "
                  + "<td>%s</td> "
                  + "<td>%s</td> ", 
                  photoBoard.getNo(), photoBoard.getTitle(), 
                  photoBoard.getCreatedDate(), photoBoard.getViewCount(),
                  photoBoard.getLessonNo()));
    }
    out.println("<a href='list'>목록</a>");
    out.println("</table></body></html>");
  }

  @RequestMapping("/photoboard/form")
  public void form(ServletRequest request, ServletResponse response) throws Exception {
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head><title>사진 추가</title></head>");
    out.println("<body>");
    out.println("<h1>사진 추가</h1>");
    out.println("<form action='add'>");
    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("   <th>사진 제목</th>");
    out.println("   <td><input name='title'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("   <th>수업</th>");
    out.println("   <td><input type='number' name='lessonNo'></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("   <th><td colspan='2'>최소 한 개의 사진 파일을 등록해야 합니다.</td></tr>");
    out.println("<tr>");
    out.println("<th rowspan='5'>사진 이름</th>");
    for (int i = 0; i < 5; i++) {
      out.println(" <td><input type='text' name='filePath" + i +"'></td>");
      out.println("</tr>");
    }
    out.println("</table>");
    out.println("<p>");
    out.println("<button type='submit'>등록</button>");
    out.println("<a href='list'>목록</a>");
    out.println("</p>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
