package controller.command.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import controller.Servlet;
import controller.command.Command;
import util.ResourceManager;

public class ChangeLangCommand implements Command {
    private static Logger logger = Logger.getLogger(ChangeLangCommand.class.getName());

	@Override
	public String execute(HttpServletRequest request) {
        Locale locale = new Locale(request.getParameter("lang"));
        ResourceManager.getInstance().changeLocale(locale);
        request.getSession(false).setAttribute("lang", locale);
		return "redirect:CruiseLine-Servlet";
	}

}
