package model.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import converter.impl.CruiseConverter;
import converter.impl.OrderConverter;
import converter.impl.UserConverter;
import model.dao.DAOFactory;
import model.dao.impl.DAOFactoryImpl;
import model.dto.OrderDTO;
import model.entity.Order;
import model.entity.OrderStatus;
import model.service.CruiseService;
import model.service.OrderService;
import model.service.UserService;
import util.exception.OrderNotFoundException;

public class OrderServiceImpl implements OrderService {
	private static Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

	private final OrderConverter orderConverter;
	private final CruiseConverter cruiseConverter;
	private final UserConverter userConverter;

    private final DAOFactory daoFactory;
    private final CruiseService cruiseService;
    private final UserService userService;

	public OrderServiceImpl() { 
		daoFactory = new DAOFactoryImpl();
		orderConverter = new OrderConverter();
		cruiseConverter = new CruiseConverter();
		userConverter = new UserConverter();
		
		cruiseService = new CruiseServiceImpl();
		userService = new UserServiceImpl();
    }
	@Override
	public OrderDTO getOrder(Long cruiseId, Long userId) {
		return orderConverter.toDto(daoFactory.getOrderDAO()
				.getOrder(cruiseId, userId)
				.orElseThrow(() -> new OrderNotFoundException("Order not found for the argument: cruiseId = "+cruiseId+", userId = "+userId)));
	}

	@Override
	public boolean submitOrderRequest(Long cruiseId, Long userId) {
		return daoFactory.getOrderDAO()
				.addOrder(Order.builder()
						.cruise(cruiseConverter.toEntity(cruiseService.getCruiseByIdNotBookined(cruiseId, userId)))
						.user(userConverter.toEntity(userService.getUser(userId)))
						.status(OrderStatus.PROCESSING)
						.build()
				);
	}

	@Override
	public List<OrderDTO> getUserOrders(Long userId) {
		return daoFactory.getOrderDAO()
				.findByUser(userId)
				.orElseThrow(() -> new OrderNotFoundException("Order list not found for userId: "+ userId))
				.stream()
				.map(o -> orderConverter.toDto(o))
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> getOrdersThatRequireProcessing() {
		return daoFactory.getOrderDAO()
				.findByStatus(OrderStatus.PROCESSING)
				.orElseThrow(() -> new OrderNotFoundException("Order list not found for status "+ OrderStatus.PROCESSING))
				.stream()
				.map(o -> orderConverter.toDto(o))
				.collect(Collectors.toList());
	}

	@Override
	public boolean pay(Long orderId, Long userId) {
		return daoFactory.getOrderDAO().updateOrderStatus(daoFactory.getOrderDAO()
				.findByUserAndIdAndStatus(userId, orderId, OrderStatus.WATING_PAYMENT)
				.orElseThrow(() -> new OrderNotFoundException("Order list not found for orderId "+ orderId))
				.pay());
	}

	@Override
	public boolean reject(Long orderId, Long userId) {
		return daoFactory.getOrderDAO().updateOrderStatus(daoFactory.getOrderDAO()
				.findByIdAndStatus(orderId, OrderStatus.PROCESSING)
				.orElseThrow(() -> new OrderNotFoundException("Order list not found for orderId "+ orderId))
				.reject());
	}

	@Override
	public boolean cancel(Long orderId, Long userId) {
		return daoFactory.getOrderDAO().updateOrderStatus(daoFactory.getOrderDAO()
				.findByUserAndIdAndStatus(userId, orderId, OrderStatus.WATING_PAYMENT)
				.orElseThrow(() -> new OrderNotFoundException("Order list not found for orderId "+ orderId))
				.cancel());
	}

	@Override
	public boolean confirm(Long orderId, Long userId) {
		return daoFactory.getOrderDAO().updateOrderStatus(daoFactory.getOrderDAO()
				.findByIdAndStatus(orderId, OrderStatus.PROCESSING)
				.orElseThrow(() -> new OrderNotFoundException("Order list not found for orderId "+ orderId))
				.confirm());
	}

	@Override
	public boolean start(Long orderId, Long userId) {
		return daoFactory.getOrderDAO().updateOrderStatus(daoFactory.getOrderDAO()
				.findByIdAndStatus(orderId, OrderStatus.PAID)
				.orElseThrow(() -> new OrderNotFoundException("Order list not found for orderId "+ orderId))
				.start());
	}

}
