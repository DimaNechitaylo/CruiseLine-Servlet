package converter.impl;

import java.util.stream.Collectors;

import converter.Mapper;
import model.dto.CruiseDTO;
import model.entity.Cruise;
import model.service.PortService;
import model.service.impl.PortServiceImpl;

public class CruiseConverter implements Mapper<Cruise, CruiseDTO>{
	PortService portService;
	
	public CruiseConverter() {
		portService = new PortServiceImpl();
	}

	@Override
    public CruiseDTO toDto(Cruise cruise) {
        return CruiseDTO.builder()
				.name(cruise.getName())
				.ship(cruise.getShip())
				.passengersCount(cruise.getPassengers()
						.size())
				.availableCount(cruise.getShip().getPassengerСapacity() - cruise.getPassengers().size())
				.start(cruise.getStart())
				.finish(cruise.getFinish())
				.portNames(cruise.getPorts().stream()
        				.map(e -> e.getName())
        				.collect(Collectors.toList()))
        		.build();
    }

    @Override
    public Cruise toEntity(CruiseDTO cruiseDTO) {
        return Cruise.builder()
				.ship(cruiseDTO.getShip())
        		.name(cruiseDTO.getName())
        		.start(cruiseDTO.getStart())
        		.finish(cruiseDTO.getFinish())
        		.ports(cruiseDTO.getPortNames()
        				.stream()
        				.map(e -> portService.getPort(e))
        				.collect(Collectors.toList()))
        		.build();
    }
}
