<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>사진 추가</title>
<link rel="stylesheet"
  href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">
<link rel="stylesheet" href="${contextRootPath}/css/common.css">
</head>
<body>
  <jsp:include page="../header.jsp" />
  <div class="container">
    <h1>사진 추가</h1>
    <form action='add' method='post' enctype='multipart/form-data'>
      <div class="form-group row">
        <label for="lessonNo" class="col-sm-2 col-form-label">수업</label>
        <div class="col-sm-10">
          <select id="lessonNo" name="lessonNo" class="btn btn-secondary dropdown-toggle"
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <c:forEach items="${lessons}" var="lesson">
              <option class="dropdown-item" value='${lesson.no}'
                ${board.lessonNo == lesson.no ? "selected" : ""}>
                ${lesson.title}(${lesson.startDate} ~ ${lesson.endDate})</option>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group row">
        <label for="title" class="col-sm-2 col-form-label">사진 제목</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="title" name='title'>
        </div>
      </div>
      <div class="form-group row">
        <div class="col-sm-10">
          <input type="text" readonly class="form-control-plaintext" id="viewCount"
            value="최소 한 개의 사진 파일을 등록해야 합니다.">
        </div>
      </div>
      <div class="form-group row">
        <label for="photoFile" class="col-sm-2 col-form-label">사진1</label>
        <div class="col-sm-10">
          <input type="file" id="photoFile">
        </div>
      </div>
      <div class="form-group row">
        <label for="photoFile" class="col-sm-2 col-form-label">사진2</label>
        <div class="col-sm-10">
          <input type="file" id="photoFile">
        </div>
      </div>
      <div class="form-group row">
        <label for="photoFile" class="col-sm-2 col-form-label">사진3</label>
        <div class="col-sm-10">
          <input type="file" id="photoFile">
        </div>
      </div>
      <div class="form-group row">
        <label for="photoFile" class="col-sm-2 col-form-label">사진4</label>
        <div class="col-sm-10">
          <input type="file" id="photoFile">
        </div>
      </div>
      <div class="form-group row">
        <label for="photoFile" class="col-sm-2 col-form-label">사진5</label>
        <div class="col-sm-10">
          <input type="file" id="photoFile">
        </div>
      </div>
      <div class="form-group row">
        <div class="col-sm-10">
          <button class="btn btn-success btn-sm">등록</button>
          <a href='.' class="btn btn-primary btn-sm">목록</a>
        </div>
      </div>
    </form>
  </div>
  <jsp:include page="../javascript.jsp" />
</body>
</html>
