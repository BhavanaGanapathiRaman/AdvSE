package Project.model;

import Project.data.UserDAO;

public class User {
	private String username;
	private String password;
	private String lastname;
	private String firstname;
	private String phone;
	private String email;
	private String roomno;
	private String deckno;
	private String memtype;
	
	public void setUser(String username, String password, String lastname, String firstname, String phone,
			String email, String roomno, String deckno, String memtype) {
		this.setUsername(username);
		this.setPassword(password);
		this.setLastname(lastname);
		this.setFirstname(firstname);
		this.setPhone(phone);
		this.setEmail(email);
		this.setRoomno(roomno);
		this.setDeckno(deckno);
		this.setMemtype(memtype);
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoomno() {
		return roomno;
	}
	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	public String getDeckno() {
		return deckno;
	}
	public void setDeckno(String deckno) {
		this.deckno = deckno;
	}
	public String getMemtype() {
		return memtype;
	}
	public void setMemtype(String memtype) {
		this.memtype = memtype;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void validateUser (String action, User user, UserErrorMsgs errorMsgs) {
		if (action.equals("register")) {
			errorMsgs.setUsernameError(validateUsernameRegister(user.getUsername()));
			errorMsgs.setPasswordError(validatePasswordRegister(user.getPassword()));
			errorMsgs.setUserLastError(validateLastName(user.getLastname()));
			errorMsgs.setUserFirstError(validateFirstName(user.getFirstname()));
			errorMsgs.setUserPhoneError(validatePhone(user.getPhone()));
			errorMsgs.setUserEmailError(validateEmail(user.getEmail()));
			errorMsgs.setUserRoomnoError(validateRoomno(user.getRoomno()));
			errorMsgs.setUserDecknoError(validateDeckno(user.getDeckno()));
			//errorMsgs.setUserSelectError(validateMemtype(user.getMemtype()));
		}
		else if (action.equals("login")) {
			System.out.println("validating");
			System.out.println("user = "+user.getUsername());
			System.out.println("pass = "+user.getPassword());
			errorMsgs.setUsernameError(validateUsernameLogin(user.getUsername()));
			errorMsgs.setPasswordError(validatePasswordLogin(user.getUsername(),user.getPassword()));
		}
		errorMsgs.setErrorMsg();
	}
	
	private String validateUsernameLogin(String username) {
		String result = "";
		
		if(!UserDAO.usernameFound(username))
			result = "Username not found in the system";
		System.out.println("u result = "+result);
		return result;
	}
	
	private String validatePasswordLogin(String username, String password) {
		String result = "";
		
		if(!UserDAO.passwordMatch(username, password))
			result = "Password does not match the username";
		System.out.println("p result = "+result);
		return result;
	}
	private String validateUsernameRegister(String username) {
		String result = "";
		if (!stringSize(username,5,20))
			result = "Username length must be >4 and <=20";
		else if(!Character.isLetter(username.charAt(0)))
			result = "Username must start with a letter";
		else if (specialCharacter(username))
			result = "Username cannot contain special characters";
		else if (UserDAO.duplicateUser(username))
				result = "Username already in database";
		return result;
	}
	
	private String validatePasswordRegister(String password) {
		String result = "";
		//Use regex matcher!!!
	/*	if(!Character.isLetter(username.charAt(0)))
			result = "Username must start with a letter";
		else if (!stringSize(username,5,20))
			result = "Username length must be >4 and <=20";
		else if (specialCharacter(username))
			result = "Username cannot contain special characters";*/
		
//		Pattern pattern;
//	    Matcher matcher;
	 
	     String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,29})";
	 /*    String lowerCase = "(?=.*[a-z])";
	     String digit = "(?=.*\\\\d)";
	     String upperCase = "(?=.*[A-Z])";
	     String specialCharacter = "(?=.*[@#$%!&*^])";
	     String charLength = "{8,29}";*/
	     
	     boolean lowercase = false;
	     boolean uppercase = false;
	     boolean digit = false;
	     boolean special = false;
	     
	    if(!stringSize(password, 8, 29))
	    {
			  result = "Password must be between 8 and 29 characters";
		}
	    else {
		    for ( int i = 0; i < password.length(); i++) {
		    	char a = password.charAt(i);
		    	if(Character.isDigit(a))
		    		digit = true;
		    	else if (Character.isUpperCase(a))
		    		uppercase = true;
		    	else if (Character.isLowerCase(a))
		    		lowercase = true;
		    	else if (a == '@' || a == '#' || a == '$' || a == '%' || a == '^' || a == '&' || a == '*' || a == '!')
		    		special = true;
		    }
		    if(!lowercase)
		    {
				  System.out.println("Password "+ password +" is invalid");
				  result = "Password must contain a lowercase character";
			}	
		    else if(!digit)
		    {
				  System.out.println("Password "+ password +" is invalid");
				  result = "Password must contain a digit";
			}
		    else if(!uppercase)
		    {
				  System.out.println("Password "+ password +" is invalid");
				  result = "Password must contain a uppercase character";
			}
		    else if(!special)
		    {
				  System.out.println("Password "+ password +" is invalid");
				  result = "Password must contain a special character";
			}
	    }
		return result;
	}
	
