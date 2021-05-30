package converter.impl;

import converter.AbstractConvertor;
import model.dto.UserDTO;
import model.entity.User;

public class UserConverter  extends AbstractConvertor<User, UserDTO> {

	public UserConverter(Class<User> entityClass, Class<UserDTO> dtoClass) {
		super(entityClass, dtoClass);
	}

	@Override
    public UserDTO toDto(User user) {
        UserDTO userDTO =  UserDTO.builder()
        		.username(user.getUsername())
        		.password(user.getPassword())
        		.build();
        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        User user =  User.builder()
        		.username(userDTO.getUsername())
        		.password(userDTO.getPassword())
        		.build();       
        return user;
    }
	
}
