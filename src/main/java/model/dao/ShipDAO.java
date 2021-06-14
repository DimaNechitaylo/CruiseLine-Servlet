package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import model.entity.Ship;

public interface ShipDAO {
	Optional<Ship> getShip(Long id);
    public Ship extractEntity(ResultSet resultSet) throws SQLException;
}
