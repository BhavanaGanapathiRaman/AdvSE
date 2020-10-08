package Project.model;

public class EventErrorMsgs {

	private String m_event_nameError;
	private String m_event_dateError;
	private String m_start_timeError;
	private String m_durationError;
	private String m_locationError;
	private String m_numberofattendeesError;
	private String m_capacityError;
	private String m_eventcoordinatorError;
	private String m_typeError;
	private String m_errorMsg;
	
	public String getM_event_nameError() {
		return m_event_nameError;
	}

	public void setM_event_nameError(String m_event_nameError) {
		this.m_event_nameError = m_event_nameError;
	}

	public String getM_event_dateError() {
		return m_event_dateError;
	}

	public void setM_event_dateError(String m_event_dateError) {
		this.m_event_dateError = m_event_dateError;
	}

	public String getM_start_timeError() {
		return m_start_timeError;
	}

	public void setM_start_timeError(String m_start_timeError) {
		this.m_start_timeError = m_start_timeError;
	}

	public String getM_durationError() {
		return m_durationError;
	}

	public void setM_durationError(String m_durationError) {
		this.m_durationError = m_durationError;
	}

	public String getM_locationError() {
		return m_locationError;
	}

	public void setM_locationError(String m_locationError) {
		this.m_locationError = m_locationError;
	}

	public String getM_numberofattendeesError() {
		return m_numberofattendeesError;
	}

	public void setM_numberofattendeesError(String m_numberofattendeesError) {
		this.m_numberofattendeesError = m_numberofattendeesError;
	}

	public String getM_capacityError() {
		return m_capacityError;
	}

	public void setM_capacityError(String m_capacityError) {
		this.m_capacityError = m_capacityError;
	}

	public String getM_eventcoordinatorError() {
		return m_eventcoordinatorError;
	}

	public void setM_eventcoordinatorError(String m_eventcoordinatorError) {
		this.m_eventcoordinatorError = m_eventcoordinatorError;
	}

	public String getM_typeError() {
		return m_typeError;
	}

	public void setM_typeError(String m_typeError) {
		this.m_typeError = m_typeError;
	}

	public String getM_errorMsg() {
		return m_errorMsg;
	}

	public void setM_errorMsg(String m_errorMsg) {
		this.m_errorMsg = m_errorMsg;
	}


	

	public EventErrorMsgs() {
		this.m_event_nameError = "";
		this.m_event_dateError = "";
		this.m_start_timeError = "";
		this.m_durationError = "";
		this.m_locationError = "";
		this.m_numberofattendeesError = "";
		this.m_capacityError = "";
		this.m_eventcoordinatorError = "";
		this.m_typeError = "";
		this.setM_errorMsg("");

	}

	public void setErrorMsg() {
		if (!m_event_nameError.equals("") || !m_event_dateError.equals("") || !m_start_timeError.equals("")
				|| !m_durationError.equals("") || !m_locationError.equals("") || !m_numberofattendeesError.equals("")
				|| !m_capacityError.equals("") || !m_eventcoordinatorError.equals("") ||! m_typeError.equals(""))
		{
			setM_errorMsg("Please correct the following errors");
		
		}
		
	}
	
	

}
