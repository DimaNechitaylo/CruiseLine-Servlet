package model.service.impl;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import converter.impl.CruiseConverter;
import model.dao.DAOFactory;
import model.dao.impl.DAOFactoryImpl;
import model.dto.CruiseDTO;
import model.entity.Cruise;
import model.service.CruiseService;
import util.exception.CruiseNotFoundException;

public class CruiseServiceImpl implements CruiseService {
    private static Logger logger = Logger.getLogger(CruiseServiceImpl.class.getName());

	private final CruiseConverter cruiseConverter;
    private final DAOFactory daoFactory;

	public CruiseServiceImpl() { 
		daoFactory = new DAOFactoryImpl();
		cruiseConverter = new CruiseConverter();
    }
	
	@Override
	public List<CruiseDTO> getAvailableCruises() {
		return daoFactory.getCruiseDAO()
				.getAvailableCruises()
				.orElseThrow(() -> new CruiseNotFoundException("Not found available cruise list"))
				.stream()
				.map(c -> cruiseConverter.toDto(c))
				.collect(toList());
	}

	@Override
	public List<CruiseDTO> filterByDate(LocalDate date) {
		return daoFactory.getCruiseDAO()
				.findAllByStart(date)
				.orElseThrow(() -> new CruiseNotFoundException("Not found filterByDate cruise list"))
				.stream()
				.map(c -> cruiseConverter.toDto(c))
				.collect(toList());
	}

	@Override
	public List<CruiseDTO> filter(LocalDate start, Long minDuration, Long maxDuration) {
		return daoFactory.getCruiseDAO()
				.findAllByStartAndFinishBetween(start, start.plusDays(minDuration), start.plusDays(maxDuration))
				.orElseThrow(() -> new CruiseNotFoundException("Not found findAllByStartAndFinishBetween cruise list"))
				.stream()
				.map(c -> cruiseConverter.toDto(c))
				.collect(toList());
	}

	@Override
	public List<CruiseDTO> filter(Long minDuration, Long maxDuration) {
		return daoFactory.getCruiseDAO()
				.findAllByFinishMinusStartBetween(minDuration, maxDuration)
				.orElseThrow(() -> new CruiseNotFoundException("Not found findAllByFinishMinusStartBetween cruise list"))
				.stream()
				.map(c -> cruiseConverter.toDto(c))
				.collect(toList());
	}

	@Override
	public CruiseDTO getCruiseDTO(Long id) {
		return cruiseConverter.toDto(daoFactory.getCruiseDAO()
				.getCruise(id)
				.orElseThrow(() -> new CruiseNotFoundException("Cruise not found with id:" + id)));
	}

	@Override
	public CruiseDTO getCruiseByIdNotBookined(Long cruiseId, Long userId) {
		return cruiseConverter.toDto(daoFactory.getCruiseDAO()
				.findByIdNotBookined(cruiseId, userId)
				.orElseThrow(() -> new CruiseNotFoundException("Not bookined cruise not found with id:" + cruiseId)));
	}

	@Override
	public List<CruiseDTO> getUserCruises(Long userId) {
		return daoFactory.getCruiseDAO()
				.findUserCruisesByOrders(userId)
				.orElseThrow(() -> new CruiseNotFoundException("Not found getUserCruises cruise list"))
				.stream()
				.map(c -> cruiseConverter.toDto(c))
				.collect(toList());
	}

}
