package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import controller.command.Command;
import model.service.CruiseService;
import model.service.impl.CruiseServiceImpl;

public class MainPageCommand implements Command{
	private static Logger logger = Logger.getLogger(MainPageCommand.class.getName());

	private CruiseService cruiseService;

	public MainPageCommand() {
		this.cruiseService = new CruiseServiceImpl();
	}
	@Override
	public String execute(HttpServletRequest request) {
		request.getSession().setAttribute("pages", cruiseService.getPages());
		return "/";
	}

}
