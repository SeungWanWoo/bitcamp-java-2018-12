<%@page import="com.eomcs.lms.domain.PhotoFile"%>
<%@page import="com.eomcs.lms.domain.Lesson"%>
<%@ page language="java" 
          contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"
          trimDirectiveWhitespaces="true"%>

<jsp:useBean scope="request" id="photoboard" type="com.eomcs.lms.domain.PhotoBoard"/>
<jsp:useBean scope="request" id="lessons" type="java.util.List<Lesson>"/>
<jsp:useBean scope="request" id="files" type="java.util.List<PhotoFile>"/>

<!DOCTYPE html>
<html>
<head>
<title>사진 조회3</title>
</head>
<%if (photoboard == null) {%>
  <p>해당 사진 정보가 존재하지 않습니다.</p>
      <%return;
    } else {%>
<body>
  <jsp:include page="/header.jsp" />
  <h1>사진 조회3</h1>
  <form action='update' method='post' enctype='multipart/form-data'>
    <table border='1'>
      <tr>
        <th>번호</th>
        <td><input type='text' name='no' value='${photoboard.no}' readonly></td>
      </tr>

      <tr>
        <th>내용</th>
        <td><textarea name='title' rows='3' cols='50'>${photoboard.title}</textarea></td>
      </tr>
      <tr>
        <th>등록일</th>
        <td>${photoboard.createdDate}</td>
      </tr>
      <tr>
        <th>조회수</th>
        <td>${photoboard.viewCount}</td>
      </tr>
      <tr>
        <th>수업</th>
        <td><select name='lessonNo'>
            <% for (Lesson lesson : lessons) {
                pageContext.setAttribute("lesson", lesson);%>
            <option value='${lesson.no}' ${board.lessonNo == lesson.no ? "selected" : ""}>
             ${lesson.title}(${lesson.startDate} ~ ${lesson.endDate})
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
      <%} %>
      <tr>
        <th>사진</th>
        <td>
        <%for (PhotoFile file : files) {
          pageContext.setAttribute("file", file);%>
        <img src='../upload/photoboard/${file.filePath}' style='height: 80px'>
         </td>
        <%}%>
      </tr>
    </table>
    <p>
      <a href='list'>목록</a> <a href='delete?no=${photoboard.no}'>삭제</a>
      <button type='submit'>변경</button>
    </p>
  </form>
  <%} %>
</body>
</html>



