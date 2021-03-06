package ua.training.converter.impl;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import ua.training.converter.Mapper;
import ua.training.model.dto.CruiseDTO;
import ua.training.model.entity.Cruise;

public class CruiseConverter implements Mapper<Cruise, CruiseDTO>{
	
	@Override
    public CruiseDTO toDto(Cruise cruise) {
        return CruiseDTO.builder()
        		.id(cruise.getId())
				.name(cruise.getName())
				.description(cruise.getDescription())
				.ship(cruise.getShip())
				.passengersCount(Objects.isNull(cruise.getPassengers()) ? 0 :
						cruise.getPassengers().size())
				.availableCount(cruise.getShip().getPassengerĐˇapacity() - (Objects.isNull(cruise.getPassengers()) ? 0 :
					cruise.getPassengers().size()))
				.start(cruise.getStart())
				.finish(cruise.getFinish())
				.portNames(Objects.isNull(cruise.getPorts()) ? new ArrayList<String>() :
						cruise.getPorts().stream()
        				.map(e -> e.getName())
        				.collect(Collectors.toList()))
        		.build();
    }

    @Override
    public Cruise toEntity(CruiseDTO cruiseDTO) {
        return Cruise.builder()
        		.id(cruiseDTO.getId())
				.ship(cruiseDTO.getShip())
        		.name(cruiseDTO.getName())
				.description(cruiseDTO.getDescription())
        		.start(cruiseDTO.getStart())
        		.finish(cruiseDTO.getFinish())
        		.build();
    }
}
