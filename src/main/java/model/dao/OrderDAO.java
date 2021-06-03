package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import model.entity.Order;
import model.entity.OrderStatus;
import model.entity.User;

public interface OrderDAO {
	Optional<Order> getOrder(Long id);
	Optional<Order> getOrder(Long cruiseId, Long userId);
	boolean addOrder(Order order);
	boolean deleteOrder(Long id);
    boolean updateOrderStatus(Order order);
    Optional<List<Order>> findByUser(Long userId);
    boolean finishCruises();
    boolean startCruises();
    Optional<OrderStatus> findStatusByOrderId(Long orderId);
	Optional<List<Order>> findAvailableByStatus(OrderStatus processing);
	Optional<Order> findByUserAndIdAndStatusNot(Long userId, Long orderId, OrderStatus status);
	Optional<Order> findByUserAndIdAndStatus(Long userId, Long orderId, OrderStatus status);
	Optional<Order> findByIdAndStatus(Long orderId, OrderStatus status);
	public Order extractEntity(ResultSet resultSet) throws SQLException;
}
