package model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import model.dao.DBRepository;
import model.dao.UserDao;
import model.entity.User;

public class UserDaoImpl extends DBRepository  implements UserDao{

	@Override
	public Optional<User> getUser(int id) {
		User user = User.builder().build();
        String query = "select * from user where id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                user = extractEntity(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(user);
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return 0;
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
            		.username(resultSet.getString("username"))
            		.id(resultSet.getLong("id"))
            		.password(resultSet.getString("password"))
            		.build();
        }
        return user;
    }

}
