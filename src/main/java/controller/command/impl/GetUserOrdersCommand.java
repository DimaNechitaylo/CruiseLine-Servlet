package controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import controller.command.Command;
import model.dto.CruiseDTO;
import model.dto.OrderDTO;
import model.dto.UserDTO;
import model.service.OrderService;
import model.service.impl.OrderServiceImpl;

public class GetUserOrdersCommand implements Command {
	private static Logger logger = Logger.getLogger(GetUserOrdersCommand.class.getName());

	private OrderService orderService;

	public GetUserOrdersCommand() {
		this.orderService = new OrderServiceImpl();
	}
	@Override
	public String execute(HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		List<OrderDTO> userOrsers = orderService.getUserOrders(user.getId());
		request.getSession().setAttribute("userOrsers", userOrsers);
		return "redirect:CruiseLine-Servlet/pages/profile.jsp";
	}

}
