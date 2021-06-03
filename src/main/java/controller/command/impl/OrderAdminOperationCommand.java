package controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.dto.UserDTO;
import model.service.OrderService;
import model.service.impl.OrderServiceImpl;

public class OrderAdminOperationCommand implements Command {
	private OrderService orderService;

	public OrderAdminOperationCommand() {
		this.orderService = new OrderServiceImpl();
	}
	@Override
	public String execute(HttpServletRequest request) {
		if(request.getParameter("order_id") == null) {
			//return error page
		}
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		String orderIdString = request.getParameter("order_id");
		Long orderId = Long.parseLong(orderIdString);

		switch (request.getParameter("operation")) {
		case "confirm":
			orderService.confirm(orderId, user.getId());
			break;
		case "reject":
			orderService.reject(orderId, user.getId());
			break;
		default:
			//return error page
			break;
		}
		return "redirect:CruiseLine-Servlet/pages/admin.jsp";
	}

}
