<%@ page language="java"
          contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"
          trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>신규 수업</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
      crossorigin="anonymous">
<link rel="stylesheet" href="${contextRootPath}/css/common.css">
</head>
<body>
  <jsp:include page="../header.jsp" />
  <div class="container">
  <h1>신규 수업</h1>
  <form action='add' method='post'>
  
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
      <button class="btn btn-success btn-sm">등록</button>
      <a href='.' class="btn btn-primary btn-sm">목록</a>
    </div>
  </div>
  </form>
  </div> <!-- container -->
  <jsp:include page="../javascript.jsp"/>
</body>
</html>