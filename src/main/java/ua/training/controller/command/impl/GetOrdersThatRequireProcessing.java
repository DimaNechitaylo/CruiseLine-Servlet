package ua.training.controller.command.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import ua.training.controller.command.Command;
import ua.training.model.service.OrderService;
import ua.training.model.service.impl.OrderServiceImpl;

public class GetOrdersThatRequireProcessing implements Command {
	private OrderService orderService;

	public GetOrdersThatRequireProcessing() {
		this.orderService = new OrderServiceImpl();
	}

	@Override
	public String execute(HttpServletRequest request) {
		request.getSession().setAttribute("orders_that_require_processing",
				orderService.getOrdersThatRequireProcessing((Locale) request.getSession().getAttribute("lang")));
		return "redirect:CruiseLine-Servlet/admin.jsp";
	}

}
