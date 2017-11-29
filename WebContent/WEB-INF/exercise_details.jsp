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
		<h4>Exercise details:</h4>
	
	<table border="1">
		<tr>
			<th scope="col">Title</th>
			<th scope="col">Description</th>
		</tr>
		<tr>
			<td><c:out value="${loadedExercise.title}"></c:out> </td>
			<td><c:out value="${loadedExercise.description}"></c:out></td>
		</tr>	
	</table>
	<h4><c:out value="Edit exercise ${loadedExercise.id}"></c:out></h4>
	
	<form method='post'>
		New exercise title:
		<input type='text' name='newExerciseTitle'></input>
		New exercise description:
		<input type='text' name='newExerciseDescription'></input>		
		<input type='submit'></input>	
	</form>
	
	<%@ include file="/WEB-INF/fragments/footer.jspf"%>

</body>
</html>