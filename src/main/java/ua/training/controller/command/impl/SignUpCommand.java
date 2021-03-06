package ua.training.controller.command.impl;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.training.controller.command.Command;
import ua.training.model.dto.UserDTO;
import ua.training.model.service.UserService;
import ua.training.model.service.impl.UserServiceImpl;
import ua.training.util.ResourceManager;
import ua.training.util.exception.UserAlreadyExistsException;

public class SignUpCommand implements Command{
    private static Logger logger = Logger.getLogger(SignUpCommand.class.getName());

    private UserService userService;

    public SignUpCommand() {
    	userService = new UserServiceImpl();
    }
    
	@Override
	public String execute(HttpServletRequest request) {
		ResourceBundle bundle = ResourceManager.getInstance().getRegularExpressionBundle();
		if(request.getParameter("username") != null && !Pattern.compile(bundle.getString("auth.username")).matcher(request.getParameter("username")).find()) {
			request.getSession().setAttribute("invalid_username", "invalid username");
			if(request.getParameter("password") != null && !Pattern.compile(bundle.getString("auth.password")).matcher(request.getParameter("password")).find()) {
				request.getSession().setAttribute("invalid_password", "invalid password");
			}
			return "redirect:CruiseLine-Servlet/signup.jsp";
		}

		try {
			userService.signUp(UserDTO.builder()
	        		.username(request.getParameter("username"))
	        		.password(request.getParameter("password"))
	        		.build());   
		} catch (UserAlreadyExistsException e) {
			logger.info(e);
			request.getSession().setAttribute("username_alredy_exist", "such username_alredy_exist");
			return "redirect:CruiseLine-Servlet/signup.jsp";
		}
		request.getSession().removeAttribute("username_alredy_exist");
		return "redirect:CruiseLine-Servlet/home.jsp";
	}
}
