<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>사진 조회</title>
<link rel="stylesheet"
  href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">
<link rel="stylesheet" href="${contextRootPath}/css/common.css">
</head>
<body>
  <jsp:include page="../header.jsp" />
  <div class="container">
    <h1>사진 조회</h1>
    <c:choose>
      <c:when test="${empty photoboard}">
        <p>해당 사진 정보가 존재하지 않습니다.</p>
      </c:when>
      <c:otherwise>
        <form action='update' method='post' enctype='multipart/form-data'>
          <div class="form-group row">
            <label for="no" class="col-sm-2 col-form-label">번호</label>
            <div class="col-sm-10">
              <input type="text" class="form-control-plaintext" id="no" name='no'
                value='${photoboard.no}' readonly>
            </div>
          </div>
          <div class="form-group row">
            <label for="title" class="col-sm-2 col-form-label">내용</label>
            <div class="col-sm-10">
              <textarea class="form-control" id="title" name='title' rows='5'>${photoboard.title}</textarea>
            </div>
          </div>
          <div class="form-group row">
            <label for="createdDate" class="col-sm-2 col-form-label">작성일</label>
            <div class="col-sm-10">
              <input type="text" readonly class="form-control-plaintext" id="createdDate"
                value="${photoboard.createdDate}">
            </div>
          </div>
          <div class="form-group row">
            <label for="viewCount" class="col-sm-2 col-form-label">조회수</label>
            <div class="col-sm-10">
              <input type="text" readonly class="form-control-plaintext" id="viewCount"
                value="${photoboard.viewCount}">
            </div>
          </div>
          <select name='lessonNo'>
            <c:forEach items="${lessons}" var="lesson">
              <option value='${lesson.no}' ${board.lessonNo == lesson.no ? "selected" : ""}>
               ${lesson.title}(${lesson.startDate} ~ ${lesson.endDate})
            </option>
            </c:forEach>
        </select>
          <div class="form-group row">
            <div class="col-sm-10">
              <input type="text" readonly class="form-control-plaintext" id="viewCount"
                value="최소 한 개의 사진 파일을 등록해야 합니다.">
            </div>
          </div>
          <table class="table">
            <thead>
              <tr>
                <th scope="row">사진1</th>
                <td scope="row"><input type='file' name='photoFile'></td>
              </tr>
              <tr>
                <th scope="row">사진2</th>
                <td scope="row"><input type='file' name='photoFile'></td>
              </tr>
              <tr>
                <th scope="row">사진3</th>
                <td scope="row"><input type='file' name='photoFile'></td>
              </tr>
              <tr>
                <th scope="row">사진4</th>
                <td scope="row"><input type='file' name='photoFile'></td>
              </tr>
              <tr>
                <th scope="row">사진5</th>
                <td scope="row"><input type='file' name='photoFile'></td>
              </tr>
            </thead>
            <tr>
              <th>사진</th>
              <c:forEach items="${files}" var="file">
                <tr>
                  <td><img src='${contextRootPath}/upload/photoboard/${file.filePath}'
                    style='height: 80px'><br></td>
                </tr>
              </c:forEach>
            </tr>
          </table>
          <div class="form-group row">
            <div class="col-sm-10">
              <a href='.' class="btn btn-primary btn-sm">목록</a> <a href='delete/${photoboard.no}'
                class="btn btn-danger btn-sm">삭제</a>
              <button class="btn btn-warning btn-sm">변경</button>
            </div>
          </div>
        </form>
      </c:otherwise>
    </c:choose>
  </div>
  <!-- container -->
  <jsp:include page="../javascript.jsp" />
</body>
</html>



