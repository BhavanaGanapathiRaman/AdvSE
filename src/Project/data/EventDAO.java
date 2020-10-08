package Project.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import Project.model.Event;
import Project.model.User;
import Project.util.SQLConnection;

public  class EventDAO {

	private static final Logger LOGGER = Logger.getLogger(EventDAO.class.getName());
	   
	static SQLConnection DBMgr = SQLConnection.getInstance();

	private static ArrayList<Event> ReturnMatchingEventsList (String queryString) {
		ArrayList<Event> eventListInDB = new ArrayList<Event>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet eventList = stmt.executeQuery(queryString);
			while (eventList.next()) {
				Event event = new Event(); 
				event.setM_event_name(eventList.getString("EVENT_NAME"));
				event.setM_event_date(eventList.getString("EVENT_DATE"));
				event.setM_start_time(eventList.getString("START_TIME"));
				event.setM_duration(eventList.getString("DURATION"));
				event.setM_location(eventList.getString("LOCATION"));
				event.setM_numberofattendees(eventList.getString("ATTENDEES"));
				event.setM_capacity(eventList.getString("CAPACITY"));
				event.setM_eventcoordinator(eventList.getString("COORDINATOR"));
				event.setM_type(eventList.getString("TYPE"));
				
				 
				eventListInDB.add(event);	
			}
		} catch (SQLException e) {}
		System.out.println(eventListInDB);
		return eventListInDB;
	}
	public static Boolean update(String username,String x) {  
		Statement stmt = null;   
		Connection conn = null;  
		conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String a="Select * from cruise_activity.system_user";
			ResultSet y=stmt.executeQuery(a);
			while (y.next()) {
			System.out.println(y.getString("EVENT_NAME"));}
			String searchUser = " UPDATE cruise_activity.system_user SET COORDINATOR='"+username+"' WHERE COORDINATOR ='"+x+"' ";
			ResultSet userList = stmt.executeQuery(searchUser);
			if (!userList.next()) return false;
			 
		} catch (SQLException e) {}  
		return true;
	}

	
	public static ArrayList<Event>  searchEvent(String eventDate, String eventTime ) {  
		String query = "SELECT * FROM cruise_activity.events where EVENT_DATE > '"+eventDate+"' && START_TIME > '"+eventTime+"'";	
		System.out.println(query);
		return ReturnMatchingEventsList(query);
	}
	public static ArrayList<Event>  searchEventForPassenger(String eventDate, String eventTime,String eventType ) {  
		String query = "SELECT * FROM cruise_activity.events where EVENT_DATE > '"+eventDate+"' && START_TIME > '"+eventTime+"' && TYPE ='"+eventType+"'";	
		System.out.println(query);
		return ReturnMatchingEventsList(query);
	}
	public static void insertEvent (Event event) {
		System.out.println("Inside insertEvent of EventDAO");
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();

			
			String old_date=event.getM_event_date();
			String feventDate=" ";
			
			DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
			try {
				Date date = (Date) parser.parse(old_date);
			
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			    feventDate = formatter.format(date);
			
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String insertEvent = "INSERT INTO cruise_activity.events VALUES ('"  
					+ event.getM_event_name()  + "','"
					+ feventDate + "','"		
					+ event.getM_start_time() + "','"
					+ event.getM_duration()+"','"
					+ event.getM_location() + "','"
					+ event.getM_numberofattendees() + "','"
					+ event.getM_capacity() + "','"
					+ event.getM_eventcoordinator() + "','"
					+ event.getM_type() + "','"
					+event.getM_username()+ "')";
			
			System.out.println(insertEvent);
			int result=stmt.executeUpdate(insertEvent);	
			System.out.println("result of sql execution"+result);
			conn.commit(); 
		} catch (SQLException e) {}
	}
	public static void modifyEvent(Event event, String oldEventName) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection(); 
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE cruise_activity.events SET EVENT_DATE='"+event.getM_event_date()+"',START_TIME='"+event.getM_start_time()+"',ATTENDEES='"+event.getM_numberofattendees()+"' WHERE EVENT_NAME='"+oldEventName+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void modifyEventName(Event event, String oldEventName) {
		// TODO Auto-generated method stub
		
		Statement stmt = null;
		System.out.println("UPDATE cruise_activity.events SET EVENT_NAME = '"+event.getM_event_name()+"', EVENT_DATE='"+event.getM_event_date()+"',START_TIME='"+event.getM_start_time()+"',ATTENDEES='"+event.getM_numberofattendees()+"' WHERE EVENT_NAME='"+oldEventName+"'");
		Connection conn = SQLConnection.getDBConnection();
		try {
		stmt = conn.createStatement();
		//String updateStmt = ;
		//boolean res = stmt.execute(updateStmt);
		int rows = stmt.executeUpdate("UPDATE cruise_activity.events SET EVENT_NAME = '"+event.getM_event_name()+"', EVENT_DATE='"+event.getM_event_date()+"',START_TIME='"+event.getM_start_time()+"',ATTENDEES='"+event.getM_numberofattendees()+"' WHERE EVENT_NAME='"+oldEventName+"'");
		conn.commit();
		System.out.println("rows impacted: " + rows);
		System.out.println(stmt);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static Boolean eventDateFull(String date, String user)  {  
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String queryString = "SELECT COUNT(*) FROM cruise_activity.events WHERE EVENT_DATE('"+date+"', '%Y-%m-%d') = date AND username = '"+user+"'";
		
			ResultSet one = stmt.executeQuery(queryString);	
			
			while(one.next()) {
				if (one.getInt(1) > 1) return true;
				else return false; 
			}
		} catch (SQLException e) {System.out.println(e);}
		return true;
	}

	
	

}
