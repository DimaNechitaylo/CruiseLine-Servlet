package model.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.log4j.Logger;

import model.dao.CruiseDAO;
import model.dao.DAOFactory;
import model.dao.DBRepository;
import model.dto.PassengerDTO;
import model.entity.Cruise;
import model.entity.Port;
import model.entity.Ship;
import util.exception.PortNotFoundException;

public class CruiseDAOImpl extends DBRepository implements CruiseDAO {
	private static Logger logger = Logger.getLogger(CruiseDAOImpl.class.getName());
	private final DAOFactory daoFactory = new DAOFactoryImpl();

	@Override
	public Optional<Cruise> getCruise(Long id, Locale locale) {
		Cruise cruise = Cruise.builder().build();
		String query = bundle.getString("cruise.getById");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					cruise = extractEntity(resultSet, locale);
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
	public Optional<List<Cruise>> findAllByStart(LocalDate startDate, int startRow,int total, Locale locale) {
		List<Cruise> cruiseList = new ArrayList<>();
		String query = bundle.getString("cruise.findAllByStart");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setDate(1, Date.valueOf(startDate));
			statement.setInt(2, startRow);
			statement.setInt(3, total);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					cruiseList.add(extractEntity(resultSet, locale));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findAllByStart(LocalDate start)" + e);
		}
		return Optional.ofNullable(cruiseList);
	}

	@Override
	public Optional<List<Cruise>> findAllByStartAndFinishBetween(LocalDate start, LocalDate finish1,
			LocalDate finish2, int startRow,int total, Locale locale) {
		List<Cruise> cruiseList = new ArrayList<>();
		String query = bundle.getString("cruise.findAllByStartAndFinishBetween");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setDate(1, Date.valueOf(start));
			statement.setDate(2, Date.valueOf(finish1));
			statement.setDate(3, Date.valueOf(finish2));
			statement.setInt(4, startRow);
			statement.setInt(5, total);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					cruiseList.add(extractEntity(resultSet, locale));
				}
			}
		} catch (SQLException e) {
			logger.error(
					"SQLException in  findAllByStartAndFinishBetween(LocalDate start, LocalDate finish1,LocalDate finish2)" + e);
		}
		return Optional.ofNullable(cruiseList);
	}
	
	@Override
	public Long getAllByStartAndFinishBetweenCount(LocalDate start, LocalDate finish1,
			LocalDate finish2) {
		Long cruiseCount = 0L;
		String query = bundle.getString("cruise.getAllByStartAndFinishBetweenCount");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setDate(1, Date.valueOf(start));
			statement.setDate(2, Date.valueOf(finish1));
			statement.setDate(3, Date.valueOf(finish2));
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					cruiseCount = resultSet.getLong(1);
				}
			}
		} catch (SQLException e) {
			logger.error(
					"SQLException in  findAllByStartAndFinishBetween(LocalDate start, LocalDate finish1,LocalDate finish2)" + e);
		}
		return cruiseCount;
	}

	@Override
	public Optional<List<Cruise>> findAllByFinishMinusStartBetween(int minDuration, int maxDuration, int startRow,int total, Locale locale) {
		List<Cruise> cruiseList = new ArrayList<>();
		String query = bundle.getString("cruise.findAllByFinishMinusStartBetween");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, minDuration);
			statement.setInt(2, maxDuration);
			statement.setInt(3, startRow);
			statement.setInt(4, total);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					cruiseList.add(extractEntity(resultSet, locale));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findAllByFinishMinusStartBetween()" + e);
		}
		return Optional.ofNullable(cruiseList);
	}
	
	@Override
	public Long getAllByFinishMinusStartBetweenCount(int minDuration, int maxDuration) {
		Long cruiseCount = 0L;
		String query = bundle.getString("cruise.getAllByFinishMinusStartBetweenCount");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, minDuration);
			statement.setInt(2, maxDuration);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					cruiseCount = resultSet.getLong(1);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getAvailableCruises() " + e);
		}
		return cruiseCount;
	}

	@Override
	public Optional<Cruise> findByIdNotBookined(Long cruiseId, Long userId, Locale locale) {
		Cruise cruise = Cruise.builder().build();
		String query = bundle.getString("cruise.findByIdNotBookined");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, cruiseId);
			statement.setLong(2, userId);
			statement.setLong(3, cruiseId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					cruise = extractEntity(resultSet, locale);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findByIdNotBookined(int id)" + e);
		}
		return Optional.ofNullable(cruise);
	}

	@Override
	public Optional<List<Cruise>> findUserCruisesByOrders(Long userId, Locale locale) {
		List<Cruise> cruiseList = new ArrayList<>();
		String query = bundle.getString("cruise.findUserCruisesByOrders");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					cruiseList.add(extractEntity(resultSet, locale));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in findUserCruisesByOrders(Long userId)" + e);
		}
		return Optional.ofNullable(cruiseList);
	}

	@Override
	public Optional<List<Port>> getPortsByCruiseId(Long cruiseId, Locale locale) {
		List<Port> portList = new ArrayList<>();
		String query = locale.getLanguage() == "uk" 
				? bundle.getString("cruise.getPortsUKByCruiseId") 
						: bundle.getString("cruise.getPortsENByCruiseId");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, cruiseId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) { // TODO optimize
					portList.add(daoFactory.getPortDAO().extractEntity(resultSet, locale));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getPortsById(int id)" + e);
		}
		return Optional.ofNullable(portList);
	}

	@Override
	public List<PassengerDTO> getPassengersById(Long cruiseId) {
		List<PassengerDTO> passengerList = new ArrayList<>();
		String query = bundle.getString("cruise.getPassengersById");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, cruiseId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) { // TODO optimize
					passengerList.add(daoFactory.getUserDAO().extractPassenger(resultSet));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getPassengersById(int id)" + e);
		}
		return passengerList;
	}

	@Override
	public Optional<List<Cruise>> getAvailableCruises(int start,int total, Locale locale) {
		List<Cruise> cruiseList = new ArrayList<Cruise>();
		String query = bundle.getString("cruise.getAvailableCruises");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, start);
			statement.setLong(2, total);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					cruiseList.add(extractEntity(resultSet, locale));
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getAvailableCruises() " + e);
		}
		return Optional.ofNullable(cruiseList);
	}
	
	@Override
	public Long getAvailableCruisesCount() {
		Long cruiseCount = 0L;
		String query = bundle.getString("cruise.getAvailableCruisesCount");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					cruiseCount = resultSet.getLong(1);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getAvailableCruises() " + e);
		}
		logger.debug(cruiseCount);
		return cruiseCount;
	}

	@Override
	public Cruise extractEntity(ResultSet resultSet, Locale locale) throws SQLException {
		Cruise cruise = Cruise.builder().build();
			cruise = Cruise.builder()
					.id(resultSet.getLong("c.id"))
					.name(resultSet.getString("c.name_"+locale.getLanguage()))
					.description(resultSet.getString("c.description_"+locale.getLanguage()))
					.ship(Ship.builder()
							.id(resultSet.getLong("s.id"))
							.name(resultSet.getString("s.name"))
							.passengerСapacity(resultSet.getInt("s.passenger_сapacity"))
							.build())
					.ports(getPortsByCruiseId(resultSet.getLong("c.id"), locale)
							.orElseThrow(() -> new PortNotFoundException("the cruise has no ports")))
					.passengers(getPassengersById(resultSet.getLong("c.id")))
					.start(resultSet.getDate("c.start").toLocalDate())
					.finish(resultSet.getDate("c.finish").toLocalDate())
					.build();
			logger.debug("Cruise^\n"+cruise);
		return cruise;
	}

}
