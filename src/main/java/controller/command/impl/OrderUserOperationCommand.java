package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.dto.UserDTO;
import model.service.OrderService;
import model.service.impl.OrderServiceImpl;

public class OrderUserOperationCommand implements Command {
	private OrderService orderService;

	public OrderUserOperationCommand() {
		this.orderService = new OrderServiceImpl();
	}

	@Override
	public String execute(HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		if (request.getParameter("cruise_id") != null && request.getParameter("operation").equals("submit")) {
			String cruiseIdString = request.getParameter("cruise_id");
			Long cruiseId = Long.parseLong(cruiseIdString);
			orderService.submitOrderRequest(cruiseId, user.getId());
		}
		
		if (request.getParameter("order_id") != null && (request.getParameter("operation").equals("pay")
				|| request.getParameter("operation").equals("cancel"))) {
			String orderIdString = request.getParameter("order_id");
			Long orderId = Long.parseLong(orderIdString);
			if (request.getParameter("operation").equals("pay")) {
				orderService.pay(orderId, user.getId());
			}
			if (request.getParameter("operation").equals("cancel")) {
				orderService.cancel(orderId, user.getId());
			}
		}

		return "redirect:CruiseLine-Servlet/pages/view-cruise.jsp";
	}

}
