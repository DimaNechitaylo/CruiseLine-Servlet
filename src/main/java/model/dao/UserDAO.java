package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import model.dto.PassengerDTO;
import model.entity.User;

public interface UserDAO {
	Optional<User> getUser(Long id);
	Optional<User> getUser(String username);
	boolean addUser(User user);
    public User extractEntity(ResultSet resultSet) throws SQLException;
    public PassengerDTO extractPassenger(ResultSet resultSet)  throws SQLException;
}
