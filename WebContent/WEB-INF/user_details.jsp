<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="pl.coderslab.model.User" %> 
<%@ page import="pl.coderslab.model.Solution" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.jspf"%>

	<h4>User details:</h4>
	
	<table border="1">
		<tr>
			<td>Username</td>
			<td>E-mail</td>
			<td>User group</td>
		</tr>
		<tr>
			<td>${loadedUser.username}</td>
			<td><c:out value="${loadedUser.email}"></c:out> </td>
			<td><c:out value="${loadedUser.user_group_id}"></c:out></td>
		</tr>
	
	
	
	</table>

	<%@ include file="/WEB-INF/fragments/footer.jspf"%>
</body>
</html>