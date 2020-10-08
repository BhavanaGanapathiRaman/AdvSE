<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="myStyle.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>

	<h1>SPECIFIC EVENT PAGE</h1>
	 
<form action='EventController?action=Reserve' method="post">

	<table border="1" class="myTable">
	 <tr>
 	<td> <input name="current_user" value='${current_user.username}'  class="text45">  </td>
     </tr>
		<tr class="myTableRow">
			<th class="myTableHead" style="width: 130px;">Selected Event</th>
			<th class="myTableHead" style="width: 130px;">Event Name</th>
			<th class="myTableHead" style="width: 130px;">Event Date</th>
			<th class="myTableHead" style="width: 105px;">Start Time</th>
			<th class="myTableHead" style="width: 74px;">Duration</th>
			<th class="myTableHead" style="width: 130px;">Location</th>
			<th class="myTableHead" style="width: 130px;">Attendees</th>
			<th class="myTableHead" style="width: 105px;">Capacity</th>
			<th class="myTableHead" style="width: 74px;">Coordinator</th>
			<th class="myTableHead" style="width: 74px;">Type</th>
			
		</tr>


		<tr class="myTableRow">
			
			<td class="myTableCell" style="width: 130px;">
			
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_event_name} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_event_date} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_start_time} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_duration} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_location} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_numberofattendees} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_capacity} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_eventcoordinator} </td> 
				<td class="myTableCell" style="width: 130px; "> ${Selected_Event.m_type} </td>
				<td class="myTableCell" style="width: 130px; "><a href="register.jsp">register</a></td> 
				<td class="myTableCell" style="width: 130px; "><a href="modifyevent.jsp">modify</a></td> 

		</tr>
	</table>
	</form>
	<a href="index.jsp"><input name="logoutButton" type="submit" value="Logout"></a>
	<a href="ChangeCoordinator.jsp"><input name="ChangeCoordinator" type="submit" value="Change Coordinator"></a>
	
	
</body>
</html>