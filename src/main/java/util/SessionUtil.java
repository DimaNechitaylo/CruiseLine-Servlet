package util;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.dto.UserDTO;

public class SessionUtil {
	public static boolean checkUserIsLogged(HttpServletRequest request, String login) {
		HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
				.getAttribute("loggedUsers");
		if (loggedUsers.stream().anyMatch(login::equals)) {
			return true;
		}
		return false;
	}

	public static void logIn(HttpServletRequest request, String login) {
		HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
				.getAttribute("loggedUsers");
		loggedUsers.add(login);
		request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
	}

	public static void removeUserFromSession(HttpSession httpSession) {
		HashSet<String> loggedUsers = (HashSet<String>) httpSession.getServletContext().getAttribute("loggedUsers");
		UserDTO userDTO = (UserDTO) httpSession.getAttribute("user");
		loggedUsers.remove(userDTO.getUsername());
		httpSession.setAttribute("loggedUsers", loggedUsers);
	}
}
