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

	<h4>Users administration panel</h4>
	
	<table border="1">
		<tr>
			<th scope="col">Username</th>
			<th scope="col">Email</th>
			<th scope="col">User Group</th>
			<th scope="col">Actions</th>
		</tr>
		<c:forEach var="user" items="${allUsers}">
			<tr>
				<td><c:out value="${user.username}"></c:out></td>		
				<td><c:out value="${user.email}"></c:out></td>
				<td><c:out value="${user.user_group_id}"></c:out></td>
				<td><a href="EditUser?id=${user.id}">Edit user</a></td>			
			</tr>
		</c:forEach>
	
	</table>
	
	<h4>Add new user:</h4>
	
	<form action='AddNewUser' method='post'>
		User: <input type='text' name='username'></input>
		Email: <input type='text' name='email'></input>
		Password: <input type='password' name='password'></input>
		User group id: <input type='text' name='user_group_id'></input>
		<br/>
		Create new user <input type='submit'></input>
	</form>

	<%@ include file="/WEB-INF/fragments/footer.jspf"%>

</body>
</html>