<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Refresh" content="2;url=form">
<title>로그인 실패</title>
<link rel="stylesheet" 
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
        crossorigin="anonymous">
  <link rel="styleheet" href="css/common.css">
</head>
<body>
<div class="container">
  <jsp:include page="../header.jsp"/>
  <h1>로그인 실패</h1>
  <p>이메일 또는 암호가 맞지 않습니다.</p>
</div>
<jsp:include page="../javascript.jsp"/>
</body>
</html>