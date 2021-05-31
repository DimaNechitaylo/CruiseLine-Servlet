package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import controller.command.Command;

public class ProfileCommand implements Command {
    private static Logger logger = Logger.getLogger(ProfileCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request) {
		logger.info("go to pages");
		return "/pages/profile.jsp";
	}

}
