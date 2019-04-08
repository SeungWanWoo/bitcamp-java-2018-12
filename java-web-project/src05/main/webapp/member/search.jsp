<%@page import="java.util.List"%>
<%@page import="com.eomcs.lms.domain.Member"%>
<%@ page language="java" 
          contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"
          trimDirectiveWhitespaces="true"%>
<%
  List<Member> members = (List<Member>) request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<title>회원 검색</title>
</head>
<body>
  <jsp:include page="/header.jsp"></jsp:include>
  <h1>회원 검색</h1>
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
      <td><%=member.getNo() %></td>
      <td><a href='detail?no=<%=member.getNo() %>'><%=member.getName()%></a></td>
      <td><%=member.getEmail() %></td>
      <td><%=member.getTel() %></td>
      <td><%=member.getRegisteredDate() %></td>
    </tr>
    <%} %>
    <a href='list'>목록</a>
  </table>
</body>
</html>