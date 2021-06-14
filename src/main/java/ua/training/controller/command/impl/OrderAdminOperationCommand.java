package ua.training.controller.command.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import ua.training.controller.command.Command;
import ua.training.model.dto.UserDTO;
import ua.training.model.service.OrderService;
import ua.training.model.service.impl.OrderServiceImpl;

public class OrderAdminOperationCommand implements Command {
	private OrderService orderService;

	public OrderAdminOperationCommand() {
		this.orderService = new OrderServiceImpl();
	}
	@Override
	public String execute(HttpServletRequest request) {
		Locale locale =  (Locale) request.getSession().getAttribute("lang");
		if(request.getParameter("order_id") == null) {
			return "redirect:CruiseLine-Servlet/error.jsp";
		}
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		String orderIdString = request.getParameter("order_id");
		Long orderId = Long.parseLong(orderIdString);

		switch (request.getParameter("operation")) {
		case "confirm":
			orderService.confirm(orderId, user.getId(), locale);
			break;
		case "reject":
			orderService.reject(orderId, user.getId(), locale);
			break;
		default:
			//return error page
			break;
		}
		return "redirect:CruiseLine-Servlet/main?action=get_orders_that_require_processing";
	}

}
