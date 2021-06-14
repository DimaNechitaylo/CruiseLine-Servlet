package ua.training.converter.impl;

import ua.training.converter.Mapper;
import ua.training.model.dto.OrderDTO;
import ua.training.model.entity.Order;
import ua.training.model.service.UserService;
import ua.training.model.service.impl.UserServiceImpl;

public class OrderConverter implements Mapper<Order, OrderDTO>{

	CruiseConverter cruiseConverter;
	UserConverter userConverter;
	UserService userService;
	
	public OrderConverter() {
		cruiseConverter = new CruiseConverter();
		userConverter = new UserConverter();
		userService = new UserServiceImpl();
	}

	@Override
    public OrderDTO toDto(Order order) {
		return OrderDTO.builder()
				.id(order.getId())
        		.userId(userConverter.toDto(order.getUser()))
        		.cruiseDto(cruiseConverter.toDto(order.getCruise()))
        		.status(order.getStatus())
        		.build();
    }

    @Override
    public Order toEntity(OrderDTO OrderDTO) {
        return Order.builder()
        		.id(OrderDTO.getId())
        		.user(userConverter.toEntity(OrderDTO.getUserDto()))
        		.cruise(cruiseConverter.toEntity(OrderDTO.getCruiseDto()))
        		.status(OrderDTO.getStatus())
        		.build();
    }
}
