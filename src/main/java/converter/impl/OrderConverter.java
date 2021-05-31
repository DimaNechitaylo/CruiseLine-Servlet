package converter.impl;

import converter.AbstractConvertor;
import model.dto.CruiseDTO;
import model.dto.OrderDTO;
import model.dto.UserDTO;
import model.entity.Cruise;
import model.entity.Order;
import model.entity.User;
import model.service.UserService;
import model.service.impl.UserServiceImpl;

public class OrderConverter  extends AbstractConvertor<Order, OrderDTO>{

	CruiseConverter cruiseConverter;
	UserConverter userConverter;
	UserService userService;
	
	public OrderConverter(Class<Order> entityClass, Class<OrderDTO> dtoClass) {  //TODO delete
		super(entityClass, dtoClass);
		
		cruiseConverter = new CruiseConverter(Cruise.class, CruiseDTO.class);
		userConverter = new UserConverter(User.class, UserDTO.class);
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
