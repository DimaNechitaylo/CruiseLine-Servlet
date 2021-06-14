package ua.training.converter.impl;

import ua.training.converter.Mapper;
import ua.training.model.dto.ShipDTO;
import ua.training.model.entity.Ship;

public class ShipConverter implements Mapper<Ship, ShipDTO> {

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
