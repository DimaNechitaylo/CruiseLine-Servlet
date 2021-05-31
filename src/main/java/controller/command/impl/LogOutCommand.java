package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import controller.command.Command;

public class LogOutCommand  implements Command {
    private static Logger logger = Logger.getLogger(LogOutCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request) {
		request.getSession().removeAttribute("username");
		logger.info("User deleted from session");
		return "/index.jsp";
	}

}
