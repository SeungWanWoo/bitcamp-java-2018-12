<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>회원 조회</title>
<jsp:include page="../commonCss.jsp"/>
</head>
<body>
  <jsp:include page="../header.jsp" />
  <div class="container">
    <h1>회원 조회</h1>
    <c:choose>
      <c:when test="${empty member}">
        <p>해당하는 회원이 존재하지 않습니다.</p>
      </c:when>
      <c:otherwise>
        <form action='update' method='post' enctype='multipart/form-data'>
          <div class="form-group row">
            <label for="no" class="col-sm-2 col-form-label">번호</label>
            <div class="col-sm-10">
              <input type="text" class="form-control-plaintext" id="no" name='no'
                value='${member.no}' readonly>
            </div>
          </div>
          <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label">이름</label>
            <div class="col-sm-10">
              <input class="form-control" id="name" name='name' value="${member.name}">
            </div>
          </div>
          <div class="form-group row">
            <label for="email" class="col-sm-2 col-form-label">이메일</label>
            <div class="col-sm-10">
              <input type='email' class="form-control" id="email" name='email'
                value="${member.email}">
            </div>
          </div>
          <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">암호</label>
            <div class="col-sm-10">
              <input type='password' class="form-control" id="password" name='password'
                value="${member.password}">
            </div>
          </div>
          <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">사진</label>
            <div class="col-sm-10">
            <c:if test="${empty member.photo}">
              <img src='${contextRootPath}/images/default.jpeg'>
            </c:if>
            <c:if test="${not empty member.photo}">
              <img src='${contextRootPath}/upload/member/${member.photo}'>
            </c:if>
              <input type='file' class="form-control" id="photoFile" name='photoFile'>
            </div>
          </div>
          <div class="form-group row">
            <label for="tel" class="col-sm-2 col-form-label">전화번호</label>
            <div class="col-sm-10">
              <input type='text' class="form-control" id="tel" name='tel'
                value="${member.tel}">
            </div>
          </div>
          <div class="form-group row">
            <label for="registeredDate" class="col-sm-2 col-form-label">가입일</label>
            <div class="col-sm-10">
              <input type='text' class="form-control" id="registeredDate" name='registeredDate'
                value="${member.registeredDate}" readonly>
            </div>
          </div>
           <div class="form-group row">
            <div class="col-sm-10">
              <a href='.' class="btn btn-primary btn-sm">목록</a> <a href='delete/${member.no}'
                class="btn btn-danger btn-sm">삭제</a>
              <button class="btn btn-warning btn-sm">변경</button>
            </div>
          </div>
        </form>
      </c:otherwise>
    </c:choose>
  </div>
  <!-- container -->
</body>
</html>


