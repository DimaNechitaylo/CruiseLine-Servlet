package converter.impl;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import converter.Mapper;
import model.dto.CruiseDTO;
import model.entity.Cruise;
import model.entity.Port;
import model.service.PortService;
import model.service.impl.PortServiceImpl;

public class CruiseConverter implements Mapper<Cruise, CruiseDTO>{
	PortService portService;
	
	public CruiseConverter() {
		portService = new PortServiceImpl();
	}

	@Override
    public CruiseDTO toDto(Cruise cruise) {
		System.out.println(cruise);
        return CruiseDTO.builder()
        		.id(cruise.getId())
				.name(cruise.getName())
				.ship(cruise.getShip())
				.passengersCount(Objects.isNull(cruise.getPassengers()) ? 0 :
						cruise.getPassengers().size())
				.availableCount(cruise.getShip().getPassengerСapacity() - (Objects.isNull(cruise.getPassengers()) ? 0 :
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
        		.start(cruiseDTO.getStart())
        		.finish(cruiseDTO.getFinish())
        		.ports(Objects.isNull(cruiseDTO.getPortNames()) ? new ArrayList<Port>() :
        				cruiseDTO.getPortNames().stream()
        				.map(e -> portService.getPort(e))
        				.collect(Collectors.toList()))
        		.build();
    }
}
