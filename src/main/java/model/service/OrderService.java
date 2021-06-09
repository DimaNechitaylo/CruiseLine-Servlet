package model.service;

import java.util.List;
import java.util.Locale;

import model.dto.OrderDTO;

public interface OrderService {
	public OrderDTO getOrder(Long cruiseId, Long userId, Locale locale);
	public boolean submitOrderRequest(Long cruiseId, Long userId, Locale locale);
	public 	List<OrderDTO> getUserOrders(Long userId, Locale locale);
	public 	List<OrderDTO> getOrdersThatRequireProcessing(Locale locale);
	public boolean pay(Long orderId, Long userId, Locale locale);
	public boolean reject(Long orderId, Long userId, Locale locale);
	public boolean cancel(Long orderId, Long userId, Locale locale);
	public boolean confirm(Long orderId, Long userId, Locale locale);
	public boolean start(Long orderId, Long userId, Locale locale);
	
}
