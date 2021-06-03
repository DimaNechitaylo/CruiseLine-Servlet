package model.service;

import java.util.List;

import model.dto.OrderDTO;

public interface OrderService {
	public OrderDTO getOrder(Long cruiseId, Long userId);
	public boolean submitOrderRequest(Long cruiseId, Long userId);
	public List<OrderDTO> getUserOrders(Long userId);
	public List<OrderDTO> getOrdersThatRequireProcessing();
	public boolean pay(Long orderId, Long userId);
	public boolean reject(Long orderId, Long userId);
	public boolean cancel(Long orderId, Long userId);
	public boolean confirm(Long orderId, Long userId);
	public boolean start(Long orderId, Long userId);
}
