<%@page import="com.eomcs.lms.domain.PhotoBoard"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%
  List<PhotoBoard> boards = (List<PhotoBoard>) request.getAttribute("photoboards");
%>
<!DOCTYPE html>
<html>
<head>
<title>사진 목록</title>
</head>
<body>
  <jsp:include page="/header.jsp" />
  <h1>사진 목록</h1>
  <p>
    <a href='add'>사진 추가</a>
  </p>
  <table border='1'>
    <tr>
      <th>번호</th>
      <th>제목</th>
      <th>등록일</th>
      <th>조회수</th>
      <th>수업 번호</th>
    </tr>
    <%for (PhotoBoard board : boards) {%>
    <tr>
      <td><%=board.getNo()%></td>
      <td><a href='detail?no=<%=board.getNo()%>'><%=board.getTitle() %></a></td>
      <td><%=board.getCreatedDate()%></td>
      <td><%=board.getViewCount()%></td>
      <td><%=board.getLessonNo()%></td>
    </tr>
    <%}%>
  </table>
  <form action='search'>
    수업번호: <input type='number' name='lessonNo'> 검색어: <input type='text' name='searchWord'>
    <button type='submit'>검색</button>
  </form>
  <a href='../index.html'>처음화면</a>
</body>
</html>
