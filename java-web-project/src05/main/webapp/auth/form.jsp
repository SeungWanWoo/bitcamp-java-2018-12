<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String email = (String) request.getAttribute("email");
%>
<!DOCTYPE html>

<html>
<head><title>로그인(JSP)</title></head>
<body>
<jsp:include page="/header.jsp"/>
<h1>로그인(JSP)</h1>
<form action='login' method='post'>
<table border='1'>
<tr>
   <th>이메일</th>
   <td><input type='email' name='email' value='<%=email%>'></td>
</tr>
<tr>
   <th>암호</th>
   <td><input type='password' name='password'></td>
</tr>
</table>
<input type='checkbox' name='saveEmail'>이메일저장
<p>
   <button>로그인</button>
</p>
</form>
</body>
</html>