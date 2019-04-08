<%@page import="java.util.List"%>
<%@page import="com.eomcs.lms.domain.PhotoBoard"%>
<%@page import="com.eomcs.lms.domain.PhotoFile"%>
<%@page import="com.eomcs.lms.domain.Lesson"%>
<%@ page language="java" 
          contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"
          trimDirectiveWhitespaces="true"%>
<%
  PhotoBoard board = (PhotoBoard) request.getAttribute("photoboard");
%>
<!DOCTYPE html>
<html>
<head>
<title>사진 조회</title>
</head>
<%if (board == null) {%>
  <p>해당 사진 정보가 존재하지 않습니다.</p>
      <%return;
    } else {%>
<body>
  <jsp:include page="/header.jsp" />
  <h1>사진 조회</h1>
  <form action='update' method='post' enctype='multipart/form-data'>
    <table border='1'>
      <tr>
        <th>번호</th>
        <td><input type='text' name='no' value='<%=board.getNo()%>' readonly></td>
      </tr>

      <tr>
        <th>내용</th>
        <td><textarea name='title' rows='3' cols='50'><%=board.getTitle()%></textarea></td>
      </tr>
      <tr>
        <th>등록일</th>
        <td><%=board.getCreatedDate()%></td>
      </tr>
      <tr>
        <th>조회수</th>
        <td><%=board.getViewCount()%></td>
      </tr>
      <tr>
        <th>수업</th>
        <td><select name='lessonNo'>
            <%List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");
            for (Lesson lesson : lessons) {%>
            <option value='<%=lesson.getNo()%>'>
            <%=lesson.getTitle()%>(<%=lesson.getStartDate() %> ~ <%=lesson.getEndDate()%>)
            </option>
         <%} %>
        </select></td>
      </tr>
      <tr>
        <td colspan='2'>최소 한 개의 사진 파일을 등록해야 합니다.</td>
      </tr>
      <%for (int i = 0; i < 5; i++) {%>
      <tr>
      <th>사진<%=i + 1%></th>
      <td><input type='file' name='photo'></td>
      </tr>
      <%}%>
      <%if (board == null) {
    } else {%>
      <tr>
        <th>사진</th>
        <% List<PhotoFile> files = board.getPhotoFiles(); %>
        <%for (PhotoFile file : files) {%>
        <td><img src='../upload/photoboard/<%=file.getFilePath()%>'
          style='height: 80px'><br></td>
        <%} %>
      </tr>
    </table>
    <p>
      <a href='list'>목록</a> <a href='delete?no=<%=board.getNo()%>'>삭제</a>
      <button type='submit'>변경</button>
    </p>
    <%} %>
  <%} %>
  </form>
</body>
</html>



