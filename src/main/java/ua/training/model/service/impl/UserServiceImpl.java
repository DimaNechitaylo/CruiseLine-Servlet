package ua.training.model.service.impl;

import ua.training.converter.impl.UserConverter;
import ua.training.model.dao.DAOFactory;
import ua.training.model.dao.impl.DAOFactoryImpl;
import ua.training.model.dto.UserDTO;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.util.PasswordEncoder;
import ua.training.util.exception.UserNotFoundException;

public class UserServiceImpl implements UserService {

	private final UserConverter userConverter;
	private final DAOFactory daoFactory;

	public UserServiceImpl() {
		daoFactory = new DAOFactoryImpl();
		userConverter = new UserConverter();
	}

	@Override
	public UserDTO getUser(Long id) {
		return daoFactory.getUserDAO().getUser(id).map(userConverter::toDto).orElse(UserDTO.builder().build());
	}

	@Override
	public UserDTO getUser(String username) {
		return daoFactory.getUserDAO().getUser(username).map(userConverter::toDto).orElse(UserDTO.builder().build());
	}

	@Override
	public boolean signUp(UserDTO userDTO) {
		userDTO.setPassword(PasswordEncoder.encoge(userDTO.getPassword()));
		return daoFactory.getUserDAO().addUser(userConverter.toEntity(userDTO));
	}

	@Override
	public UserDTO signIn(String username, String password) throws UserNotFoundException {
		User user = daoFactory.getUserDAO().getUser(username)
				.orElseThrow(() -> new UserNotFoundException("User not found with username -" + username));
		if (!PasswordEncoder.validatePassword(password, user.getPassword())) {
			throw new UserNotFoundException(username + " (id:" + user.getId() + ") entered the wrong password");
		}
		return userConverter.toDto(user);
	}

}
