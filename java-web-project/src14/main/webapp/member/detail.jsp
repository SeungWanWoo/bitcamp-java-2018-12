<%@ page language="java"
          contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"
          trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextRootPath" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>회원 조회(JSP4)</title>
</head>
<body>
  <h1>회원 조회(JSP4)</h1>
  <form action='update' method='post' enctype='multipart/form-data'>
    <table border='1'>
      <tr>
        <th>번호</th>
        <td><input type='text' name='no' value='${member.no}' readonly></td>
      </tr>

      <tr>
        <th>이름</th>
        <td><input type='text' name='name' value='${member.name}'></td>
      </tr>
      <tr>
        <th>이메일</th>
        <td><input type='email' name='email' value='${member.email}'></td>
      </tr>
      <tr>
        <th>암호</th>
        <td><input type='password' name='password' value='${member.password}'></td>
      </tr>
      <tr>
        <th>사진</th>
        <td>
          <c:choose>
            <c:when test="${empty member.photo}">
             <img src='${contextRootPath}/images/default.jpeg' style='height:80px'>
            </c:when>
            <c:otherwise>
              <img src='${contextRootPath}/upload/member/${member.photo}' style='height:80px'>
            </c:otherwise>
          </c:choose>
        <input type='file' name='photoFile'>
        </td>
      <tr>
        <th>전화번호</th>
        <td><input type='text' name='tel' value='${member.tel}'></td>
      </tr>
      <tr>
        <th>가입일</th>
        <td>${member.registeredDate}</td>
      </tr>
    </table>
    <p>
      <a href='list'>목록</a> <a href='delete?no=${member.no}'>삭제</a>
      <button type='submit'>변경</button>
    </p>
  </form>
</body>
</html>


