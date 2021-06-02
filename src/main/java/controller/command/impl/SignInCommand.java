package controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import controller.command.Command;
import model.dao.impl.UserDAOImpl;
import model.dto.UserDTO;
import model.service.UserService;
import model.service.impl.UserServiceImpl;
import util.exception.UserNotFoundException;

public class SignInCommand implements Command {
    private static Logger logger = Logger.getLogger(SignInCommand.class.getName());

	private UserService userService;

	public SignInCommand() {
		this.userService = new UserServiceImpl();
	}

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			UserDTO userDTO = userService.signIn(request.getParameter("username"), 
					request.getParameter("password"));
			session.setAttribute("user", userDTO);
		} catch (UserNotFoundException e) {
			logger.info(e);
		}
		return "redirect:CruiseLine-Servlet";
	}

}
