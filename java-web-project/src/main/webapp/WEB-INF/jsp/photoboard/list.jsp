<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>사진 목록</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="${contextRootPath}/css/common.css">
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container">
  <h1>사진 목록</h1>
  <p>
    <a href='form' class="btn btn-success btn-sm">사진 추가</a>
  </p>
  <div class="bit-list">
  <table class="table table-hover">
    <thead>
    <tr>
      <th scope="col">번호</th>
      <th scope="col">제목</th>
      <th scope="col">등록일</th>
      <th scope="col">조회수</th>
      <th scope="col">수업 번호</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${photoboards}" var="board">
    <tr>
      <th scope="row">${board.no}</th>
      <td scope="row"><a href='${board.no}'>${board.title}</a></td>
      <td scope="row">${board.createdDate}</td>
      <td scope="row">${board.viewCount}</td>
      <td scope="row">${board.lessonNo}</td>
    </tr>
    </c:forEach>
    </tbody>
  </table>
  </div> <!-- bit-list -->
  <form action='search'>
    수업번호: <input type='text' name='lessonNo'> 검색어: <input type='text' name='searchWord'>
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
