package model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.log4j.Logger;

import model.dao.DBRepository;
import model.dao.PortDAO;
import model.entity.Port;

public class PortDAOImpl extends DBRepository implements PortDAO {
	private static Logger logger = Logger.getLogger(PortDAOImpl.class.getName());

	@Override
	public Optional<Port> getPort(Long id) {
		Port port = Port.builder().build();
		String query = bundle.getString("port.getById");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				port = extractEntity(resultSet);
			}
		} catch (SQLException e) {
			logger.error("SQLException in getPort(int id)");
		}
		return Optional.ofNullable(port);
	}

	@Override
	public Optional<Port> getPort(String name) {
		Port port = Port.builder().build();
		String query = bundle.getString("port.getByName");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, name);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					port = extractEntity(resultSet);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getPort(String name)");
		}
		return Optional.ofNullable(port);
	}

	@Override
	public boolean addPort(Port user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePort(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePort(Port user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Port extractEntity(ResultSet resultSet) throws SQLException {
		Port port = null;
		port = Port.builder().id(resultSet.getLong("id")).name(resultSet.getString("name")).build();
		return port;
	}
}
