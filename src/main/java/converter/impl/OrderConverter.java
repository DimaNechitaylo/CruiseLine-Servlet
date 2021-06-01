package converter.impl;

import converter.Mapper;
import model.dto.OrderDTO;
import model.entity.Order;
import model.service.UserService;
import model.service.impl.UserServiceImpl;

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
        		.userId(order.getUser().getId())
        		.cruiseDto(cruiseConverter.toDto(order.getCruise()))
        		.status(order.getStatus())
        		.build();
    }

    @Override
    public Order toEntity(OrderDTO OrderDTO) {
        return Order.builder()
        		.user(userConverter.toEntity(userService.getUser(OrderDTO.getUserId())))
        		.cruise(cruiseConverter.toEntity(OrderDTO.getCruiseDto()))
        		.status(OrderDTO.getStatus())
        		.build();
    }
}
