<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action='EventController?action=updateEventCoordinator' method="post">
	FirstName:<input type="text" name = 'FirstName' value = '${user.username}'/>
	LastName:<input type="text" name="LastName" value = '${user.password}'/>
<input type="submit" value="submit"/> 
</form>
</body>
</html>