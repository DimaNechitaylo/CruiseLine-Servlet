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
    boolean updateOrder(Order order);
    Optional<List<Order>> findByUser(User user);
    boolean finishCruises();
    boolean startCruises();
    Optional<OrderStatus> findStatusByOrderId(Long orderId);
	Optional<List<Order>> findByStatus(OrderStatus processing);
	Optional<Order> findByUserAndIdAndStatusNotStatus(User user, Long order_id, OrderStatus status);
    public Order extractEntity(ResultSet resultSet) throws SQLException;
}
