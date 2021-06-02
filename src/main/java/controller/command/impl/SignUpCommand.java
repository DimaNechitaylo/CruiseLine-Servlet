package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import controller.command.DataExtractor;
import model.dto.UserDTO;
import model.service.UserService;
import model.service.impl.UserServiceImpl;

public class SignUpCommand implements Command{
    private UserService userService;

    public SignUpCommand() {
    	userService = new UserServiceImpl();
    }
    
	@Override
	public String execute(HttpServletRequest request) {
        userService.signUp(UserDTO.builder()
        		.username(request.getParameter("username"))
        		.password(request.getParameter("password"))
        		.build());        
		return "index.jsp";
	}
}
