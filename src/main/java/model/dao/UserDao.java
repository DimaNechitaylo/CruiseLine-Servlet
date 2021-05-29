package model.dao;

import java.util.Optional;

import model.entity.User;

public interface UserDao {
	Optional<User> getUser(int id);
    int addUser(User user);
    void deleteUser(String id);
    void updateUser(User user);
    Optional<User> getUser(String login, String pass, boolean isAdmin);
    boolean existsUserWithSuchLogin(String login);
}
