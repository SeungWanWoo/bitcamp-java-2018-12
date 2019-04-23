<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>사진 추가</title>
</head>
<body>
  <jsp:include page="../header.jsp" />
  <h1>사진 추가</h1>
  <form action='add' method='post' enctype='multipart/form-data'>
    <table border='1'>
      <tr>
        <th>수업</th>
        <td><select name='lessonNo'>
            <option value='0'>수업을 선택하세요</option>
            <c:forEach items="${lessons}" var="lesson">
              <option value='${lesson.no}'>${lesson.title}</option>
            </c:forEach>
        </select></td>
      </tr>
      <tr>
        <th>사진 제목</th>
        <td><input type='text' name='title'></td>
      </tr>
      <tr>
        <th>
        <td colspan='2'>최소 한 개의 사진 파일을 등록해야 합니다.</td>
      </tr>
      <tr>
        <th>사진1</th>
        <td><input type='file' name='photoFile'></td>
      </tr>
      <tr>
        <th>사진2</th>
        <td><input type='file' name='photoFile'></td>
      </tr>
      <tr>
        <th>사진3</th>
        <td><input type='file' name='photoFile'></td>
      </tr>
      <tr>
        <th>사진4</th>
        <td><input type='file' name='photoFile'></td>
      </tr>
      <tr>
        <th>사진5</th>
        <td><input type='file' name='photoFile'></td>
      </tr>
    </table>
    <p>
      <button type='submit'>등록</button>
      <a href='.'>목록</a>
    </p>
  </form>
</body>
</html>
