<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="pl.coderslab.model.UserGroup" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>

	<%@ include file="/WEB-INF/fragments/header.jspf"%>
	<h4><c:out value="Edit group ${userGroup.id}"></c:out></h4>
	
	<form method='post'>
		Edit group name:
		<input type='text' name='newGroupName'></input>
		<input type='submit'></input>	
	</form>

	<%@ include file="/WEB-INF/fragments/footer.jspf"%>

</body>
</html>