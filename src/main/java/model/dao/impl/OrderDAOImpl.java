package model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import model.dao.DBRepository;
import model.dao.OrderDAO;
import model.entity.Cruise;
import model.entity.Order;
import model.entity.OrderStatus;
import model.entity.Role;
import model.entity.User;

public class OrderDAOImpl extends DBRepository implements OrderDAO {
	private static Logger logger = Logger.getLogger(OrderDAOImpl.class.getName());

	@Override
	public Optional<Order> getOrder(Long id) {
		Order order = Order.builder().build();
		String query = bundle.getString("order.getById");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				order = extractEntity(resultSet);
			}
		} catch (SQLException e) {
			logger.error("SQLException in getOrder(Long id)");
		}
		return Optional.ofNullable(order);
	}

	@Override
	public boolean addOrder(Order order) {
		String query = bundle.getString("order.addOrder");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, order.getStatus().toString());
			statement.setLong(2, order.getUser().getId());
			statement.setLong(3, order.getCruise().getId());
			if(statement.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			logger.error("SQLException in addOrder(Order order)");
			return false;
		}
	}

	@Override
	public boolean deleteOrder(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOrder(Order order) {
		String query = bundle.getString("order.updateOrder");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, order.getStatus().toString());
			statement.setLong(2, order.getUser().getId());
			statement.setLong(3, order.getCruise().getId());
			if(statement.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			logger.error("SQLException in updateOrder(Order order)");
			return false;
		}
	}

	@Override
	public boolean finishCruises() {
		String query = bundle.getString("order.finishCruises");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, OrderStatus.FINISHED.toString());
			statement.setString(2, OrderStatus.STARTED.toString());
			if(statement.executeUpdate() > 0) {
				logger.info("Finish cruises");
				return true;
			}
			return false;
		} catch (SQLException e) {
			logger.error("SQLException in finishCruises()");
			return false;
		}
	}

	@Override
	public boolean startCruises() {
		String query = bundle.getString("order.startCruises");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, OrderStatus.STARTED.toString());
			statement.setString(2, OrderStatus.PAID.toString());
			if(statement.executeUpdate() > 0) {
				logger.info("Start cruises");
				return true;
			}
			return false;
		} catch (SQLException e) {
			logger.error("SQLException in startCruises()");
			return false;
		}
	}

	@Override
	public Optional<OrderStatus> findStatusByOrderId(Long orderId) {
		OrderStatus orderStatus = null;
		String query = bundle.getString("order.findStatusByOrderId");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, orderId);
			try (ResultSet resultSet = statement.executeQuery()) {
				orderStatus = OrderStatus.valueOf(resultSet.getString("s.name"));
			}
		} catch (SQLException e) {
			logger.error("SQLException in findStatusByOrderId(Long orderId)");
		}
		return Optional.ofNullable(orderStatus);
	}

	@Override
	public Optional<List<Order>> findByStatus(OrderStatus processing) {
		List<Order> orderList = Arrays.asList();
		String query = bundle.getString("order.findByStatus");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				statement.setString(1, processing.toString());
				while (resultSet.next()) {
					orderList.add(extractEntity(resultSet));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findByStatus(OrderStatus processing)");
		}
		return Optional.ofNullable(orderList);
	}
	
	@Override
	public Optional<Order> findByUserAndIdAndStatusNot(Long userId, Long orderId, OrderStatus status) {
		Order order = Order.builder().build();
		String query = bundle.getString("order.findByUserAndIdAndStatusNot");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, userId);
			statement.setLong(2, orderId);
			statement.setString(3, status.toString());
			try (ResultSet resultSet = statement.executeQuery()) {
				order = extractEntity(resultSet);
			}
		} catch (SQLException e) {
			logger.error("SQLException in findByUserAndIdAndStatusNotFinished(User user, Long order_id, OrderStatus status)");
		}
		return Optional.ofNullable(order);

	}
	

	@Override
	public Optional<Order> findByUserAndIdAndStatus(Long userId, Long orderId, OrderStatus status) {
		Order order = Order.builder().build();
		String query = bundle.getString("order.findByUserAndIdAndStatus");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, userId);
			statement.setLong(2, orderId);
			statement.setString(3, status.toString());
			try (ResultSet resultSet = statement.executeQuery()) {
				order = extractEntity(resultSet);
			}
		} catch (SQLException e) {
			logger.error("SQLException in findByUserAndIdAndStatus(User user, Long order_id, OrderStatus status)");
		}
		return Optional.ofNullable(order);

	}
	

	@Override
	public Optional<Order> findByIdAndStatus(Long orderId, OrderStatus status) {
		Order order = Order.builder().build();
		String query = bundle.getString("order.findByIdAndStatus");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, orderId);
			statement.setString(2, status.toString());
			try (ResultSet resultSet = statement.executeQuery()) {
				order = extractEntity(resultSet);
			}
		} catch (SQLException e) {
			logger.error("SQLException in findByUserAndIdAndStatus(User user, Long order_id, OrderStatus status)");
		}
		return Optional.ofNullable(order);
	}
	
	@Override
	public Optional<Order> getOrder(Long cruiseId, Long userId) {
		Order order = Order.builder().build();
		String query = bundle.getString("order.findByCruiseIdAndUserId");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, cruiseId);
			statement.setLong(2, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				order = extractEntity(resultSet);
			}
		} catch (SQLException e) {
			logger.error("SQLException in getOrder(Long cruiseId, Long userId)");
		}
		return Optional.ofNullable(order);
	}

	@Override
	public Optional<List<Order>> findByUser(Long userId) {
		List<Order> orderList = Arrays.asList();
		String query = bundle.getString("order.findByUser");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				statement.setLong(1, userId);
				while (resultSet.next()) {
					orderList.add(extractEntity(resultSet));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findByUser(User user)");
		}
		return Optional.ofNullable(orderList);
	}

	@Override
	public Order extractEntity(ResultSet resultSet) throws SQLException {
		Order order = Order.builder().build();
		while (resultSet.next()) {
			order = Order.builder()
					.id(resultSet.getLong("o.id"))
					.user(User.builder()
							.id(resultSet.getLong("u.id"))
							.username(resultSet.getString("u.username"))
							.role(Role.valueOf(resultSet.getString("st.name")))
							.build())
					.cruise(Cruise.builder()
						.id(resultSet.getLong("c.id"))
						.name(resultSet.getString("c.name"))
						.start(resultSet.getDate("c.start").toLocalDate())
						.finish(resultSet.getDate("c.finish").toLocalDate())
						.build())
					.build();
		}
		return order;
	}
}
