<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="pl.coderslab.model.SolutionWithUser" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>

	<%@ include file="/WEB-INF/fragments/header.jspf"%>
	
	<h4>Details of username <c:out value="${loadedUser.username}"></c:out></h4>
	<p>Type values you would like to change</p>
	
	<ul>
		<li>Current username: <c:out value="${loadedUser.username}"></c:out></li>
		<li>Current email: <c:out value="${loadedUser.email}"></c:out></li>
		<li>Current user group: <c:out value="${loadedUser.user_group_id}"></c:out></li>
	</ul>

	<h4>Edit options for username <c:out value="${loadedUser.username}"></c:out></h4>

	<form method='post'>
	<ul>
		<li>Change username to: <input type='text' name='newUsername'></input></li>
		<li>Change email to: <input type='text' name='newEmail'></input></li>
		<li>Change user group id to: <input type='text' name='newUserGroupId'></input></li>
	</ul>
	<input type='submit'>Zatwierdz zmiany</input>
	</form>

	<%@ include file="/WEB-INF/fragments/footer.jspf"%>

</body>
</html>