package controller.command.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import controller.command.Command;
import model.dto.CruiseDTO;
import model.service.CruiseService;
import model.service.impl.CruiseServiceImpl;

public class GetFiltredCruisesCommand implements Command {
	private static Logger logger = Logger.getLogger(GetFiltredCruisesCommand.class.getName());

	private CruiseService cruiseService;

	public GetFiltredCruisesCommand() {
		this.cruiseService = new CruiseServiceImpl();
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String date = (String) request.getParameter("date");
		String min = (String) request.getParameter("min_duration");
		String max = (String) request.getParameter("max_duration");
		int minDuration = min.isBlank() ? 0 : Integer.parseInt(min);
		int maxDuration = max.isBlank() ? 10_000 : Integer.parseInt(max);
		logger.debug("min: "+minDuration+";   max: "+maxDuration);
		request.getSession().setAttribute("cruises", date.isBlank() 
														? cruiseService.getFiltredCruises(minDuration, maxDuration) 
														: cruiseService.getFiltredCruises(LocalDate.parse(date), minDuration, maxDuration));

		return "redirect:CruiseLine-Servlet";
	}
}
