<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="pl.coderslab.model.Exercise" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.jspf"%>
	<h4>Available exercises:</h4>
	
	<table border="1">
		<tr>
			<th scope="col">Title</th>
			<th scope="col">Description</th>
			<th scope="col">Actions</th>
		</tr>
		
		<c:forEach var="ex" items="${allExercises}">
			<tr>
				<td><c:out value="${ex.title}"></c:out></td>
				<td><c:out value="${ex.description}"></c:out></td>
				<td> <a href="ExerciseDetails?id=${ex.id}">Edit</a> </td>
			</tr>
		
		</c:forEach>
	
	</table>
	<br/>
	<form action='AddNewExercise' method='post'>
		Title: <input type='text' name='newExTitle'></input>
		Description: <input type='text' name='newExDescription'></input>
		<br/>
		Create new exercise <input type='submit'></input>
	</form>
	
	<%@ include file="/WEB-INF/fragments/footer.jspf"%>

</body>
</html>