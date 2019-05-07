<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>사진 목록 검색</title>
<jsp:include page="../commonCss.jsp"/>
</head>
<body>
  <jsp:include page="../header.jsp" />
  <div class="container">
    <h1>사진 목록 검색</h1>
    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">사진 제목</th>
          <th scope="col">등록일</th>
          <th scope="col">조회수</th>
          <th scope="col">사진 번호</th>
        </tr>
      </thead>
      <tbody>
        <p class="btn btn-primary btn-sm">검색 결과</p>
        <c:forEach items="${photoboard}" var="board">
          <tr>
            <td scope="row">${board.no}</td>
            <td scope="row"><a href='${board.no}'>${board.title}</a></td>
            <td scope="row">${board.createdDate}</td>
            <td scope="row">${board.viewCount}</td>
            <td scope="row">${board.lessonNo}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <a href='.' class="btn btn-primary btn-sm">목록</a>
  </div>
</body>
</html>
