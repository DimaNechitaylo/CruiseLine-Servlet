package controller.command.impl;

import java.util.Locale;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import controller.command.Command;
import model.dto.CruiseDTO;
import model.dto.UserDTO;
import model.service.CruiseService;
import model.service.OrderService;
import model.service.impl.CruiseServiceImpl;
import model.service.impl.OrderServiceImpl;
import util.exception.OrderNotFoundException;
import util.exception.UserNotFoundException;

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
		Locale locale =  (Locale) request.getSession().getAttribute("lang");

		CruiseDTO cruise = cruiseService.getCruiseDTO(Long.parseLong(request.getParameter("cruise_id")), (Locale) request.getSession().getAttribute("lang"));
		request.setAttribute("cruise", cruise);

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		if(Objects.isNull(user)) {
			return "pages/view-cruise.jsp";
		}
		try {
			request.setAttribute("order",
					orderService.getOrder(Long.parseLong(request.getParameter("cruise_id")), user.getId(), locale));
		} catch (OrderNotFoundException eOrder) {
			logger.info("Order not found");
		} catch (UserNotFoundException eUser) {
			logger.info("User not found");
		}
		return "pages/view-cruise.jsp";
	}

}