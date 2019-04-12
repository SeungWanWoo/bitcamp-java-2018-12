<%@ page language="java" 
          contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"
          trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>회원 목록(JSP4)</title>
</head>
<body>
  <jsp:include page="/header.jsp" />
  <h1>회원 목록(JSP4)</h1>
  <p>
    <a href='form'>회원 가입</a>
  </p>
  <table border='1'>
    <tr>
      <th>번호</th>
      <th>이름</th>
      <th>이메일</th>
      <th>전화번호</th>
      <th>등록일</th>
    </tr>
    <c:forEach items="${members}" var="member">
    <tr>
      <td>${member.no}</td>
      <td><a href='detail?no=${member.no}'>${member.name}</a></td>
      <td>${member.email}</td>
      <td>${member.tel}</td>
      <td>${member.registeredDate}</td>
    </tr>
    </c:forEach>
  </table>
  <form action='search'>
    <input name='name'>
    <button type='submit'>검색</button>
  </form>
  <a href='../../index.html'>처음화면</a>
</body>
</html>