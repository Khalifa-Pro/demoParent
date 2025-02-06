<%--
  Created by IntelliJ IDEA.
  User: FINAPPS
  Date: 06/02/2025
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    session.invalidate();
    response.sendRedirect("index.jsp");
%>

