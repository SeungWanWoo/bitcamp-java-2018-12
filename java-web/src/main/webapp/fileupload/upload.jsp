<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>업로드 데이터</title>
</head>
<body>

  <h1>업로드 데이터</h1>
  <%
    request.setCharacterEncoding("UTF-8");
    Collection<Part> parts = request.getParts();
    for (Part part : parts) {
      if (part.getContentType() == null) {
        out.println(
            String.format("%s=%s<br>\n", part.getName(), request.getParameter(part.getName())));
      } else {
        out.println(String.format("%s=%s<br>\n", part.getName(), part.getSubmittedFileName()));
      }
    }
  %>
</body>
</html>