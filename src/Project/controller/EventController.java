package Project.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

import Project.data.EventDAO;
import Project.data.UserDAO;
import Project.model.*;

@WebServlet("/EventController")
public class EventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private void getEventParam (HttpServletRequest request, Event event) {
		event.setEvent(request.getParameter("eventname"), request.getParameter("eventdate"), request.getParameter("starttime"), 
				request.getParameter("duration"), request.getParameter("location"), request.getParameter("attendees"), 
				request.getParameter("capacity"), request.getParameter("coordinator"), request.getParameter("evetype"));
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Entered doPost in Controller");
		String action = request.getParameter("action"), url = "";
		HttpSession session = request.getSession();
		Event event = new Event();
		EventErrorMsgs CerrorMsgs = new EventErrorMsgs();
		session.removeAttribute("errorMsgs"); // didnt understand
		ArrayList<Event> eventInDB = new ArrayList<Event>();
		int selectedEventIndex;

		if (action.equalsIgnoreCase("listEvents")) {
			String feventDate = null;
			String eventDate = request.getParameter("event_date");
			String eventTime = request.getParameter("event_time");
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			
			DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
			try {
				Date date = (Date) parser.parse(eventDate);
			
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				feventDate = formatter.format(date);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			session.removeAttribute("errorMsgs");
			event.setEvent("", eventDate, eventTime, "", "", "", "", "", "");
			event.validateEvent(action, event, CerrorMsgs);
			if (CerrorMsgs.getM_errorMsg().equals("")) {
				if (!eventDate.equals("") && !eventTime.equals("")) {
					eventInDB = EventDAO.searchEvent(feventDate, eventTime);
					
					for (int i = 0; i < eventInDB.size(); i++) {
						String fdateItem = null;
						String dateItem = eventInDB.get(i).getM_event_date();
						if(dateItem != null) {
							DateFormat p = new SimpleDateFormat("yyyy-MM-dd");
							try {
								Date d = (Date) p.parse(dateItem);
								DateFormat f = new SimpleDateFormat("MM/dd/yyyy");
								fdateItem = f.format(d);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						eventInDB.get(i).setM_event_date(fdateItem);
					}
					
				//	session.setAttribute("EVENTS", );
					System.out.println("SIZE"+eventInDB.size());
					System.out.println("came till here");
					System.out.println(eventInDB.get(0).getM_event_date());
					System.out.println(eventInDB.get(0).getM_start_time());
					Event e = eventInDB.get(0);
					System.out.println("THERE IS NO ERROR hence entered this block");
					session.setAttribute("EVENTS", eventInDB);
					//System.out.println(session.getAttribute("EVENTS"));
					//ArrayList<Event> eventslist=session.getAttribute("EVENTS");
					url="/EventSummaryPage.jsp";
				}
			} else {
				session.setAttribute("event", event);
				session.setAttribute("errorMsgs", CerrorMsgs);
				getServletContext().getRequestDispatcher("/EventsManagerHomepage.jsp").forward(request, response);
			}


		}
		
		else if (action.equalsIgnoreCase("listEventsForPassengers")) {
			String feventDate = null;
			String eventDate = request.getParameter("event_date");
			String eventTime = request.getParameter("event_time");
			String eventType = request.getParameter("event_type");
			String currentuser = request.getParameter("current_user");
			System.out.println("Retrived session current username"+currentuser);
			System.out.println("Retrived session event time"+eventTime);
			System.out.println("Retrived session session event type"+eventType);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			
			DateFormat parser = new SimpleDateFormat("MM/dd/yyyy");
			try {
				Date date = (Date) parser.parse(eventDate);
			
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				feventDate = formatter.format(date);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			

			
			session.removeAttribute("errorMsgs");
			event.setEvent("", eventDate, eventTime, "", "", "", "", "", eventType);
			event.validateEventForPassenger(action,  event, eventType, CerrorMsgs);
			if (CerrorMsgs.getM_errorMsg().equals("")) {
				if (!eventDate.equals("") && !eventTime.equals("") && !eventType.equals("")) {
					eventInDB = EventDAO.searchEventForPassenger(feventDate, eventTime,eventType);
					
					for (int i = 0; i < eventInDB.size(); i++) {
						String fdateItem = null;
						String dateItem = eventInDB.get(i).getM_event_date();
						if(dateItem != null) {
							DateFormat p = new SimpleDateFormat("yyyy-MM-dd");
							try {
								Date d = (Date) p.parse(dateItem);
								DateFormat f = new SimpleDateFormat("MM/dd/yyyy");
								fdateItem = f.format(d);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						eventInDB.get(i).setM_event_date(fdateItem);
					}
					
				//	session.setAttribute("EVENTS", );
					System.out.println("SIZE"+eventInDB.size());
					System.out.println("came till here");
					System.out.println(eventInDB.get(0).getM_event_date());
					System.out.println(eventInDB.get(0).getM_start_time());
					System.out.println(eventInDB.get(0).getM_type());
					Event e = eventInDB.get(0);
					System.out.println("THERE IS NO ERROR hence entered this block");
					session.setAttribute("EVENTS", eventInDB);
					
					
					User currentUserReturned=UserDAO.returnUser(currentuser);
					System.out.println("User obj retured"+currentUserReturned);
					session.setAttribute("current_user", currentUserReturned);
					System.out.println("printing from Event controller while going to evemt sum page"+currentUserReturned.getUsername());
					
					url="/EventSummaryPage.jsp";
				}
			} else {
				session.setAttribute("event", event);
				session.setAttribute("eventerrmsg", CerrorMsgs);
				getServletContext().getRequestDispatcher("/passengerhomepage.jsp").forward(request, response);
			}


		}

		else if(action.equals("updateEventCoordinator")) {
			String FirstName = request.getParameter("FirstName");
			String LastName = request.getParameter("LastName");
			String x="jerry";
			EventDAO.update(FirstName,x);
			System.out.println("There are no error msgs, hence im in insert block");
			
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
			try {
				
				getServletContext().getRequestDispatcher("/ListSpecificEvent.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else if(action.equalsIgnoreCase("listSpecificEvent")) { // action=listSpecificCompany
			ArrayList<Event> companyInDB = new ArrayList<Event>();
			Event selectedEvent= new Event();
			
			  if (request.getParameter("radioEvent") != null) 
			  { 
				 
				 String currentuser = request.getParameter("current_user");
				 System.out.println("Printing current user from list spec action ctrl: "+currentuser);
				  selectedEventIndex = Integer.parseInt(request.getParameter("radioEvent")) - 1; 
				  companyInDB=(ArrayList<Event>)session.getAttribute("EVENTS");
				  selectedEvent.setEvent(companyInDB.get(selectedEventIndex).getM_event_name(),
				  companyInDB.get(selectedEventIndex).getM_event_date(),
				  companyInDB.get(selectedEventIndex).getM_start_time(),
				  companyInDB.get(selectedEventIndex).getM_duration(),
				  companyInDB.get(selectedEventIndex).getM_location(),
				  companyInDB.get(selectedEventIndex).getM_numberofattendees(),
				  companyInDB.get(selectedEventIndex).getM_capacity(),
				  companyInDB.get(selectedEventIndex).getM_eventcoordinator(),
				  companyInDB.get(selectedEventIndex).getM_type() ); 
				 
			
				
				  session.setAttribute("Selected_Event", selectedEvent); 
				  System.out.println(selectedEvent.getM_event_name());
				  User currentUserReturned=UserDAO.returnUser(currentuser);
					System.out.println("User obj retured in specific event action ctrl "+currentUserReturned);
					System.out.println(currentUserReturned.getUsername());
					session.setAttribute("current_user", currentUserReturned);
					session.setAttribute("selected_event_obj", selectedEvent);
				  session.setAttribute("eventName", selectedEvent.getM_event_name());
				  url = "/ListSpecificEvent.jsp"; 
			  
			  } 
			/*
			 * else { // without selecting a company // if
			 * (request.getParameter("ListSelectedCompanyButton") != null) { String
			 * errorMsgs = "Please select a company"; session.setAttribute("errorMsgs",
			 * errorMsgs); url = "/EventsManagerHomepage.jsp"; }
			 */
			  
			  
			}
		else if(action.equalsIgnoreCase("updateEvent"))
		{
			getEventParam(request, event);
			event.validateEvent("updateEvent", event, CerrorMsgs);
			String oldEventName = (String) session.getAttribute("eventName");
			if(!CerrorMsgs.getM_errorMsg().equals("")) {
				session.setAttribute("event", event);
				session.setAttribute("errorMsgs", CerrorMsgs);
				url = "/modifyevent.jsp";
			}
			else {
				//CerrorMsgs.setM_errorMsg("Modified Successfully");
					EventDAO.modifyEvent(event, oldEventName);
				session.setAttribute("EVENTS", event);
				url = "/EventSummaryPage.jsp";
				
			}
			
		}
		else if(action.equalsIgnoreCase("updateEvent"))
		{
			getEventParam(request, event);
			event.validateEvent("updateEvent", event, CerrorMsgs);
			String oldEventName = (String) session.getAttribute("eventName");
			if(!CerrorMsgs.getM_errorMsg().equals("")) {
				session.setAttribute("event", event);
				session.setAttribute("errorMsgs", CerrorMsgs);
				url = "/modifyevent.jsp";
			}
			else {
				//CerrorMsgs.setM_errorMsg("Modified Successfully");
					EventDAO.modifyEvent(event, oldEventName);
				session.setAttribute("EVENTS", event);
				url = "/EventSummaryPage.jsp";
				
			}
			
		}
		else if (action.equalsIgnoreCase("Reserve")) {
			System.out.println("Inside Reserve Event block of Evenet Controller");
			getEventParam(request, event);
			Event e=(Event)session.getAttribute("selected_event_obj");
			String no_of_attendees=request.getParameter("attendees");
			e.setM_numberofattendees(no_of_attendees);	
			String currentuser = request.getParameter("current_user");
			e.setEventUser(currentuser);
			event.validateEvent(action, e, CerrorMsgs);
			if (!CerrorMsgs.getM_errorMsg().equals("")) {
				
				System.out.println("Error msgs found");
				session.setAttribute("errorMsgs",CerrorMsgs);
				url="/RegisterEventsInputs.jsp";
			}
			else {// if no error messages
				System.out.println("Printing capacity inside Reserve Clause"+e.getM_capacity());
				System.out.println("Printing capacity inside Reserve Clause"+e.getM_numberofattendees());
				System.out.println("Printing capacity inside Reserve Clause"+e.getM_event_date());
				System.out.println("Printing capacity inside Reserve Clause"+e.getM_event_name());
				System.out.println("Printing capacity inside Reserve Clause"+e.getM_start_time());
				System.out.println("Printing capacity inside Reserve Clause"+e.getM_type());
				System.out.println("Printing capacity inside Reserve Clause"+e.getM_start_time());
			
				EventDAO.insertEvent(e);
				
			System.out.println("Event Succesfully Registered");
			
				url="";
			}
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
			
		}

	}
