package converter.impl;

import converter.Mapper;
import model.dto.PassengerDTO;
import model.dto.UserDTO;
import model.entity.User;

public class UserConverter implements Mapper<User, UserDTO> {
	@Override
	public UserDTO toDto(User user) {
		return UserDTO.builder().id(user.getId()).username(user.getUsername()).password(user.getPassword())
				.role(user.getRole()).build();
	}

	@Override
	public User toEntity(UserDTO userDTO) {
		return User.builder().id(userDTO.getId()).username(userDTO.getUsername()).password(userDTO.getPassword())
		.role(userDTO.getRole()).build();
	}

	public PassengerDTO toPassengerDto(User user) {
		return PassengerDTO.builder().id(user.getId()).username(user.getUsername()).build();
	}

}
