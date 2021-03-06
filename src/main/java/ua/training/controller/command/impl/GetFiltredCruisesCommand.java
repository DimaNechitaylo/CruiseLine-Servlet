package ua.training.controller.command.impl;

import java.time.LocalDate;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import ua.training.controller.command.Command;
import ua.training.model.service.CruiseService;
import ua.training.model.service.impl.CruiseServiceImpl;
import ua.training.util.ResourceManager;

public class GetFiltredCruisesCommand implements Command {

	private CruiseService cruiseService;

	public GetFiltredCruisesCommand() {
		this.cruiseService = new CruiseServiceImpl();
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		Locale locale = (Locale) request.getSession().getAttribute("lang");

		String date = (String) request.getParameter("date");
		String min = (String) request.getParameter("min_duration");
		String max = (String) request.getParameter("max_duration");
		Integer page;
		try {
			String pageStr = request.getParameter("page");
			page = Integer.parseInt(pageStr);
		}catch (Exception e) {
			return "redirect:CruiseLine-Servlet/error.jsp";
		}
		if(!Pattern.compile(ResourceManager.getInstance().getRegularExpressionBundle().getString("filter.min_duration")).matcher(min).find()
				|| !Pattern.compile(ResourceManager.getInstance().getRegularExpressionBundle().getString("filter.max_duration")).matcher(max).find()) {
			request.getSession().setAttribute("invalid_duration", "invalid duration");
			return "redirect:CruiseLine-Servlet/home.jsp";
		}

		int minDuration = min.isBlank() ? 0 : Integer.parseInt(min);
		int maxDuration = max.isBlank() ? 10_000 : Integer.parseInt(max);
		request.getSession().setAttribute("cruises", date.isBlank() 
														? cruiseService.getFiltredCruises(minDuration, maxDuration, page, locale)
														: cruiseService.getFiltredCruises(LocalDate.parse(date), minDuration, maxDuration, page,locale));
		request.getSession().removeAttribute("invalid_duration");
		request.getSession().setAttribute("pages", cruiseService.getPages(minDuration, maxDuration));
		if(!date.isBlank()) {
			request.getSession().setAttribute("date", date);
			request.getSession().setAttribute("pages", cruiseService.getPages(LocalDate.parse(date), minDuration, maxDuration));
		}
		if(!min.isBlank()) {
			request.getSession().setAttribute("min_duration", min);

		}
		if(!max.isBlank()) {
			request.getSession().setAttribute("max_duration", max);
		}
		return "redirect:CruiseLine-Servlet/home.jsp";
	}
}
