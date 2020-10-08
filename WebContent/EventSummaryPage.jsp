<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!-- m_event_name; -->
<!-- 	private String m_event_date; -->
<!-- 	private String m_start_time; -->
<!-- 	private String m_duration; -->
<!-- 	private String m_location; -->
<!-- 	private String m_numberofattendees; -->
<!-- 	private String m_capacity; -->
<!-- 	private String m_eventcoordinator; -->
<!-- 	private String m_type; -->
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="myStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>

<h1>THIS IS EVENT SUMMARY PAGE</h1>


<form action='EventController?action=listSpecificEvent' method="post">
 <table border="1" class="myTable"> 
 <tr>
 	<td> <input name="current_user" value='${current_user.username}'  class="text45">  </td>
</tr>
			<tr class="myTableRow"> 
				<th class="myTableHead" style="width: 130px; ">Select Event</th>
				<th class="myTableHead" style="width: 130px; ">Event name</th>
				<th class="myTableHead" style="width: 130px; ">Event date</th>
				<th class="myTableHead" style="width: 130px; ">Start time</th>
				<th class="myTableHead" style="width: 105px; ">Duration</th>
				<th class="myTableHead" style="width: 74px;  ">Location</th> 
				<th class="myTableHead" style="width: 130px; ">Number of attendees</th> 
			</tr>
			
			<c:forEach items="${EVENTS}" var="item" varStatus="status">
			<tr class="myTableRow"> 
			<td class="myTableRadio"><input type="radio" id="radioEvent${status.count}" name="radioEvent" value="<c:out value="${status.count}" />"></td> 
				<td class="myTableCell" style="width: 130px; "> ${item.m_event_name} </td> 
				<td class="myTableCell" style="width: 130px; "> ${item.m_event_date} </td>
				<td class="myTableCell" style="width: 105px; "> ${item.m_start_time}</td>
				<td class="myTableCell" style="width: 74px;  "> ${item.m_duration} </td>
				<td class="myTableCell" style="width: 130px; "> ${item.m_location} </td> 
				<td class="myTableCell" style="width: 130px; "> ${item.m_numberofattendees} </td>
			
		   </tr>
		   </c:forEach>
</table>
<input name="ListSelectedCompanyButton" type="submit" value="View selected">

</form>
<a href="index.jsp"><input name="logoutButton" type="submit" value="Logout"></a>

</body>
</html>