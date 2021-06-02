package model.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import model.dao.CruiseDAO;
import model.dao.DAOFactory;
import model.dao.DBRepository;
import model.entity.Cruise;
import model.entity.Order;
import model.entity.Port;
import model.entity.Ship;
import model.entity.User;
import util.exception.PortNotFoundException;

public class CruiseDAOImpl extends DBRepository implements CruiseDAO {
	private static Logger logger = Logger.getLogger(CruiseDAOImpl.class.getName());
	private final DAOFactory daoFactory = new DAOFactoryImpl();

	@Override
	public Optional<Cruise> getCruise(Long id) {
		Cruise cruise = Cruise.builder().build();
		String query = bundle.getString("cruise.getById");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					cruise = extractEntity(resultSet);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getCruise(int id)" + e);
		}
		return Optional.ofNullable(cruise);
	}

	@Override
	public boolean addCruise(Cruise user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCruise(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCruise(Cruise user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<List<Cruise>> findAllByStart(LocalDate start) {
		List<Cruise> cruiseList = new ArrayList<>();
		String query = bundle.getString("cruise.findAllByStart");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				statement.setDate(1, Date.valueOf(start));
				while (resultSet.next()) {
					cruiseList.add(extractEntity(resultSet));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findAllByStart(LocalDate start)" + e);
		}
		return Optional.ofNullable(cruiseList);
	}

	@Override
	public Optional<List<Cruise>> findAllByStartAndFinishBetween(LocalDate start, LocalDate finish1,
			LocalDate finish2) {
		List<Cruise> cruiseList = new ArrayList<>();
		String query = bundle.getString("cruise.findAllByFinishMinusStartBetween");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				statement.setDate(1, Date.valueOf(start));
				statement.setDate(2, Date.valueOf(finish1));
				statement.setDate(3, Date.valueOf(finish2));
				while (resultSet.next()) {
					cruiseList.add(extractEntity(resultSet));
				}
			}
		} catch (SQLException e) {
			logger.error(
					"SQLException in  findAllByStartAndFinishBetween(LocalDate start, LocalDate finish1,LocalDate finish2)" + e);
		}
		return Optional.ofNullable(cruiseList);
	}

	@Override
	public Optional<List<Cruise>> findAllByFinishMinusStartBetween(Long minDuration, Long maxDuration) {
		List<Cruise> cruiseList = new ArrayList<>();
		String query = bundle.getString("cruise.findAllByFinishMinusStartBetween");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				statement.setLong(1, minDuration);
				statement.setLong(2, maxDuration);
				while (resultSet.next()) {
					cruiseList.add(extractEntity(resultSet));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findAllByFinishMinusStartBetween()" + e);
		}
		return Optional.ofNullable(cruiseList);
	}

	@Override
	public Optional<Cruise> findByIdNotBookined(Long cruiseId, Long userId) {
		Cruise cruise = Cruise.builder().build();
		String query = bundle.getString("cruise.findByIdNotBookined");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, cruiseId);
			statement.setLong(2, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					cruise = extractEntity(resultSet);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findByIdNotBookined(int id)" + e);
		}
		return Optional.ofNullable(cruise);
	}

	@Override
	public Optional<List<Cruise>> findUserCruisesByOrders(Long userId) {
		List<Cruise> cruiseList = new ArrayList<>();
		String query = bundle.getString("cruise.findUserCruisesByOrders");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				statement.setLong(1, userId);
				while (resultSet.next()) {
					cruiseList.add(extractEntity(resultSet));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findUserCruisesByOrders(Long userId)" + e);
		}
		return Optional.ofNullable(cruiseList);
	}

	@Override
	public Optional<List<Port>> getPortsById(Long id) {
		List<Port> portList = new ArrayList<>();
		String query = bundle.getString("cruise.getPortsById");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) { // TODO optimize
					portList.add(daoFactory.getPortDAO().extractEntity(resultSet));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getPortsById(int id)" + e);
		}
		return Optional.ofNullable(portList);
	}

	@Override
	public List<User> getPassengersById(Long id) {
		List<User> userList = new ArrayList<>();
		String query = bundle.getString("cruise.getPassengersById");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) { // TODO optimize
					userList.add(daoFactory.getUserDAO().extractEntity(resultSet));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getPassengersById(int id)" + e);
		}
		return userList;
	}

	@Override
	public Optional<List<Cruise>> getAvailableCruises() {
		List<Cruise> cruiseList = new ArrayList<Cruise>();
		String query = bundle.getString("cruise.getAvailableCruises");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					cruiseList.add(extractEntity(resultSet));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getAvailableCruises() " + e);
		}
		return Optional.ofNullable(cruiseList);
	}

	@Override
	public Cruise extractEntity(ResultSet resultSet) throws SQLException {
		Cruise cruise = Cruise.builder().build();
			cruise = Cruise.builder()
					.id(resultSet.getLong("c.id"))
					.name(resultSet.getString("c.name"))
					.ship(Ship.builder()
							.id(resultSet.getLong("s.id"))
							.name(resultSet.getString("s.name"))
							.passengerСapacity(resultSet.getInt("s.passenger_сapacity"))
							.build())
					.ports(getPortsById(resultSet.getLong("c.id"))
							.orElseThrow(() -> new PortNotFoundException("the cruise has no ports")))
					.passengers(getPassengersById(resultSet.getLong("c.id")))
					.start(resultSet.getDate("c.start").toLocalDate())
					.finish(resultSet.getDate("c.finish").toLocalDate())
					.build();
		return cruise;
	}

}
