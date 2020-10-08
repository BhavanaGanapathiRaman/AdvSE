<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Passenger Profile</title>
</head>
<body>
<table style="with: 100%">
				<tr>
					<td>Username</td>
					<td>${loginU.username}</td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td>${loginU.lastname}</td>
				</tr>
					<tr>
					<td>First Name</td>
					<td>${loginU.firstname}</td>
				</tr>
				<tr>
					<td>Phone</td>
					<td>${loginU.phone}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td>${loginU.email}</td>
				</tr>
				<tr>
				<td>Room Number</td>
					<td>${loginU.roomno}</td>
				</tr>
				<tr>
				<td>Deck Number</td>
					<td>${loginU.deckno}</td>
				</tr>
				<tr>
				<td><label for="memtype">Membership Type</label></td>
					<td>${loginU.memtype}
					</td>
				</tr>
				</table>
				<a href = "updateprofile.jsp">Update Passenger Profile</a><br>
</body>
</html>