package model.service.impl;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import converter.impl.CruiseConverter;
import model.dao.DAOFactory;
import model.dao.impl.DAOFactoryImpl;
import model.dto.CruiseDTO;
import model.entity.Cruise;
import model.service.CruiseService;
import util.ResourceManager;
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
	public List<CruiseDTO> getAvailableCruises(int page) {
		return daoFactory.getCruiseDAO()
				.getAvailableCruises(page * ResourceManager.total - ResourceManager.total, ResourceManager.total)
				.orElseThrow(() -> new CruiseNotFoundException("Not found available cruise list")).stream()
				.map(c -> cruiseConverter.toDto(c)).collect(toList());
	}

	@Override
	public List<Long> getPages() {
		List<Long> pageNumbers = new ArrayList<Long>();
 		Long rowCount = daoFactory.getCruiseDAO().getAvailableCruisesCount();
		Long pageCount = rowCount / ResourceManager.total + ((rowCount % ResourceManager.total!=0)?1:0);
		for(Long i = 1L; i<= pageCount; i++) {
			pageNumbers.add(i);
		}
		return pageNumbers;
	}
	@Override
	public List<Long> getPages(LocalDate start, int minDuration, int maxDuration) {
		List<Long> pageNumbers = new ArrayList<Long>();
 		Long rowCount = daoFactory.getCruiseDAO().getAllByStartAndFinishBetweenCount(start, start.plusDays(minDuration), start.plusDays(maxDuration));
		Long pageCount = rowCount / ResourceManager.total + ((rowCount % ResourceManager.total!=0)?1:0);
		for(Long i = 1L; i<= pageCount; i++) {
			pageNumbers.add(i);
		}
		return pageNumbers;
	}
	@Override
	public List<Long> getPages(int minDuration, int maxDuration) {
		List<Long> pageNumbers = new ArrayList<Long>();
 		Long rowCount = daoFactory.getCruiseDAO().getAllByFinishMinusStartBetweenCount(minDuration, maxDuration);
		Long pageCount = rowCount / ResourceManager.total + ((rowCount % ResourceManager.total!=0)?1:0);
		for(Long i = 1L; i<= pageCount; i++) {
			pageNumbers.add(i);
		}
		return pageNumbers;
	}

	@Override
	public List<CruiseDTO> getFiltredCruises(LocalDate start, int minDuration, int maxDuration, int page) {
		return daoFactory.getCruiseDAO()
				.findAllByStartAndFinishBetween(start, start.plusDays(minDuration), start.plusDays(maxDuration),
						page * ResourceManager.total - ResourceManager.total, ResourceManager.total)
				.orElseThrow(() -> new CruiseNotFoundException("Not found findAllByStartAndFinishBetween cruise list"))
				.stream().map(c -> cruiseConverter.toDto(c)).collect(toList());
	}

	@Override
	public List<CruiseDTO> getFiltredCruises(int minDuration, int maxDuration, int page) {
		return daoFactory.getCruiseDAO()
				.findAllByFinishMinusStartBetween(minDuration, maxDuration,
						page * ResourceManager.total - ResourceManager.total, ResourceManager.total)
				.orElseThrow(
						() -> new CruiseNotFoundException("Not found findAllByFinishMinusStartBetween cruise list"))
				.stream().map(c -> cruiseConverter.toDto(c)).collect(toList());
	}

	@Override
	public CruiseDTO getCruiseDTO(Long id) {
		return cruiseConverter.toDto(daoFactory.getCruiseDAO().getCruise(id)
				.orElseThrow(() -> new CruiseNotFoundException("Cruise not found with id:" + id)));
	}

	@Override
	public CruiseDTO getCruiseByIdNotBookined(Long cruiseId, Long userId) {
		return cruiseConverter.toDto(daoFactory.getCruiseDAO().findByIdNotBookined(cruiseId, userId)
				.orElseThrow(() -> new CruiseNotFoundException("Not bookined cruise not found with id:" + cruiseId)));
	}

	@Override
	public List<CruiseDTO> getUserCruises(Long userId) {
		return daoFactory.getCruiseDAO().findUserCruisesByOrders(userId)
				.orElseThrow(() -> new CruiseNotFoundException("Not found getUserCruises cruise list")).stream()
				.map(c -> cruiseConverter.toDto(c)).collect(toList());
	}
}
