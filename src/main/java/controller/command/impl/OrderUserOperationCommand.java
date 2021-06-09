package controller.command.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import controller.command.Command;
import controller.command.CommandCaller;
import model.dto.UserDTO;
import model.service.OrderService;
import model.service.impl.OrderServiceImpl;

public class OrderUserOperationCommand implements Command {
    private static Logger logger = Logger.getLogger(OrderUserOperationCommand.class.getName());

	private OrderService orderService;

	public OrderUserOperationCommand() {
		this.orderService = new OrderServiceImpl();
	}

	@Override
	public String execute(HttpServletRequest request) {
		logger.debug(request.getParameter("order_id"));
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		if (request.getParameter("cruise_id") != null && request.getParameter("operation").equals("submit")) {
			String cruiseIdString = request.getParameter("cruise_id");
			Long cruiseId = Long.parseLong(cruiseIdString);
			orderService.submitOrderRequest(cruiseId, user.getId(), (Locale) request.getSession().getAttribute("lang"));
		}
		
		if (request.getParameter("order_id") != null && (request.getParameter("operation").equals("pay")
				|| request.getParameter("operation").equals("cancel"))) {
			String orderIdString = request.getParameter("order_id");
			Long orderId = Long.parseLong(orderIdString);
			if (request.getParameter("operation").equals("pay")) {
				orderService.pay(orderId, user.getId(),  (Locale) request.getSession().getAttribute("lang"));
			}
			if (request.getParameter("operation").equals("cancel")) {
				orderService.cancel(orderId, user.getId(),  (Locale) request.getSession().getAttribute("lang"));
			}
		}
		return "redirect:CruiseLine-Servlet/pages/profile.jsp";
	}

}
