package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			UserDTO userDTO = userService.signIn(username, password);
			logger.debug(userDTO);
			request.getSession().setAttribute("user", userDTO);
			return "pages/home.jsp";
		} catch (UserNotFoundException e) {
			logger.error(e);
		}
		return null;
	}

}
