package ua.training.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import ua.training.model.entity.Ship;

public interface ShipDAO {
	Optional<Ship> getShip(Long id);
    public Ship extractEntity(ResultSet resultSet) throws SQLException;
}
