package ua.training.controller.command.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import ua.training.controller.command.Command;
import ua.training.model.service.CruiseService;
import ua.training.model.service.impl.CruiseServiceImpl;

public class MainPageCommand implements Command{
	private CruiseService cruiseService;

	public MainPageCommand() {
		this.cruiseService = new CruiseServiceImpl();
	}
	@Override
	public String execute(HttpServletRequest request) {
		request.getSession().setAttribute("pages", cruiseService.getPages());
		request.getSession().setAttribute("cruises", cruiseService.getAvailableCruises(1, (Locale) request.getSession().getAttribute("lang")));
		return "redirect:CruiseLine-Servlet/home.jsp";
	}

}
