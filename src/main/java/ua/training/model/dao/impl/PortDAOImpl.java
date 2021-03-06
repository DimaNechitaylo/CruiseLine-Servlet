package ua.training.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

import org.apache.log4j.Logger;

import ua.training.model.dao.DBRepository;
import ua.training.model.dao.PortDAO;
import ua.training.model.entity.Port;

public class PortDAOImpl extends DBRepository implements PortDAO {
	private static Logger logger = Logger.getLogger(PortDAOImpl.class.getName());

	@Override
	public Optional<Port> getPort(Long id, Locale locale) {
		Port port = Port.builder().build();
		String query = bundle.getString("port.getById");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				port = extractEntity(resultSet, locale);
			}
		} catch (SQLException e) {
			logger.error("SQLException in getPort(int id)");
		}
		return Optional.ofNullable(port);
	}

	@Override
	public Optional<Port> getPort(String name, Locale locale) {
		Port port = Port.builder().build();
		String query = bundle.getString("port.getByName");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, name);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					port = extractEntity(resultSet, locale);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getPort(String name)");
		}
		return Optional.ofNullable(port);
	}

	@Override
	public Port extractEntity(ResultSet resultSet, Locale locale) throws SQLException {
		return Port.builder().id(resultSet.getLong("id")).name(resultSet.getString("name_"+locale)).build();
	}
}
