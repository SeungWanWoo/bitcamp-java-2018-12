<%@page import="com.eomcs.lms.domain.PhotoBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<jsp:useBean scope="request" id="photoboard" type="java.util.List<PhotoBoard>"/>
<!DOCTYPE html>
<html>
<head>
<title>사진 목록 검색3</title>
</head>
<body>
  <jsp:include page="/header.jsp"/>
  <h1>사진 목록 검색3</h1>
  <table border='1'>
    <tr>
      <th>번호</th>
      <th>사진 제목</th>
      <th>등록일</th> 
      <th>조회수</th>
      <th>사진 번호</th>
    </tr>
    <p>[검색 결과]</p>
    <%for (PhotoBoard board : photoboard) {
          pageContext.setAttribute("board", board);%>
    <tr>
      <td>${board.no}</td>
      <td><a href='detail?no=${board.no}'>${board.title}</a></td>
      <td>${board.createdDate}</td>
      <td>${board.viewCount}</td>
      <td>${board.lessonNo}</td>
    </tr>
    <%} %>
      <a href='list'>목록</a>
  </table>
</body>
</html>
