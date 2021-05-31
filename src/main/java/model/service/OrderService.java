package model.service;

import java.util.List;

import model.dto.OrderDTO;

public interface OrderService {
	public OrderDTO getOrderByCruiseIdAndUserId(Long cruiseId);
	public OrderDTO submitOrderRequest(Long cruiseId);
	public List<OrderDTO> getUserOrders();
	public List<OrderDTO> getGetOrderForVerification();
	public OrderDTO pay(Long orderId);
	public OrderDTO reject(Long orderId);
	public OrderDTO cancel(Long orderId);
	public OrderDTO confirm(Long orderId);
	public OrderDTO start(Long orderId);
}
