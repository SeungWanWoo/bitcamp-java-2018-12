<%@page import="com.eomcs.lms.domain.PhotoBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>사진 목록 검색(JSP4)</title>
</head>
<body>
  <jsp:include page="/header.jsp"/>
  <h1>사진 목록 검색(JSP4)</h1>
  <table border='1'>
    <tr>
      <th>번호</th>
      <th>사진 제목</th>
      <th>등록일</th> 
      <th>조회수</th>
      <th>사진 번호</th>
    </tr>
    <p>[검색 결과]</p>
    <c:forEach items="${photoboard}" var="board">
    <tr>
      <td>${board.no}</td>
      <td><a href='detail?no=${board.no}'>${board.title}</a></td>
      <td>${board.createdDate}</td>
      <td>${board.viewCount}</td>
      <td>${board.lessonNo}</td>
    </tr>
    </c:forEach>
      <a href='list'>목록</a>
  </table>
</body>
</html>
