package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

import model.entity.Port;

public interface PortDAO {
	Optional<Port> getPort(Long id, Locale locale);
	Optional<Port> getPort(String name, Locale locale);
	boolean addPort(Port user);
	boolean deletePort(Long id);
    boolean updatePort(Port user);
    public Port extractEntity(ResultSet resultSet, Locale locale) throws SQLException;
}
