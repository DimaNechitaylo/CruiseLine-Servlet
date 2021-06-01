package converter.impl;

import org.apache.log4j.Logger;

import converter.Mapper;
import model.dto.UserDTO;
import model.entity.User;
public class UserConverter implements Mapper<User, UserDTO> {
    private static Logger logger = Logger.getLogger(UserConverter.class.getName());

	@Override
    public UserDTO toDto(User user) {
		logger.debug("UsertoDto");
        UserDTO userDTO =  UserDTO.builder()
        		.username(user.getUsername())
        		.password(user.getPassword())
        		.role(user.getRole())
        		.build();
        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
		logger.debug("UserDTOtoEntity");
        User user =  User.builder()
        		.username(userDTO.getUsername())
        		.password(userDTO.getPassword())
        		.role(userDTO.getRole())
        		.build();       
        return user;
    }
	
}
