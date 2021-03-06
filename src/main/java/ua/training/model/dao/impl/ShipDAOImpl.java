package ua.training.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.log4j.Logger;

import ua.training.model.dao.DBRepository;
import ua.training.model.dao.ShipDAO;
import ua.training.model.entity.Ship;

public class ShipDAOImpl extends DBRepository implements ShipDAO {
    private static Logger logger = Logger.getLogger(ShipDAOImpl.class.getName());

	@Override
	public Optional<Ship> getShip(Long id) {
		Ship ship = Ship.builder().build();
        String query = bundle.getString("ship.getById");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
            	ship = extractEntity(resultSet);
            }
        } catch (SQLException e) {
			logger.error("SQLException in getShip(int id)");	
        }
        return Optional.ofNullable(ship);
	}

	@Override
	public Ship extractEntity(ResultSet resultSet) throws SQLException {
		Ship ship = null;
	        while (resultSet.next()) {
	        	ship = Ship.builder()
	            		.id(resultSet.getLong("id"))
	            		.name(resultSet.getString("name"))
	            		.passengerСapacity(resultSet.getInt("passenger_сapacity"))
	            		.build();
	        }
	        return ship;
	}

}
