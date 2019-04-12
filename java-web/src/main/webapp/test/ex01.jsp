<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>외부 라이브러리 사용</title>
</head>
<body>
  <h1>java-web-library.jar의 클래스 사용하기</h1>
  <jsp:useBean id="calc" class="bitcamp.Calculater" scope="page"/>
  10 + 20 = ${calc.plus(10, 20)}
</body>
</html>