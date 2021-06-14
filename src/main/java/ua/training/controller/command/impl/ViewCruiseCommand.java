package ua.training.controller.command.impl;

import java.util.Locale;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.training.controller.command.Command;
import ua.training.model.dto.CruiseDTO;
import ua.training.model.dto.UserDTO;
import ua.training.model.service.CruiseService;
import ua.training.model.service.OrderService;
import ua.training.model.service.impl.CruiseServiceImpl;
import ua.training.model.service.impl.OrderServiceImpl;
import ua.training.util.exception.CruiseNotFoundException;
import ua.training.util.exception.OrderNotFoundException;
import ua.training.util.exception.UserNotFoundException;

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
		Locale locale = (Locale) request.getSession().getAttribute("lang");
		try {
			request.setAttribute("cruise", cruiseService.getCruiseDTO(Long.parseLong(request.getParameter("cruise_id")),
					(Locale) request.getSession().getAttribute("lang")));

			UserDTO user = (UserDTO) request.getSession().getAttribute("user");
			if (Objects.isNull(user)) {
				return "pages/view-cruise.jsp";
			}
			request.setAttribute("order",
					orderService.getOrder(Long.parseLong(request.getParameter("cruise_id")), user.getId(), locale));
		} catch (OrderNotFoundException eOrder) {
			logger.info("Order not found");
		} catch (UserNotFoundException eUser) {
			logger.info("User not found");
		} catch (CruiseNotFoundException eUser) {
			logger.info("Cruise not found");
		}
		return "view-cruise.jsp";
	}

}