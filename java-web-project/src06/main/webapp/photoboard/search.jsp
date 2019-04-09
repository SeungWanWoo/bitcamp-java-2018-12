<%@page import="com.eomcs.lms.domain.PhotoBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<jsp:useBean scope="request" id="boards" type="java.util.List<PhotoBoard>"/>
<!DOCTYPE html>
<html>
<head>
<title>사진 목록 검색</title>
</head>
<body>
  <jsp:include page="/header.jsp"/>
  <h1>사진 목록 검색</h1>
  <table border='1'>
    <tr>
      <th>번호</th>
      <th>사진 제목</th>
      <th>등록일</th> 
      <th>조회수</th>
      <th>사진 번호</th>
    </tr>
    <p>[검색 결과]</p>
    <%for (PhotoBoard board : boards) {%>
    <tr>
      <td><%=board.getNo()%></td>
      <td><a href='detail?no=<%=board.getNo()%>'><%=board.getTitle()%></a></td>
      <td><%=board.getCreatedDate()%></td>
      <td><%=board.getViewCount() %></td>
      <td><%=board.getLessonNo() %></td>
    </tr>
    <%} %>
      <a href='list'>목록</a>
  </table>
</body>
</html>
