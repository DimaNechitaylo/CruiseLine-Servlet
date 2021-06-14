package ua.training.controller.command.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import ua.training.controller.command.Command;
import ua.training.util.ResourceManager;

public class ChangeLangCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
        Locale locale = new Locale(request.getParameter("lang"));
        ResourceManager.getInstance().changeLocale(locale);
        request.getSession(false).setAttribute("lang", locale);
		return "redirect:CruiseLine-Servlet";
	}

}
