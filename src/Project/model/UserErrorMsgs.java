package Project.model;

public class UserErrorMsgs {
	private String errorMsg;
	private String usernameError;
	private String passwordError;
	private String userLastError;
	private String userFirstError;
	private String userPhoneError;
	private String userEmailError;
	private String userRoomnoError;
	private String userDecknoError;
	private String userSelectError;
	
	public UserErrorMsgs() {
		this.errorMsg = "";
		this.usernameError = "";
		this.passwordError = "";
		this.userLastError = "";
		this.userFirstError = "";
		this.userPhoneError = "";
		this.userEmailError = "";
		this.userRoomnoError = "";
		this.userDecknoError = "";
		this.userSelectError = "";
		
	}

	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg() {
		if (!usernameError.equals("") || !passwordError.equals("") || !userLastError.equals("") || !userFirstError.equals("") ||
				!userPhoneError.equals("") || !userEmailError.equals("") || !userRoomnoError.equals("") || !userDecknoError.equals("") ||
				!userSelectError.equals("")) {
			errorMsg = "Please correct the following errors";
		}
		else {
			System.out.println("no errors");
		}
	}
	
	
	public String getUsernameError() {
		return usernameError;
	}
	public void setUsernameError(String usernameError) {
		this.usernameError = usernameError;
	}
	public String getPasswordError() {
		return passwordError;
	}
	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}
	public String getUserLastError() {
		return userLastError;
	}
	public void setUserLastError(String userLastError) {
		this.userLastError = userLastError;
	}
	public String getUserFirstError() {
		return userFirstError;
	}
	public void setUserFirstError(String userFirstError) {
		this.userFirstError = userFirstError;
	}
	public String getUserPhoneError() {
		return userPhoneError;
	}
	public void setUserPhoneError(String userPhoneError) {
		this.userPhoneError = userPhoneError;
	}
	public String getUserEmailError() {
		return userEmailError;
	}
	public void setUserEmailError(String userEmailError) {
		this.userEmailError = userEmailError;
	}
	public String getUserRoomnoError() {
		return userRoomnoError;
	}
	public void setUserRoomnoError(String userRoomnoError) {
		this.userRoomnoError = userRoomnoError;
	}
	public String getUserDecknoError() {
		return userDecknoError;
	}
	public void setUserDecknoError(String userDecknoError) {
		this.userDecknoError = userDecknoError;
	}
	public String getUserSelectError() {
		return userSelectError;
	}
	public void setUserSelectError(String userSelectError) {
		this.userSelectError = userSelectError;
	}
	
}
