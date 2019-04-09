<%@page import="com.eomcs.lms.domain.Member"%>
<%@ page language="java" 
          contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"
          trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>회원 검색3</title>
</head>
<body>
  <jsp:include page="/header.jsp"></jsp:include>
  <h1>회원 검색3</h1>
  <table border='1'>
    <tr>
      <th>번호</th>
      <th>이름</th>
      <th>이메일</th>
      <th>전화번호</th>
      <th>등록일</th>
    </tr>
    <jsp:useBean scope="request" id="members" type="java.util.List<Member>"/>
    <%for (Member member : members) {
          pageContext.setAttribute("member", member);%>
    <tr>
      <td>${member.no}</td>
      <td><a href='detail?no=${member.no}'>${member.name}</a></td>
      <td>${member.email}</td>
      <td>${member.tel}</td>
      <td>${member.registeredDate}</td>
    </tr>
    <%} %>
    <a href='list'>목록</a>
  </table>
</body>
</html>