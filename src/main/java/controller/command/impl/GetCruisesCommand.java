package controller.command.impl;

import java.time.LocalDate;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.service.CruiseService;
import model.service.impl.CruiseServiceImpl;
import util.ResourceManager;

public class GetCruisesCommand implements Command {

	private CruiseService cruiseService;

	public GetCruisesCommand() {
		this.cruiseService = new CruiseServiceImpl();
	}
	@Override
	public String execute(HttpServletRequest request) {
		Locale locale = (Locale) request.getSession().getAttribute("lang");
		request.getSession().setAttribute("pages", cruiseService.getPages());
		Integer page;
		try {
			String pageStr = request.getParameter("page");
			page = Integer.parseInt(pageStr);
		}catch (Exception e) {
			return "redirect:CruiseLine-Servlet/error.jsp";
		}
		
		String date = request.getSession().getAttribute("date") == null ? "" :  (String) request.getSession().getAttribute("date");
		String min = request.getSession().getAttribute("min_duration") == null ? "" :  (String) request.getSession().getAttribute("min_duration");
		String max = request.getSession().getAttribute("max_duration") == null ? "" :  (String) request.getSession().getAttribute("max_duration");
		
		if(!Pattern.compile(ResourceManager.getInstance().getRegularExpressionBundle().getString("filter.min_duration")).matcher(min).find()
				|| !Pattern.compile(ResourceManager.getInstance().getRegularExpressionBundle().getString("filter.max_duration")).matcher(max).find()) {
			request.getSession().setAttribute("invalid_duration", "invalid duration");
			return "redirect:CruiseLine-Servlet/home.jsp";
		}
		request.getSession().removeAttribute("invalid_duration");
		
		int minDuration = min.isBlank() ? 0 : Integer.parseInt(min);
		int maxDuration = max.isBlank() ? 10_000 : Integer.parseInt(max);
		request.getSession().setAttribute("cruises", date.isBlank() 
													? cruiseService.getFiltredCruises(minDuration, maxDuration, page, locale) 
													: cruiseService.getFiltredCruises(LocalDate.parse(date), minDuration, maxDuration, page, locale));
		return "redirect:CruiseLine-Servlet/home.jsp";
	}

}
