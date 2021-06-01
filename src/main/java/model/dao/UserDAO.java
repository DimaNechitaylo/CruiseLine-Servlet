package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import model.entity.User;

public interface UserDAO {
	Optional<User> getUser(Long id);
	Optional<User> getUser(String username);
	Long addUser(User user);
	boolean deleteUser(Long id);
    boolean updateUser(User user);
    boolean existsUserWithSuchLogin(String login);
    public User extractEntity(ResultSet resultSet) throws SQLException;
}
