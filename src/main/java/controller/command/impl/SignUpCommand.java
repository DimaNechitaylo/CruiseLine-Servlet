package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import controller.command.DataExtractor;
import model.dto.UserDTO;
import model.service.UserService;
import model.service.impl.UserServiceImpl;

public class SignUpCommand implements Command, DataExtractor<String>{
    private UserService userService;

    public SignUpCommand() {
    	userService = new UserServiceImpl();
    }
    
	@Override
	public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDTO userDTO = userService.signUp(UserDTO.builder()
        		.username(username)
        		.password(password)
        		.build());        
		return "index.jsp";
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
