<%@page import="java.util.UUID"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
  trimDirectiveWhitespaces="true"
%>
<%
  request.setCharacterEncoding("UTF-8");
  Collection<Part> parts = request.getParts();
  int count = 0;
  out.println("{");
  ArrayList<FileInfo> files = new ArrayList<>();

  for (Part part : parts) {
    if (part.getContentType() == null) {
      out.println(String.format("  %s\"%s\":\"%s\"", (count++ > 0 ? "," : ""), part.getName(),
          request.getParameter(part.getName())));
    } else if (part.getSize() > 0) {
      // 파일을 저장한다.
      String filename = UUID.randomUUID().toString();
      String filepath = application.getRealPath(// => /java-web
          "/upload/" + filename);
      part.write(filepath);

      // 저장한 파일 정보를 보관한다.
      FileInfo fileInfo = new FileInfo();
      fileInfo.filename = filename;
      fileInfo.filesize = part.getSize();
      fileInfo.originFilename = part.getSubmittedFileName();

      files.add(fileInfo);
    }
  }

  if (count++ > 0) {
    out.print("  ,");
  }

  out.println("\"files\": [");
  int fileCount = 0;
  for (FileInfo fileInfo : files) {
    out.print(String.format("  %s{\"filename\":\"%s\",\"filesize\":\"%d\"}\n",
        (fileCount++ > 0 ? "," : ""), fileInfo.filename, fileInfo.filesize));
  }
  out.println("  ]");
  out.println("}");
%>


<%!static class FileInfo {
    String filename;
    long filesize;
    String originFilename;
  }%>
