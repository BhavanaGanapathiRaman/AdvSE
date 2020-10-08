<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<input name="errMsgs"  value='${errorMsgs.errorMsg}' class="errorPane">
<input name="none"  value='${none}' class="errorPane">
	<form method="post" action="UserController?action=login">  
	Username:<input type="text" name = 'username' value = '${user.username}'/>
	<input name="usernameError"  value='${errorMsgs.usernameError}' class="errorMsg"><br/><br/>
	Password:<input type="password" name="password" value = '${user.password}'/>
	<input name="passwordError"  value='${errorMsgs.passwordError}' class="errorMsg"><br/><br/>  
<input type="submit" value="submit"/> 
</form>
</body>
</html>