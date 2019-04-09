<%@page import="java.util.List"%>
<%@page import="com.eomcs.lms.domain.PhotoBoard"%>
<%@page import="com.eomcs.lms.domain.PhotoFile"%>
<%@page import="com.eomcs.lms.domain.Lesson"%>
<%@ page language="java" 
          contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"
          trimDirectiveWhitespaces="true"%>

<jsp:useBean scope="request" id="photoboard" type="com.eomcs.lms.domain.PhotoBoard"/>
<jsp:useBean scope="request" id="lessons" type="java.util.List<Lesson>"/>

<!DOCTYPE html>
<html>
<head>
<title>사진 조회</title>
</head>
<%if (photoboard == null) {%>
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
        <td><input type='text' name='no' value='<%=photoboard.getNo()%>' readonly></td>
      </tr>

      <tr>
        <th>내용</th>
        <td><textarea name='title' rows='3' cols='50'><%=photoboard.getTitle()%></textarea></td>
      </tr>
      <tr>
        <th>등록일</th>
        <td><%=photoboard.getCreatedDate()%></td>
      </tr>
      <tr>
        <th>조회수</th>
        <td><%=photoboard.getViewCount()%></td>
      </tr>
      <tr>
        <th>수업</th>
        <td><select name='lessonNo'>
            <% for (Lesson lesson : lessons) {%>
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
      <%if (photoboard == null) {
    } else {%>
      <tr>
        <th>사진</th>
        <jsp:useBean scope="request" id="files" type="java.util.List<PhotoFile>"/>
        <%for (PhotoFile file : files) {%>
        <td><img src='../upload/photoboard/<%=file.getFilePath()%>'
          style='height: 80px'><br></td>
        <%} %>
      </tr>
    </table>
    <p>
      <a href='list'>목록</a> <a href='delete?no=<%=photoboard.getNo()%>'>삭제</a>
      <button type='submit'>변경</button>
    </p>
    <%} %>
  <%} %>
  </form>
</body>
</html>



