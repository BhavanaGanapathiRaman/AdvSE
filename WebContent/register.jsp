<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify selected event</title>
</head>
<body>

	<input name="errMsg" value="<c:out value='${errorMsgs.errorMsg}'/>"
		class="errorPane">

	<form method="post" action="EventController?action=Reserve">
		<input name="user"
			value="<c:out value='${Selected_Event.m_username}'/>">
		<table style="with: 100%">
			<tr>
				<td><input name="current_user" value='${current_user.username}'
					class="text45"></td>
			</tr>
			<tr>
				<td>Event Name</td>
				<td>${Selected_Event.m_event_name}</td>

			</tr>
			<tr>
				<td>Event Date</td>
				<td>${Selected_Event.m_event_date}</td>

			</tr>
			<tr>
				<td>Start Time</td>
				<td>${Selected_Event.m_start_time}</td>
				<td>
			</tr>
			<tr>
				<td>Duration</td>
				<td>${Selected_Event.m_duration}</td>

			</tr>
			<tr>
				<td>Location</td>
				<td>${Selected_Event.m_location}</td>

			</tr>
			<tr>
				<td>Attendees</td>
				<td><input type="text" name="attendees"
					value="${Selected_Event.m_numberofattendees}" /></td>
				<td><input name="attendeeserror"
					value='${errorMsgs.m_numberofattendeesError}' class="errorMsg">
				</td>
			</tr>
			<tr>
				<td>Capacity</td>
				<td>${Selected_Event.m_capacity}</td>

			</tr>
			<tr>
				<td>Coordinator</td>
				<td>${Selected_Event.m_eventcoordinator}</td>

			</tr>
			<tr>
				<td><label for="evetype">Type</label></td>
				<td>${Selected_Event.m_type}</span>
				</td>

			</tr>
		</table>
		<input type="submit" value="Submit" onclick="return updateconfirm()" />
	</form>
	<script>
		function updateconfirm() {

			if (confirm("Event Registered!"))
				return true;
			else
				history.go(-2);
			return false;
		}
	</script>

</body>
</html>