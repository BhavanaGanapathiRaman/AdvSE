package Project.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import Project.data.EventDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Event implements Serializable {

	private static final long serialVersionUID = 1L;
	private String m_event_name;
	private String m_event_date;
	private String m_start_time;
	private String m_duration;
	private String m_location;
	private String m_numberofattendees;
	private String m_capacity;
	private String m_eventcoordinator;
	private String m_type;
	private String m_username;

	public void setEventUser(String username) {
		setM_username(username);
	}

	public void setEvent(String eventname, String eventdate, String starttime, String duration, String location,
			String numberofattendees, String capacity, String eventcoordinator, String type) {
		setM_event_name(eventname);
		setM_event_date(eventdate);
		setM_start_time(starttime);
		setM_duration(duration);
		setM_location(location);
		setM_numberofattendees(numberofattendees);
		setM_capacity(capacity);
		setM_eventcoordinator(eventcoordinator);
		setM_type(type);

	}

	public String getM_event_name() {
		return m_event_name;
	}

	public void setM_event_name(String m_event_name) {
		this.m_event_name = m_event_name;
	}

	public String getM_event_date() {
		return m_event_date;
	}

	public void setM_event_date(String m_event_date) {
		this.m_event_date = m_event_date;
	}

	public String getM_start_time() {
		return m_start_time;
	}

	public void setM_start_time(String m_start_time) {
		this.m_start_time = m_start_time;
	}

	public String getM_duration() {
		return m_duration;
	}

	public void setM_duration(String m_duration) {
		this.m_duration = m_duration;
	}

	public String getM_location() {
		return m_location;
	}

	public void setM_location(String m_location) {
		this.m_location = m_location;
	}

	public String getM_numberofattendees() {
		return m_numberofattendees;
	}

	public void setM_numberofattendees(String m_numberofattendees) {
		this.m_numberofattendees = m_numberofattendees;
	}

	public String getM_capacity() {
		return m_capacity;
	}

	public void setM_capacity(String m_capacity) {
		this.m_capacity = m_capacity;
	}

	public String getM_eventcoordinator() {
		return m_eventcoordinator;
	}

	public void setM_eventcoordinator(String m_eventcoordinator) {
		this.m_eventcoordinator = m_eventcoordinator;
	}

	public String getM_type() {
		return m_type;
	}

	public void setM_type(String m_type) {
		this.m_type = m_type;
	}

	public String getM_username() {
		return m_username;
	}

	public void setM_username(String m_username) {
		this.m_username = m_username;
	}

	public void validateEvent(String action, Event event, EventErrorMsgs errorMsgs) {
		if (action.equals("listEvents") || action.equalsIgnoreCase("updateEvent")) {
			if (event.getM_event_date().equals("") && event.getM_start_time().equals("")) {
				errorMsgs.setM_event_dateError("event date cannot be blank");
				errorMsgs.setM_start_timeError("event time cannot be blank");
				System.out.println("BOTH DATE AND TIME EMPTY");
			} else if (event.getM_event_date().equals("")) {
				errorMsgs.setM_event_dateError("event date cannot be blank");
				System.out.println("DATE EMPTY");
			} else if (event.getM_start_time().equals("")) {
				errorMsgs.setM_event_dateError("event time cannot be blank");
				System.out.println("TIME EMPTY");
			}
			String result = "";
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date date1 = null, date2 = null;
			try {
				date1 = sdf.parse(event.getM_event_date());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				result = "Date not in correct format";
				errorMsgs.setM_event_dateError(result);
			}
			String current = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
			try {
				date2 = sdf.parse(current);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				result = "Date not in correct format";
				errorMsgs.setM_event_dateError(result);
			}
			if (result.equals("")) {
				if (date1.before(date2)) {
					result = "Cannot be past date";
					errorMsgs.setM_event_dateError(result);
				} else if (date1.equals(date2)) {
					result = validateTime(event.getM_start_time());
					errorMsgs.setM_start_timeError(result);
				}
			}

			errorMsgs.setErrorMsg();
		} else if (action.equals("Reserve")) {
			
			if (Integer.parseInt(event.getM_capacity()) < Integer.parseInt(event.getM_numberofattendees()) ){
				String result = "";
				result = "Number of Attendees cannot be higher than Capacity";
				errorMsgs.setM_numberofattendeesError(result);
			}
			errorMsgs.setErrorMsg();
		}
	}

	public void validateEventForPassenger(String action, Event event, String type, EventErrorMsgs errorMsgs) {
		if (action.equals("listEventsForPassengers") || action.equalsIgnoreCase("updateEvent")) {
			if (event.getM_event_date().equals("") && event.getM_start_time().equals("")
					&& event.getM_type().equals("")) {
				errorMsgs.setM_event_dateError("event date cannot be blank");
				errorMsgs.setM_start_timeError("event time cannot be blank");
				errorMsgs.setM_typeError("event type cannot be blank");
				System.out.println(" DATE,TIME AND TYPE EMPTY");
			} else if (event.getM_event_date().equals("")) {
				errorMsgs.setM_event_dateError("event date cannot be blank");
				System.out.println("DATE EMPTY");
			} else if (event.getM_start_time().equals("")) {
				errorMsgs.setM_event_dateError("event time cannot be blank");
				System.out.println("TIME EMPTY");
			} else if (event.getM_type().equals("")) {
				errorMsgs.setM_typeError("event type cannot be blank");
				System.out.println("TYPE EMPTY");
			}
			String result = "";
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date date1 = null, date2 = null;
			try {
				date1 = sdf.parse(event.getM_event_date());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				result = "Date not in correct format";
				errorMsgs.setM_event_dateError(result);
			}
			String current = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
			try {
				date2 = sdf.parse(current);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				result = "Date not in correct format";
				errorMsgs.setM_event_dateError(result);
			}
			if (result.equals("")) {
				if (date1.before(date2)) {
					result = "Cannot be past date";
					errorMsgs.setM_event_dateError(result);
				} else if (date1.equals(date2)) {
					result = validateTime(event.getM_start_time());
					errorMsgs.setM_start_timeError(result);
				}
			}

			errorMsgs.setErrorMsg();
		}
	}

	private String validateTime(String time) {
		String result = "";
		SimpleDateFormat skf = new SimpleDateFormat("HH:mm:ss");
		Date time1 = null, time2 = null;
		try {
			time1 = skf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			result = "time not in correct format";
		}
		String current = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		try {
			time2 = skf.parse(current);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result.equals("")) {
			if (time1.before(time2)) {
				result = "Cannot be past time";
			}
		}
		return result;
	}

	private String validateEventDateReserve(String date, String time, String user) {
		String result = "";
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String currentTime = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());

		int dateInt = Integer.parseInt(date.substring(0, 4) + date.substring(5, 7) + date.substring(8));

		int currentInt = Integer
				.parseInt(currentDate.substring(0, 4) + currentDate.substring(5, 7) + currentDate.substring(8));
		int year = dateInt / 10000;
		int month = (dateInt % 10000) / 100;
		int day = dateInt % 100;
		if (dateInt < currentInt)
			result = "Date must not be in the past";

		else if (dateInt == currentInt && time.compareTo(currentTime) < 0)
			result = "Date/time must not be in the past";

		else if (EventDAO.eventDateFull(date, user))
			result = "No more than 2 events can be booked per day";

//			else if (EventDAO.eventWeekFull(date,user))
//				result = "No more than 5 events can be booked in a 7 day period";

		return result;
	}

}
