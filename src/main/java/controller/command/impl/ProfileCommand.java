package controller.command.impl;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import model.dto.OrderDTO;
import model.dto.UserDTO;
import model.service.OrderService;
import model.service.impl.OrderServiceImpl;

public class ProfileCommand implements Command {

	private OrderService orderService;

	public ProfileCommand() {
		this.orderService = new OrderServiceImpl();
	}
	@Override
	public String execute(HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		List<OrderDTO> userOrsers = orderService.getUserOrders(user.getId(),  (Locale) request.getSession().getAttribute("lang"));
		request.getSession().setAttribute("userOrsers", userOrsers);
		return "redirect:CruiseLine-Servlet/profile.jsp";
	}


}
