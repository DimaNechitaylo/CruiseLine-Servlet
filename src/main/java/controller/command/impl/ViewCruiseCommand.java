package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import controller.command.Command;
import model.dto.CruiseDTO;
import model.dto.OrderDTO;
import model.dto.UserDTO;
import model.service.CruiseService;
import model.service.OrderService;
import model.service.impl.CruiseServiceImpl;
import model.service.impl.OrderServiceImpl;
import util.exception.OrderNotFoundException;

public class ViewCruiseCommand implements Command {
	private static Logger logger = Logger.getLogger(ViewCruiseCommand.class.getName());

	private CruiseService cruiseService;
	private OrderService orderService;

	public ViewCruiseCommand() {
		this.cruiseService = new CruiseServiceImpl();
		this.orderService = new OrderServiceImpl();
	}

	@Override
	public String execute(HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		try {
			request.getSession().setAttribute("order", orderService.getOrder(Long.parseLong(request.getParameter("cruise_id")), user.getId()));

		}catch(OrderNotFoundException e){
			logger.info("Order not found");
		}
		CruiseDTO cruise = cruiseService.getCruiseDTO(Long.parseLong(request.getParameter("cruise_id")));
		request.getSession().setAttribute("cruise", cruise);

//		request.getSession().setAttribute("cruise_"+cruise.getId(), cruise);
		return "pages/view-cruise.jsp";
	}

}
