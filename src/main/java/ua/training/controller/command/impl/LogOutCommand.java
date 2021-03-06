package ua.training.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.training.controller.command.Command;

public class LogOutCommand  implements Command {
    private static Logger logger = Logger.getLogger(LogOutCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		request.getSession().invalidate();
		logger.info("User deleted from session");
		return "redirect:CruiseLine-Servlet/home.jsp";
	}

}
