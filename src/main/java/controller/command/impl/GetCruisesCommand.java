package controller.command.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import controller.command.Command;
import model.dto.CruiseDTO;
import model.service.CruiseService;
import model.service.impl.CruiseServiceImpl;

public class GetCruisesCommand implements Command {
	private static Logger logger = Logger.getLogger(GetCruisesCommand.class.getName());

	private CruiseService cruiseService;

	public GetCruisesCommand() {
		this.cruiseService = new CruiseServiceImpl();
	}
	@Override
	public String execute(HttpServletRequest request) {
		List<CruiseDTO> cruises = cruiseService.getAvailableCruises();
		request.getSession().setAttribute("cruises", cruises);
		return "redirect:CruiseLine-Servlet";
	}

}
