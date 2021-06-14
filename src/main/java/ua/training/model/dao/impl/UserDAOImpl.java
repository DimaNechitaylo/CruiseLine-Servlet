package ua.training.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Optional;

import org.apache.log4j.Logger;

import ua.training.model.dao.DBRepository;
import ua.training.model.dao.UserDAO;
import ua.training.model.dto.PassengerDTO;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.util.exception.UserAlreadyExistsException;

public class UserDAOImpl extends DBRepository implements UserDAO {
	private static Logger logger = Logger.getLogger(UserDAOImpl.class.getName());

	@Override
	public Optional<User> getUser(Long id) {
		User user = null;
		String query = bundle.getString("user.getById");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					user = extractEntity(resultSet);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getUser(Long id)" + e);
		}
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<User> getUser(String username) {
		User user = null;
		String query = bundle.getString("user.getByUsernameAndPassword");
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, username);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					user = extractEntity(resultSet);
				}
			}
		} catch (SQLException e) {
			logger.error("SQLException in getUser(String username) " + e);
		}
		return Optional.ofNullable(user);
	}

	@Override
	public boolean addUser(User user) {
		String query = bundle.getString("user.add");
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			if (statement.executeUpdate() > 0) {
				return true;
			}
			return false;
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new UserAlreadyExistsException("User with username: " + user.getUsername() + " alredy exist");
		} catch (SQLException e) {
			logger.error("SQLException in addUser(User user) " + e);
			return false;
		}
	}

	@Override
	public User extractEntity(ResultSet resultSet) throws SQLException {
		return User.builder().id(resultSet.getLong("id")).username(resultSet.getString("username"))
				.password(resultSet.getString("password")).role(Role.valueOf(resultSet.getString("r.name"))).build();
	}
	@Override
	public PassengerDTO extractPassenger(ResultSet resultSet) throws SQLException {
		return PassengerDTO.builder().id(resultSet.getLong("id")).username(resultSet.getString("username")).build();
	}
}
