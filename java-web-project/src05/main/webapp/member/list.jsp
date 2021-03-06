<%@ page import="com.eomcs.lms.domain.Member"%>
<%@ page import="java.util.List"%>
<%@ page language="java" 
          contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"
          trimDirectiveWhitespaces="true"%>
<%
List<Member> members = (List<Member>) request.getAttribute("members");
%>
<!DOCTYPE html>
<html>
<head>
  <title>회원 목록(JSP)</title>
</head>
<body>
  <jsp:include page="/header.jsp" />
  <h1>회원 목록(JSP)</h1>
  <p>
    <a href='add'>회원 가입</a>
  </p>
  <table border='1'>
    <tr>
      <th>번호</th>
      <th>이름</th>
      <th>이메일</th>
      <th>전화번호</th>
      <th>등록일</th>
    </tr>
    <%for (Member member : members) {%>
    <tr>
      <td><%=member.getNo()%></td>
      <td><a href='detail?no=<%=member.getNo()%>'><%=member.getName()%></a></td>
      <td><%=member.getEmail()%></td>
      <td><%=member.getTel()%></td>
      <td><%=member.getRegisteredDate()%></td>
    </tr>
    <%}%>
  </table>
  <form action='search'>
    <input name='name'>
    <button type='submit'>검색</button>
  </form>
  <a href='../index.html'>처음화면</a>
</body>
</html>