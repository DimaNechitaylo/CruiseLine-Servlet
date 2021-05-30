package model.service;

import model.dto.UserDTO;
import util.exception.UserNotFoundException;

public interface UserService {
    UserDTO getUser(int id);
    UserDTO signUp(UserDTO userDTO);
	UserDTO signIn(String username, String password) throws UserNotFoundException;
}
