package model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.log4j.Logger;

import model.dao.DBRepository;
import model.dao.OrderDAO;
import model.entity.Cruise;
import model.entity.Order;
import model.entity.OrderStatus;
import model.entity.Ship;
import model.entity.User;

public class OrderDAOImpl extends DBRepository implements OrderDAO {
	private static Logger logger = Logger.getLogger(OrderDAOImpl.class.getName());

	@Override
	public Optional<Order> getOrder(Long id, Locale locale) {
		Order order = null;
		String query = bundle.getString("order.getById");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					order = extractEntity(resultSet, locale);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getOrder(Long id)" + e);
		}
		return Optional.ofNullable(order);
	}
	
	@Override
	public Optional<Order> getOrder(Long cruiseId, Long userId, Locale locale) {
		Order order = null;
		String query = bundle.getString("order.findByCruiseIdAndUserId");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, userId);
			statement.setLong(2, cruiseId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					order = extractEntity(resultSet, locale);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getOrder(Long cruiseId, Long userId)" + e);
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
			logger.error("SQLException in addOrder(Order order)" + e);
			return false;
		}

	}

	@Override
	public boolean updateOrderStatus(Order order) {
		System.out.println(order);
		String query = bundle.getString("order.updateOrderStatus");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, order.getStatus().toString());
			statement.setLong(2, order.getId());
			if(statement.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			logger.error("SQLException in updateOrder(Order order)" + e);
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
			logger.error("SQLException in finishCruises()" + e);
			return false;
		}
	}

	@Override
	public boolean startCruises() {
		String query = bundle.getString("order.startCruises");
		try (PreparedStatement statement1 = connection.prepareStatement(query); 
				PreparedStatement statement2 = connection.prepareStatement(query); 
				PreparedStatement statement3 = connection.prepareStatement(query)) {
			statement1.setString(1, OrderStatus.STARTED.toString());
			statement1.setString(2, OrderStatus.PAID.toString());
			statement2.setString(1, OrderStatus.CANCELED.toString());
			statement2.setString(2, OrderStatus.WATING_PAYMENT.toString());
			statement3.setString(1, OrderStatus.REJECTED.toString());
			statement3.setString(2, OrderStatus.PROCESSING.toString());
			if(statement1.executeUpdate() > 0 | statement2.executeUpdate() > 0) {
				logger.info("Start cruises");
				return true;
			}
			return false;
		} catch (SQLException e) {
			logger.error("SQLException in startCruises()" + e);
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
				while (resultSet.next()) {
					orderStatus = OrderStatus.valueOf(resultSet.getString("s.name"));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findStatusByOrderId(Long orderId)" + e);
		}
		return Optional.ofNullable(orderStatus);
	}

	@Override
	public Optional<List<Order>> findAvailableByStatus(OrderStatus processing, Locale locale) {
		List<Order> orderList = new ArrayList<Order>();
		String query = bundle.getString("order.findAvailableByStatus");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, processing.toString());
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					orderList.add(extractEntity(resultSet, locale));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findByStatus(OrderStatus processing)" + e);
		}
		return Optional.ofNullable(orderList);
	}
	
	@Override
	public Optional<List<Order>> findByStatus(OrderStatus processing, Locale locale) {
		List<Order> orderList = new ArrayList<Order>();
		String query = bundle.getString("order.findByStatus");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, processing.toString());
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					orderList.add(extractEntity(resultSet, locale));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findByStatus(OrderStatus processing)" + e);
		}
		return Optional.ofNullable(orderList);
	}
	
	@Override
	public Optional<Order> findByUserAndIdAndStatusNot(Long userId, Long orderId, OrderStatus status, Locale locale) {
		Order order = Order.builder().build();
		String query = bundle.getString("order.findByUserAndIdAndStatusNot");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, userId);
			statement.setLong(2, orderId);
			statement.setString(3, status.toString());
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					order = extractEntity(resultSet, locale);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findByUserAndIdAndStatusNotFinished(User user, Long order_id, OrderStatus status)");
		}
		return Optional.ofNullable(order);

	}
	

	@Override
	public Optional<Order> findByUserAndIdAndStatus(Long userId, Long orderId, OrderStatus status, Locale locale) {
		Order order = Order.builder().build();
		String query = bundle.getString("order.findByUserAndIdAndStatus");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, userId);
			statement.setLong(2, orderId);
			statement.setString(3, status.toString());
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					order = extractEntity(resultSet, locale);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findByUserAndIdAndStatus(User user, Long order_id, OrderStatus status)");
		}
		return Optional.ofNullable(order);

	}
	

	@Override
	public Optional<Order> findByIdAndStatus(Long orderId, OrderStatus status, Locale locale) {
		Order order = Order.builder().build();
		String query = bundle.getString("order.findByIdAndStatus");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, orderId);
			statement.setString(2, status.toString());
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					order = extractEntity(resultSet, locale);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findByUserAndIdAndStatus(User user, Long order_id, OrderStatus status)");
		}
		return Optional.ofNullable(order);
	}
	

	@Override
	public Optional<List<Order>> findByUser(Long userId, Locale locale) {
		List<Order> orderList = new ArrayList<Order>();
		String query = bundle.getString("order.findByUser");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					orderList.add(extractEntity(resultSet, locale));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findByUser(User user)" + e);
		}
		return Optional.ofNullable(orderList);
	}

	@Override
	public Order extractEntity(ResultSet resultSet, Locale locale) throws SQLException {
		return Order.builder()
				.id(resultSet.getLong("o.id"))
				.user(User.builder()
						.id(resultSet.getLong("u.id"))
						.username(resultSet.getString("u.username"))
						.build())
				.cruise(Cruise.builder()
					.id(resultSet.getLong("c.id"))
					.ship(Ship.builder()
		            		.id(resultSet.getLong("s.id"))
		            		.name(resultSet.getString("s.name"))
		            		.passengerСapacity(resultSet.getInt("s.passenger_сapacity"))
		            		.build())
					.name(resultSet.getString("c.name_"+locale.getLanguage()))
					.description(resultSet.getString("c.description_"+locale.getLanguage()))
					.start(resultSet.getDate("c.start").toLocalDate())
					.finish(resultSet.getDate("c.finish").toLocalDate())
					.build())
				.status(OrderStatus.valueOf(resultSet.getString("st.name")))
				.build();
	}
}
