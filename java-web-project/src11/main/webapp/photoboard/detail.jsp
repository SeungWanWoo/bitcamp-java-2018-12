<%@ page language="java" 
          contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"
          trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextRootPath" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>사진 조회3</title>
</head>
<body>

<jsp:include page="/header.jsp" />
<c:choose>
  <c:when test="${empty photoboard}">
    <p>해당 사진 정보가 존재하지 않습니다.</p>
  </c:when>
  <c:otherwise>
  <h1>사진 조회3</h1>
  <form action='update' method='post' enctype='multipart/form-data'>
    <table border='1'>
      <tr>
        <th>번호</th>
        <td><input type='text' name='no' value='${photoboard.no}' readonly></td>
      </tr>

      <tr>
        <th>내용</th>
        <td><textarea name='title' rows='3' cols='50'>${photoboard.title}</textarea></td>
      </tr>
      <tr>
        <th>등록일</th>
        <td>${photoboard.createdDate}</td>
      </tr>
      <tr>
        <th>조회수</th>
        <td>${photoboard.viewCount}</td>
      </tr>
      <tr>
        <th>수업</th>
        <td><select name='lessonNo'>
            <c:forEach items="${lessons}" var="lesson">
              <option value='${lesson.no}' ${board.lessonNo == lesson.no ? "selected" : ""}>
               ${lesson.title}(${lesson.startDate} ~ ${lesson.endDate})
            </option>
            </c:forEach>
        </select></td>
      </tr>
      <tr>
        <td colspan='2'>최소 한 개의 사진 파일을 등록해야 합니다.</td>
      </tr>
      <tr>
        <th>사진1</th>
        <td><input type='file' name='photo'></td>
      </tr>
      <tr>
        <th>사진2</th>
        <td><input type='file' name='photo'></td>
      </tr>
      <tr>
        <th>사진3</th>
        <td><input type='file' name='photo'></td>
      </tr>
      <tr>
        <th>사진4</th>
        <td><input type='file' name='photo'></td>
      </tr>
      <tr>
        <th>사진5</th>
        <td><input type='file' name='photo'></td>
      </tr>
      <tr>
        <th>사진</th>
        <c:forEach items="${files}" var="file">
        <tr>
          <td>
          <img src='${contextRootPath}/upload/photoboard/${file.filePath}' style='height: 80px'>
          </td>
        </tr>
        </c:forEach>
      </tr>
    </table>
    <p>
      <a href='list'>목록</a> <a href='delete?no=${photoboard.no}'>삭제</a>
      <button type='submit'>변경</button>
    </p>
    </form>
    </c:otherwise>
  </c:choose>
</body>
</html>



