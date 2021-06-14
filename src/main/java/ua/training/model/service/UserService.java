package ua.training.model.service;

import ua.training.model.dto.UserDTO;
import ua.training.util.exception.UserNotFoundException;

public interface UserService {
    public UserDTO getUser(Long id);
    public UserDTO getUser(String username);
    public boolean signUp(UserDTO userDTO);
    public UserDTO signIn(String username, String password) throws UserNotFoundException;
}
