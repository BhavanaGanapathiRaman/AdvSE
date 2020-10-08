<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify selected event</title>
</head>
<body>
	<input name="errMsg" value="<c:out value='${errorMsgs.errorMsg}'/>" class="errorPane">
<form  method="post" action="EventController?action=updateEvent">
			<table style="with: 100%">
				<tr>
					<td>Event Name</td>
					<td>${Selected_Event.m_event_name}</td>
					<td> <input name="usernameError" value='${errorMsgs.usernameError}' class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Event Date</td>
					<td><input type="text" name="eventdate" value="${Selected_Event.m_event_date}"/></td>
					<td> <input name="passwordError" value='${errorMsgs.passwordError}' class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Start Time</td>
					<td><input type="text" name="starttime" value="${Selected_Event.m_start_time}"/></td>
					<td> <input name="userLastError" value= '${errorMsgs.userLastError}' class="errorMsg"> </td>
				</tr>
					<tr>
					<td>Duration</td>
					<td>${Selected_Event.m_duration}</td>
					<td> <input name="userFirstError" value='${errorMsgs.userFirstError}' class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Location</td>
					<td>${Selected_Event.m_location}</td>
					<td> <input name="userPhoneError" value='${errorMsgs.userPhoneError}' class="errorMsg"> </td>
				</tr>
				<tr>
					<td>Attendees</td>
					<td><input type="text" name="attendees" value="${Selected_Event.m_numberofattendees}"/></td>
					<td> <input name="userEmailError" value='${errorMsgs.userEmailError}' class="errorMsg"> </td>
				</tr>
				<tr>
				<td>Capacity</td>
					<td>${Selected_Event.m_capacity}</td>
					<td> <input name="userRoomnoError" value='${errorMsgs.userRoomnoError}' class="errorMsg"> </td>
				</tr>
				<tr>
				<td>Coordinator</td>
					<td>${Selected_Event.m_eventcoordinator}</td>
					<td> <input name="userDecknoError" value='${errorMsgs.userDecknoError}' class="errorMsg"> </td>
				</tr>
				<tr>
				<td><label for="evetype">Type</label></td>
					<td>${Selected_Event.m_type}</span>
					</td>
					<td> <input name="userSelectError" value='${errorMsgs.userSelectError}' class="errorMsg"> </td>
				</tr>
				</table>
			<input type="submit" value="Submit" onclick="return updateconfirm()"/>
			</form>
			<script type="text/javascript">
			function updateconfirm() {

				if(confirm("Are you sure you want to Update?"))
					return true;	
				else
				    history.go(-2);
					return false;
				}
			</script>
</body>
</html>