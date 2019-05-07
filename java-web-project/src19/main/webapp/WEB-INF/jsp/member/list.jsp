<%@ page language="java" 
          contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"
          trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>회원 목록</title>
  <jsp:include page="../commonCss.jsp"/>
</head>
<body>
  <jsp:include page="../header.jsp" />
  <div class="container">
  <h1>회원 목록</h1>
  <p>
    <a href='form' class="btn btn-success btn-sm">회원 가입</a>
  </p>
  <table class="table table-hover">
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
      <td><a href='${member.no}'>${member.name}</a></td>
      <td>${member.email}</td>
      <td>${member.tel}</td>
      <td>${member.registeredDate}</td>
    </tr>
    </c:forEach>
  </table>
  <form action='search'>
    <input name='name'>
    <button class="btn btn-info btn-sm">검색</button>
  </form>
  
  <nav aria-label="목록 페이지 이동">
  <ul class="pagination justify-content-center" >
    <li class="page-item ${pageNo <= 1 ? 'disabled' : '' }  "><a class="page-link" 
        href="?pageNo=${pageNo - 1}&pageSize=${pageSize}">이전</a></li>
    <li class="page-item active"><span class="page-link">${pageNo}</span></li>
    <li class="page-item ${pageNo >= totalPage ? 'disabled' : '' }"><a class="page-link" 
        href="?pageNo=${pageNo + 1}&pageSize=${pageSize}">다음</a></li>
  </ul>
  </nav>
  </div> <!-- container -->
</body>
</html>