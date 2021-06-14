package ua.training.controller.command.impl;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import ua.training.controller.command.Command;
import ua.training.model.dto.OrderDTO;
import ua.training.model.dto.UserDTO;
import ua.training.model.service.OrderService;
import ua.training.model.service.impl.OrderServiceImpl;

public class ProfileCommand implements Command {

	private OrderService orderService;

	public ProfileCommand() {
		this.orderService = new OrderServiceImpl();
	}
	@Override
	public String execute(HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		request.getSession().setAttribute("userOrsers", orderService.getUserOrders(user.getId(),  (Locale) request.getSession().getAttribute("lang")));
		return "redirect:CruiseLine-Servlet/profile.jsp";
	}


}
