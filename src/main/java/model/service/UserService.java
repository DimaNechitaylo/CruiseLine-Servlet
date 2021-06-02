package model.service;

import model.dto.UserDTO;
import util.exception.UserNotFoundException;

public interface UserService {
    public UserDTO getUser(Long id);
    public UserDTO getUser(String username);
    public UserDTO signUp(UserDTO userDTO);
    public UserDTO signIn(String username, String password) throws UserNotFoundException;
}
