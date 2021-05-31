package converter.impl;

import converter.AbstractConvertor;
import model.dto.ShipDTO;
import model.entity.Ship;

public class ShipConverter extends AbstractConvertor<Ship, ShipDTO> {


	public ShipConverter(Class<Ship> entityClass, Class<ShipDTO> dtoClass) {  //TODO delete
		super(entityClass, dtoClass);
		// TODO Auto-generated constructor stub
	}

	@Override
    public ShipDTO toDto(Ship ship) {
        return ShipDTO.builder()
				.name(ship.getName())
				.passengerСapacity(ship.getPassengerСapacity())
        		.build();
    }

    @Override
    public Ship toEntity(ShipDTO shipDTO) {
        return Ship.builder()
				.name(shipDTO.getName())
				.passengerСapacity(shipDTO.getPassengerСapacity())
        		.build();  
    }

}
