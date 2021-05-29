package controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.Command;
import controller.command.DataExtractor;

public class SignUpCommand implements Command, DataExtractor<String>{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("login", request.getParameter("login"));
		return "pages/home.jsp";
	}
	
	@Override
	public String extractData(HttpServletRequest request) {
		request.getSession().getServletContext();
		return null;
	}
	
    public boolean checkUserIsLogged(HttpServletRequest request, String login) {
    	//TODO
//        @SuppressWarnings("unchecked")  
//		HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
//                .getAttribute("loggedUsers");
//        if (loggedUsers.stream().anyMatch(login::equals)) {
//            return true;
//        }
//        loggedUsers.add(login);
//        request.getSession().getServletContext()
//                .setAttribute("loggedUsers", loggedUsers);
//        return false;
    	return false;
    }



}
