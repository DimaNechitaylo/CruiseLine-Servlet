package model.service.impl;

import org.apache.log4j.Logger;

import converter.impl.UserConverter;
import model.dao.DAOFactory;
import model.dao.impl.DAOFactoryImpl;
import model.dto.UserDTO;
import model.entity.User;
import model.service.UserService;
import util.PasswordEncoder;
import util.exception.UserNotFoundException;

public class UserServiceImpl implements UserService{
    private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

	private final UserConverter userConverter;
    private final DAOFactory daoFactory;

	public UserServiceImpl() { 
		daoFactory = new DAOFactoryImpl();
		userConverter = new UserConverter();
    }
	
	@Override
	public UserDTO getUser(Long id) {
		return daoFactory.getUserDAO()
				.getUser(id)
				.map(userConverter::toDto)
				.orElse(UserDTO.builder().build());
	}
	
	@Override
	public UserDTO getUser(String username) {
		return daoFactory.getUserDAO()
				.getUser(username)
				.map(userConverter::toDto)
				.orElse(UserDTO.builder().build());
	}

	@Override
	public UserDTO signUp(UserDTO userDTO) {
		userDTO.setPassword(PasswordEncoder.generatePasswordHash(userDTO.getPassword()));
		return userConverter.toDto(
				daoFactory.getUserDAO()
				.getUser(daoFactory.getUserDAO()
						.addUser(userConverter.toEntity(userDTO)))
						.get()
				);
	}

	@Override
	public UserDTO signIn(String username, String password) throws UserNotFoundException {
		User user = daoFactory.getUserDAO().getUser(username)
				.orElseThrow(() -> new UserNotFoundException("User not found with username -" + username));
		if(!PasswordEncoder.validatePassword(password, user.getPassword())) {
			throw new UserNotFoundException(username + " (id:"+user.getId()+") entered the wrong password");
		}
		return userConverter.toDto(user);
	}
	
}
