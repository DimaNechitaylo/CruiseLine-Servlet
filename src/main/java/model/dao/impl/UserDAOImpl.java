package model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import controller.Servlet;
import model.dao.DBRepository;
import model.dao.UserDAO;
import model.entity.User;

public class UserDAOImpl extends DBRepository  implements UserDAO{
    private static Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
    ResourceBundle bundle = ResourceBundle.getBundle("query", Locale.getDefault());

	@Override
	public Optional<User> getUser(int id) {
		User user = User.builder().build();
        String query = bundle.getString("user.getUserById");
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                user = extractEntity(resultSet);
            }
        } catch (SQLException e) {
			logger.error("SQLException in getUser(int id)");	
        }
        return Optional.ofNullable(user);
	}
	
	@Override
	public Optional<User> getUser(String username){
		User user = User.builder().build();
        String query = bundle.getString("user.getUserByUsernameAndPassword");
		try(PreparedStatement statement = connection.prepareStatement(query)){
			statement.setString(1, username);
			try (ResultSet resultSet = statement.executeQuery()) {
                user = extractEntity(resultSet);
            }
		} catch (SQLException e) {
			logger.error(e + " in getUser(String username, String password)");
		}
		return Optional.ofNullable(user);
	}

	@Override
	public int addUser(User user) {
		int userId = 0;
        String query = bundle.getString("user.addUser");
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    userId = resultSet.getInt(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userId;
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<User> getUser(String login, String pass, boolean isAdmin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsUserWithSuchLogin(String login) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public User extractEntity(ResultSet resultSet) throws SQLException {
        User user = null;
        while (resultSet.next()) {
            user = User.builder()
            		.id(resultSet.getLong("id"))
            		.username(resultSet.getString("username"))
            		.password(resultSet.getString("password"))
            		.build();
        }
        return user;
    }

}
