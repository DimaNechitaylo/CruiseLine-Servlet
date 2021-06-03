package controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.dto.OrderDTO;
import model.dto.UserDTO;
import model.service.OrderService;
import model.service.impl.OrderServiceImpl;

public class GetOrdersThatRequireProcessing implements Command {
	private OrderService orderService;

	public GetOrdersThatRequireProcessing() {
		this.orderService = new OrderServiceImpl();
	}
	@Override
	public String execute(HttpServletRequest request) {
		List<OrderDTO> ordersThatRequireProcessing = orderService.getOrdersThatRequireProcessing();
		request.getSession().setAttribute("orders_that_require_processing", ordersThatRequireProcessing);
		return "redirect:CruiseLine-Servlet/pages/admin.jsp";
	}

}
