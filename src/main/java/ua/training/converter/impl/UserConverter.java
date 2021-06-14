package ua.training.converter.impl;

import ua.training.converter.Mapper;
import ua.training.model.dto.PassengerDTO;
import ua.training.model.dto.UserDTO;
import ua.training.model.entity.User;

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
