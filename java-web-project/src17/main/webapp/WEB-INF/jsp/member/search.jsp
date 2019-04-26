<%@ page language="java" 
          contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"
          trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>회원 검색</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <link rel="stylesheet" href="${contextRootPath}/css/common.css">
</head>
<body>
  <jsp:include page="../header.jsp" />
  <div class="container">
  <h1>회원 검색</h1>
  <table class="table table-hover">
    <thead>
    <tr>
      <th scope="col">번호</th>
      <th scope="col">이름</th>
      <th scope="col">이메일</th>
      <th scope="col">전화번호</th>
      <th scope="col">등록일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${members}" var="member">
    <tr>
      <td scope="row">${member.no}</td>
      <td scope="row"><a href='${member.no}'>${member.name}</a></td>
      <td scope="row">${member.email}</td>
      <td scope="row">${member.tel}</td>
      <td scope="row">${member.registeredDate}</td>
    </tr>
    </c:forEach>
    </tbody>
  </table>
    <a href='.' class="btn btn-primary btn-sm">목록</a>
    </div> <!-- container -->
</body>
</html>