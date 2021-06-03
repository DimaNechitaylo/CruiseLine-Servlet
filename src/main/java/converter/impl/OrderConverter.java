package converter.impl;

import org.apache.log4j.Logger;

import converter.Mapper;
import model.dto.OrderDTO;
import model.entity.Order;
import model.service.UserService;
import model.service.impl.UserServiceImpl;

public class OrderConverter implements Mapper<Order, OrderDTO>{
	private static Logger logger = Logger.getLogger(OrderConverter.class.getName());

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
