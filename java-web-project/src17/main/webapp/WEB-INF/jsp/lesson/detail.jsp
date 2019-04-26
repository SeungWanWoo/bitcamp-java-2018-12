<%@ page language="java" 
          contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"
          trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>수업 조회</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="${contextRootPath}/css/common.css">
</head>
<body>
  <jsp:include page="../header.jsp" />
  
  <div class="container">
  <h1>수업 조회</h1>
  <form action='update' method='post'>
  <div class="form-group row">
    <label for="no" class="col-sm-2 col-form-label">번호</label>
    <div class="col-sm-10">
      <input type="text" class="form-control-plaintext" id="no" 
              name='no' value='${lesson.no}' readonly>
    </div>
  </div>
  <div class="form-group row">
    <label for="title" class="col-sm-2 col-form-label">수업명</label>
    <div class="col-sm-10">
      <textarea class="form-control" id="title" 
                 name='title'>${lesson.title}</textarea>
    </div>
  </div>
  <div class="form-group row">
    <label for="contents" class="col-sm-2 col-form-label">설명</label>
    <div class="col-sm-10">
      <textarea class="form-control" id="contents" 
                 name='contents'>${lesson.contents}</textarea>
    </div>
  </div>
  <div class="form-group row">
    <label for="startDate" class="col-sm-2 col-form-label">시작일</label>
    <div class="col-sm-10">
      <input type="date" class="form-control" id="startDate" 
                 name='startDate' value='${lesson.startDate}'>
    </div>
  </div>
  <div class="form-group row">
    <label for="endDate" class="col-sm-2 col-form-label">종료일</label>
    <div class="col-sm-10">
      <input type="date" class="form-control" id="endDate" 
                 name='endDate' value='${lesson.endDate}'>
    </div>
  </div>
  <div class="form-group row">
    <label for="totalHours" class="col-sm-2 col-form-label">총수업시간</label>
    <div class="col-sm-10">
      <input type="number" class="form-control" id="totalHours" 
                 name='totalHours' value='${lesson.totalHours}'>
    </div>
  </div>
  <div class="form-group row">
    <label for="dayHours" class="col-sm-2 col-form-label">일수업시간</label>
    <div class="col-sm-10">
      <input type="number" class="form-control" id="dayHours" 
                 name='dayHours' value='${lesson.dayHours}'>
    </div>
  </div>
  <div class="form-group row">
    <div class="col-sm-10">
      <a href='.' class="btn btn-primary btn-sm">목록</a>
      <a href='delete/${lesson.no}' class="btn btn-danger btn-sm">삭제</a>
      <button class="btn btn-warning btn-sm">변경</button>
    </div>
  </div>
  </form>
  </div>
  
  
  <jsp:include page="../javascript.jsp"/>
</body>
</html>

