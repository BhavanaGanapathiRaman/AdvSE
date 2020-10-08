package Project.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Project.model.User;
import Project.util.SQLConnection;

public class UserDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public static void insertUser (User user) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertUser = "INSERT INTO cruise_activity.system_user VALUES ('"  
					+ user.getUsername()  + "','"
					+ user.getPassword() + "','"		
					+ user.getLastname() +"','"
					+ user.getFirstname() + "','"
					+ user.getPhone()+ "','"
					+ user.getEmail() + "','"
					+ user.getRoomno() + "','"
					+ user.getDeckno() + "','"
					+ user.getMemtype() + "')";
					
			stmt.executeUpdate(insertUser);	
			System.out.println(stmt);
			conn.commit(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String getMtype(String username) {
		Statement stmt = null;   
		Connection conn = null; 
		String mtype = "";
		conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String searchUser = " SELECT MEM_TYPE from cruise_activity.system_user WHERE USERNAME = '"+username+"'";
			ResultSet userList = stmt.executeQuery(searchUser);
			while (userList.next()) {
				mtype = userList.getString("MEM_TYPE");
			}
			System.out.println("MEM_TYPE = "+mtype);
		} catch (SQLException e) {}  
		return mtype;
	}
	public static Boolean usernameFound(String username) {  
		Statement stmt = null;   
		Connection conn = null;  
		conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String searchUser = " SELECT * from cruise_activity.system_user WHERE username = '"+username+"'";
			ResultSet userList = stmt.executeQuery(searchUser);
			if (!userList.next()) return false;
			 
		} catch (SQLException e) {}  
		return true;
	}
	public static Boolean passwordMatch(String username, String password) {  
		Statement stmt = null;   
		Connection conn = null;  
		conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String searchUser = " SELECT * from cruise_activity.system_user WHERE username = '"+username+"' AND password = '"+password+"'";
			ResultSet userList = stmt.executeQuery(searchUser);
			if (!userList.next()) return false;
			 
		} catch (SQLException e) {}  
		return true;
	}
	public static Boolean duplicateUser(String username) {  
		Statement stmt = null;   
		Connection conn = null;  
		conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String searchUser = " SELECT * from cruise_activity.system_user WHERE username = '"+username+"'";
			ResultSet userList = stmt.executeQuery(searchUser);
			if (!userList.next()) return false;
			 
		} catch (SQLException e) {}  
		return true;
	}
	public static Boolean duplicateMemtype(String role) {  
		Statement stmt = null;   
		Connection conn = null;  
		conn = SQLConnection.getDBConnection();  
		ArrayList<User> userListInDB = new ArrayList<User>();
		try {
			stmt = conn.createStatement();
			String searchUser = " SELECT * from CATERING.SYSTEM_USER WHERE role = '"+role+"'";
			ResultSet userList = stmt.executeQuery(searchUser);
			if (!userList.next()) return false;
			 
		} catch (SQLException e) {}  
		return true;
	}
	
	public static Boolean duplicateId(String id) {  
		Statement stmt = null;   
		Connection conn = null;  
		conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String searchUser = " SELECT * from cruise_activity.system_user WHERE ROOM_NUMBER = '"+id+"'";
			ResultSet userList = stmt.executeQuery(searchUser);
			if (!userList.next()) return false;
			 
		} catch (SQLException e) {}  
		return true;
	}
	public static ArrayList<User> Searchusername(String username) {
		System.out.println(username);
		return returnMatcingusers("SELECT * from cruise_activity.system_user WHERE username='"+username+"'");
	}
	public static User returnUser(String username)
	{
		Statement stmt = null;   
		Connection conn = null;  
		conn = SQLConnection.getDBConnection();  
		User userobj = new User();
		
		try {
			stmt = conn.createStatement();
			System.out.println("argument passed"+username);
			String searchUser = " SELECT * from cruise_activity.system_user WHERE USERNAME = '"+username+"'";
			ResultSet user = stmt.executeQuery(searchUser);
			
			if(user.next())
			{
			
				userobj.setUsername(user.getString("USERNAME"));
				userobj.setFirstname(user.getString("FIRSTNAME"));
				userobj.setLastname(user.getString("LASTNAME"));
				userobj.setPhone(user.getString("PHONE"));
				userobj.setEmail(user.getString("EMAIL"));
				userobj.setRoomno(user.getString("ROOM_NUMBER"));
				userobj.setDeckno(user.getString("DECK_NUMBER"));
				userobj.setMemtype(user.getString("MEM_TYPE"));
				userobj.setPassword(user.getString("PASSWORD"));
				
			
			}
			
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return userobj;
		
	}
	private static ArrayList<User> returnMatcingusers(String queryString) {
		ArrayList<User> userListInDB=new ArrayList<User>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet userList=stmt.executeQuery(queryString);
			while(userList.next())
			{
				User user = new User();
				user.setUsername(userList.getString("USERNAME"));
				user.setFirstname(userList.getString("FIRSTNAME"));
				user.setLastname(userList.getString("LASTNAME"));
				user.setPhone(userList.getString("PHONE"));
				user.setEmail(userList.getString("EMAIL"));
				user.setRoomno(userList.getString("ROOM_NUMBER"));
				user.setDeckno(userList.getString("DECK_NUMBER"));
				user.setMemtype(userList.getString("MEM_TYPE"));
				user.setPassword(userList.getString("PASSWORD"));
				
				userListInDB.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(userListInDB.get(0));
		return userListInDB;
	}
	public static void modifyUser(User user) {
		// TODO Auto-generated method stub
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection(); 
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE cruise_activity.system_user SET FIRSTNAME='"+user.getFirstname()+"',LASTNAME='"+user.getLastname()+"',PASSWORD='"+user.getPassword()+"',MEM_TYPE='"+user.getMemtype()+"',PHONE='"+user.getPhone()+"',EMAIL='"+user.getEmail()+"',ROOM_NUMBER='"+user.getRoomno()+"',DECK_NUMBER='"+user.getDeckno()+"' WHERE USERNAME='"+user.getUsername()+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void modifyUsername(User user, String username) {
		// TODO Auto-generated method stub
		
		Statement stmt = null;
		System.out.println("UPDATE cruise_activity.system_user SET USERNAME = '"+user.getUsername()+"', FIRSTNAME='"+user.getFirstname()+"',LASTNAME='"+user.getLastname()+"',PASSWORD='"+user.getPassword()+"',MEM_TYPE='"+user.getMemtype()+"',PHONE='"+user.getPhone()+"',EMAIL='"+user.getEmail()+"',ROOM_NUMBER='"+user.getRoomno()+"',DECK_NUMBER='"+user.getDeckno()+"' WHERE USERNAME='"+username+"'");
		Connection conn = SQLConnection.getDBConnection();
		try {
		stmt = conn.createStatement();
		//String updateStmt = ;
		//boolean res = stmt.execute(updateStmt);
		int rows = stmt.executeUpdate("UPDATE cruise_activity.system_user SET USERNAME = '"+user.getUsername()+"', FIRSTNAME='"+user.getFirstname()+"',LASTNAME='"+user.getLastname()+"',PASSWORD='"+user.getPassword()+"',MEM_TYPE='"+user.getMemtype()+"',PHONE='"+user.getPhone()+"',EMAIL='"+user.getEmail()+"',ROOM_NUMBER='"+user.getRoomno()+"',DECK_NUMBER='"+user.getDeckno()+"' WHERE USERNAME= '"+username+"'");
		conn.commit();
		System.out.println("rows impacted: " + rows);
		System.out.println(stmt);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
