package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import model.entity.Order;
import model.entity.OrderStatus;

public interface OrderDAO {
	Optional<Order> getOrder(Long id, Locale locale);
	Optional<Order> getOrder(Long cruiseId, Long userId, Locale locale);
	boolean addOrder(Order order);
	boolean deleteOrder(Long id);
    boolean updateOrderStatus(Order order);
	Optional<List<Order>> findByUser(Long userId, Locale locale);
    boolean finishCruises();
    boolean startCruises();
    Optional<OrderStatus> findStatusByOrderId(Long orderId);
	Optional<List<Order>> findByStatus(OrderStatus processing, Locale locale);
	Optional<List<Order>> findAvailableByStatus(OrderStatus processing, Locale locale);
	Optional<Order> findByUserAndIdAndStatusNot(Long userId, Long orderId, OrderStatus status, Locale locale);
	Optional<Order> findByUserAndIdAndStatus(Long userId, Long orderId, OrderStatus status, Locale locale);
	Optional<Order> findByIdAndStatus(Long orderId, OrderStatus status, Locale locale);
	public Order extractEntity(ResultSet resultSet, Locale locale) throws SQLException;
}
