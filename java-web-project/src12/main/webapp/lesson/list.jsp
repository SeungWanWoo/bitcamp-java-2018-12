<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>수업 목록(JSP4)</title>
</head>
<body>
  <jsp:include page="/header.jsp" />
  <h1>수업 목록(JSP4)</h1>
  <p>
    <a href='add'>신규 수업</a>
  </p>
  <table border='1'>
    <tr>
      <th>번호</th>
      <th>수업 제목</th>
      <th>시작일 ~ 종료일</th>
      <th>총 시간</th>
    </tr>
    <c:forEach items="${lessons}" var="lesson">
    <tr>
      <td>${lesson.no}</td>
      <td><a href='detail?no=${lesson.no}'>${lesson.title}</a></td>
      <td>${lesson.startDate}~ ${lesson.endDate}</td>
      <td>${lesson.totalHours}</td>
    </tr>
    </c:forEach>
  </table>
  <a href='../../index.html'>처음화면</a>
</body>
</html>
