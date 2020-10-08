package Project.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Project.data.UserDAO;
import Project.model.Event;
import Project.model.EventErrorMsgs;
import Project.model.User;
import Project.model.UserErrorMsgs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
	private static final long serialVersionUID = 1L;
	
	private void getUserParam(HttpServletRequest request, User user) {
		user.setUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("lastname"), 
				request.getParameter("firstname"), request.getParameter("phone"),request.getParameter("email"),
				request.getParameter("roomno"), request.getParameter("deckno"), request.getParameter("memtype"));
	}
	
	/*
	 * private void getUserParam (HttpServletRequest request, User user) {
	 * user.setUser(request.getParameter("username"),
	 * request.getParameter("password"), request.getParameter("lastname"),
	 * request.getParameter("firstname"),
	 * request.getParameter("phone"),request.getParameter("email"),
	 * request.getParameter("roomno"), request.getParameter("deckno"),
	 * request.getParameter("memtype")) }
	 */
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action"), url;
		session.removeAttribute("errorMsgs");
		
			doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in post");
		LOGGER.info("in post");
		String action=request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		User user = new User();
		user.setUser("", "", "", "", "", "", "", "", "");
		UserErrorMsgs UerrorMsgs = new UserErrorMsgs();
		EventErrorMsgs CerrorMsgs = new EventErrorMsgs();
		int selectedUserIndex;
		if(action.equals("login")) {
		user.setUsername(request.getParameter("username"));
		session.setAttribute("currentUsername", user.getUsername());
		user.setPassword(request.getParameter("password"));
		user.validateUser(action, user, UerrorMsgs);
		if (!UerrorMsgs.getErrorMsg().equals("")) {
			session.setAttribute("errorMsgs",UerrorMsgs);
			url="/login.jsp"; 
		}
		else {
			System.out.println("user = "+user.getUsername());
			System.out.println("pass = "+user.getPassword());
			if(user.getUsername().equalsIgnoreCase("EventManager")) {
				String currentdate = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
				String currentTime =new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				Event event = new Event();
				
				event.setM_event_date(currentdate);
				event.setM_start_time(currentTime);
				System.out.println(event.getM_event_date());
				System.out.println(event.getM_start_time());
				event.validateEvent("listEvents", event, CerrorMsgs);
				session.setAttribute("eventerrmsg", CerrorMsgs);
				session.setAttribute("dateevent",event);
				System.out.println("HERE");
				getServletContext().getRequestDispatcher("/EventsManagerHomepage.jsp").forward(request, response);			
				}
			else if (user.getUsername().equalsIgnoreCase("EventCoordinator")) {
				System.out.println("Im here with codri");
				getServletContext().getRequestDispatcher("/EventsCoordinatorHomepage.jsp").forward(request, response);
			}
			
			String currentdate = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
			String currentTime =new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
			Event event = new Event();
			
			event.setM_event_date(currentdate);
			event.setM_start_time(currentTime);
			System.out.println(event.getM_event_date());
			System.out.println(event.getM_start_time());
			event.validateEvent("listEvents", event, CerrorMsgs);
			session.setAttribute("eventerrmsg", CerrorMsgs);
			session.setAttribute("dateevent",event);
			System.out.println("HERE");
			
			ArrayList<User> UserinDB=new ArrayList<User>();
			UserinDB=UserDAO.Searchusername(user.getUsername());
			User seluser=new User();
			try {
				seluser.setUser(UserinDB.get(0).getUsername(), UserinDB.get(0).getPassword(), 
						UserinDB.get(0).getLastname(), UserinDB.get(0).getFirstname(), UserinDB.get(0).getPhone(), 
						UserinDB.get(0).getEmail(), UserinDB.get(0).getRoomno(), UserinDB.get(0).getDeckno(), UserinDB.get(0).getMemtype());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(seluser.getPassword().equals(request.getParameter("password"))) {
				session.setAttribute("currentuser", seluser);
			}
			
			url="/passengerhomepage.jsp";
			
		}
		}
		else if(action.equalsIgnoreCase("updateUser")) {
			getUserParam(request, user);
			String username = request.getParameter("username");
			//String username = request.getParameter("seluser");
			//String id = request.getParameter("id");
			user.validateUser(action, user, UerrorMsgs);
			String oldUsername = (String) session.getAttribute("currentUsername");
			if (!oldUsername.equals(user.getUsername()))
				if (UserDAO.duplicateUser(user.getUsername()))
					UerrorMsgs.setUsernameError("Username already in database");
			UerrorMsgs.setErrorMsg();
			session.setAttribute("user",user);
			if (!UerrorMsgs.getErrorMsg().equals("")) {
				session.setAttribute("errorMsgs",UerrorMsgs);
				url="/updateprofile.jsp";
			}
			else {// if no error messages
				String mtype = UserDAO.getMtype(oldUsername);
				user.setMemtype(mtype);
				if (oldUsername.equals(user.getUsername()))
					UserDAO.modifyUser(user);
				else
					UserDAO.modifyUsername(user, oldUsername);
				session.setAttribute("users", user);
				
				UserErrorMsgs SerrorMsgs = new UserErrorMsgs();
				session.setAttribute("errorMsgs", SerrorMsgs);
				user.setUsername(username);
				
				if (mtype.equalsIgnoreCase("passenger")) {
					session.setAttribute("user", user);
					url="/passengerhomepage.jsp"; 
				}
//				else if (mtype.equals("Caterer Manager")) {
//					session.setAttribute("user", user);
//					url="/mgrHomepage.jsp";
//				}
//				else if (mtype.equals("User")) {
//					session.setAttribute("user", user);
//					url="/userHomepage.jsp"; 
//				}
//				else if (mtype.equals("Caterer Staff")) {
//					session.setAttribute("user", user);
//					url="/CatererStaffHome.jsp";
//				}
				
			}
		}
		else if(action.equals("register")) {
			getUserParam(request, user);
			user.validateUser(action, user, UerrorMsgs);
			System.out.println("inside register");
			//session.setAttribute("user",user);
			//System.out.println(session.getAttribute(user.getFirstname()));
			if (!UerrorMsgs.getErrorMsg().equals("")) {
				System.out.println("hence im not in insert block");
				getUserParam(request,user);
				session.setAttribute("errorMsgs",UerrorMsgs);
				url="/register.jsp"; 
			}
		
			else {// if no error messages
				System.out.println("Before insert");
				UserDAO.insertUser(user);
				System.out.println("There are no error msgs, hence im in insert block");
				UserErrorMsgs SerrorMsgs = new UserErrorMsgs();
				System.out.println("No errors here!");
				session.setAttribute("errorMsgs", SerrorMsgs);
				session.setAttribute("reguser", user);
				
//				try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
				url="/login.jsp";
			}
		}
		//System.out.println(getServletContext().getRequestDispatcher(url));
		try {
			getServletContext().getRequestDispatcher(url).forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
				
			
		}
		//getServletContext().getRequestDispatcher("/index.jsp").forwarsetUsernamed(request, response);
		//UserErrorMsgs UerrorMsgs = new UserErrorMsgs();int selectedUserIndex;
		//String lastname;
		//session.removeAttribute("errorMsgs");
		
		/*
		 * if (action.equals("login")) { //insert employee button pressed String
		 * username = request.getParameter("username"); String password =
		 * request.getParameter("password");
		 * 
		 * user.setUsername(username); user.setPassword(password);
		 * user.validateUser(action, user, UerrorMsgs); if
		 * (!UerrorMsgs.getErrorMsg().equals("")) {
		 * session.setAttribute("errorMsgs",UerrorMsgs); url="/login.jsp"; } else {// if
		 * no error messages System.out.println("user = "+user.getUsername());
		 * System.out.println("pass = "+user.getPassword()); String role =
		 * UserDAO.getRole(user.getUsername(), user.getPassword());
		 * System.out.println("role = "+role); UserErrorMsgs SerrorMsgs = new
		 * UserErrorMsgs(); session.setAttribute("errorMsgs", SerrorMsgs);
		 * user.setUsername(username);
		 * 
		 * if (role.equals("Admin")) { session.setAttribute("user", user);
		 * url="/adminHomepage.jsp"; } else if (role.equals("Caterer Manager")) {
		 * session.setAttribute("user", user); url="/mgrHomepage.jsp"; } else if
		 * (role.equals("User")) { session.setAttribute("user", user);
		 * url="/userHomepage.jsp"; } else if (role.equals("Caterer Staff")) {
		 * session.setAttribute("user", user); url="/StaffHome.jsp"; } }
		 * 
		 * } else if(action.equals("register")) { getUserParam(request, user);
		 * user.validateUser(action, user, UerrorMsgs);
		 * session.setAttribute("user",user); if (!UerrorMsgs.getErrorMsg().equals(""))
		 * { getUserParam(request,user); session.setAttribute("errorMsgs",UerrorMsgs);
		 * url="/register.jsp"; } else {// if no error messages
		 * UserDAO.insertUser(user); UserErrorMsgs SerrorMsgs = new UserErrorMsgs();
		 * session.setAttribute("errorMsgs", SerrorMsgs); url="/index.jsp"; } }
		 */
	
	}


