package ua.training.util;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
	
	@SuppressWarnings("unchecked")
	public static void logIn(HttpServletRequest request, String login) {
		HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
				.getAttribute("loggedUsers");
		loggedUsers.add(login);
		request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
	}

}