	private String validateLastName(String last) {
		String result = "";
		
		if (!stringSize(last,3,29))
			result = "Last name length must be >2 and <30";	
		else if (isTextAnInteger(last))
			result = "Last name cannot be a number";
		else if(!Character.isUpperCase(last.charAt(0)))
			result = "Last name must start with a capital letter";
		else if (specialCharacter(last))
			result = "Last name cannot contain special characters";
		return result;
	}
	
	private String validateFirstName(String first) {
		String result = "";

		if (!stringSize(first,3,29))
			result = "First name length must be >2 and <30";
		else if (isTextAnInteger(first))
			result = "First name cannot be a number";
		else if(!Character.isUpperCase(first.charAt(0)))
			result = "First name must start with a capital letter";		
		else if (specialCharacter(first))
			result = "First name cannot contain special characters";
		return result;
	}
	
	
	/*
	 * private String validateMemtype (String role) { String result = ""; if
	 * (role.equals("Admin") || role.equals("Caterer Manager")) {
	 * if(UserDAO.duplicateMemtype(role)) result = "System can only have one "+role;
	 * } return result;
	 * 
	 * }
	 */
	 
	
	private String validatePhone(String phone) {
		String result = "";
		
		if(phone.length() != 10)
			result = "Phone number must have 10 digits";
		else if (!isTextAnInteger(phone))
			result = "Phone number must be numeric";
		
		return result;
	}
	
	private String validateRoomno(String no) {
		String result = "";
		
		 if (!isTextAnInteger(no))
			result = "ROOM NUMBER must be numeric";
		else if (UserDAO.duplicateId(no))
			result = "Room Number already in use";	
		 else if(Integer.parseInt(no) < 100 || Integer.parseInt(no) > 199) {
			result = "Room Number cannot be < 100 or > 199";
		}
		
		return result;
	}
	
	private String validateEmail(String email) {
		String result = "";

		if(!stringSize(email,7,45))
			result = "Email address must be between 7 and 45 characters long";
		else if (!email.contains("@"))
			result = "Email address needs to contain @";
		else {
			String domain = email.substring(email.length()-4);
			if (!(domain.equals(".com") || domain.equals(".gov") || domain.equals(".edu") ||
					domain.equals(".org") || domain.equals(".mil") || domain.equals(".net")))
				result = "Invalid domain name";
		}
					
		
		return result;
	}
	
	private String validateDeckno(String number) {
		String result = "";
System.out.println(number);
		if (!stringSize(number,1,6))
			result = "Deck number length must be >0 and <16";
		else if (!isTextAnInteger(number))
			result = "Deck number must be numeric";
		else if(Integer.parseInt(number) <= 0)
			result = "Deck number must be >0";		
		
		return result;
	}
	
	private boolean stringSize(String string, int min, int max) {
		
		return string.length()>=min && string.length()<=max;
	}
	
	private boolean isTextAnInteger (String string) {
        boolean result;
		try
        {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) 
        {
            result=false;
        }
		return result;
	}
	
	private boolean specialCharacter(String string) {
		boolean result = false;
		for (int i = 0; i < string.length(); i++) {
			if (!(Character.isLetter(string.charAt(i)) || Character.isDigit(string.charAt(i))))
				result = true;
		}
		return result;
	}
}
