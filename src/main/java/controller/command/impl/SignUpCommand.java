package controller.command.impl;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import controller.command.Command;
import controller.command.CommandProperties;
import model.dto.UserDTO;
import model.service.UserService;
import model.service.impl.UserServiceImpl;
import util.exception.UserAlreadyExistsException;
import util.exception.UserNotFoundException;

public class SignUpCommand implements Command{
    private static Logger logger = Logger.getLogger(SignUpCommand.class.getName());

    private UserService userService;

    public SignUpCommand() {
    	userService = new UserServiceImpl();
    }
    
	@Override
	public String execute(HttpServletRequest request) {
		
		if(request.getParameter("username") != null && !Pattern.compile(CommandProperties.bundle.getString("auth.username")).matcher(request.getParameter("username")).find()) {
			request.getSession().setAttribute("invalid_username", "invalid username");
			if(request.getParameter("password") != null && !Pattern.compile(CommandProperties.bundle.getString("auth.password")).matcher(request.getParameter("password")).find()) {
				request.getSession().setAttribute("invalid_password", "invalid password");
			}
			return "redirect:CruiseLine-Servlet/pages/signup.jsp";
		}

		try {
			userService.signUp(UserDTO.builder()
	        		.username(request.getParameter("username"))
	        		.password(request.getParameter("password"))
	        		.build());   
		} catch (UserAlreadyExistsException e) {
			logger.info(e);
			request.getSession().setAttribute("username_alredy_exist", "such username_alredy_exist");
			return "redirect:CruiseLine-Servlet/pages/signup.jsp";
		}
		request.getSession().removeAttribute("username_alredy_exist");
		return "redirect:CruiseLine-Servlet";
	}
}
