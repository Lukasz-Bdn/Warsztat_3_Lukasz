<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="pl.coderslab.model.User" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>

	<%@ include file="/WEB-INF/fragments/header.jspf"%>

	<h4>All users in this group (group id = ${groupId}):</h4>
	
	<table border='1'>
		<tr>
			<th scope="col">User name:</th>
			<th scope="col">Actions:</th>
		</tr>
		
		<c:forEach var="user" items="${usersInGroup}">
			<tr>
				<td><c:out value="${user.username}"></c:out></td>
				<td><a href='UserDetails?id=${user.id}'>User details</a></td>
			</tr>
		
		</c:forEach>
	
	
	</table>
	
	<%@ include file="/WEB-INF/fragments/footer.jspf"%>

</body>
</html>