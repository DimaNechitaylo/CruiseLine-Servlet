package ua.training.model.service.impl;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ua.training.converter.impl.CruiseConverter;
import ua.training.model.dao.DAOFactory;
import ua.training.model.dao.impl.DAOFactoryImpl;
import ua.training.model.dto.CruiseDTO;
import ua.training.model.service.CruiseService;
import ua.training.util.ResourceManager;
import ua.training.util.exception.CruiseNotFoundException;

public class CruiseServiceImpl implements CruiseService {

	private final CruiseConverter cruiseConverter;
	private final DAOFactory daoFactory;

	public CruiseServiceImpl() {
		daoFactory = new DAOFactoryImpl();
		cruiseConverter = new CruiseConverter();
	}

	@Override
	public List<CruiseDTO> getAvailableCruises(int page, Locale locale) {
		return daoFactory.getCruiseDAO()
				.getAvailableCruises(page * ResourceManager.total - ResourceManager.total, ResourceManager.total, locale)
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
	public List<CruiseDTO> getFiltredCruises(LocalDate start, int minDuration, int maxDuration, int page, Locale locale) {
		return daoFactory.getCruiseDAO()
				.findAllByStartAndFinishBetween(start, start.plusDays(minDuration), start.plusDays(maxDuration),
						page * ResourceManager.total - ResourceManager.total, ResourceManager.total, locale)
				.orElseThrow(() -> new CruiseNotFoundException("Not found findAllByStartAndFinishBetween cruise list"))
				.stream().map(c -> cruiseConverter.toDto(c)).collect(toList());
	}

	@Override
	public List<CruiseDTO> getFiltredCruises(int minDuration, int maxDuration, int page, Locale locale) {
		return daoFactory.getCruiseDAO()
				.findAllByFinishMinusStartBetween(minDuration, maxDuration,
						page * ResourceManager.total - ResourceManager.total, ResourceManager.total, locale)
				.orElseThrow(
						() -> new CruiseNotFoundException("Not found findAllByFinishMinusStartBetween cruise list"))
				.stream().map(c -> cruiseConverter.toDto(c)).collect(toList());
	}

	@Override
	public CruiseDTO getCruiseDTO(Long id, Locale locale) {
		return cruiseConverter.toDto(daoFactory.getCruiseDAO().getCruise(id, locale)
				.orElseThrow(() -> new CruiseNotFoundException("Cruise not found with id:" + id)));
	}

	@Override
	public CruiseDTO getCruiseByIdNotBookined(Long cruiseId, Long userId, Locale locale) {
		return cruiseConverter.toDto(daoFactory.getCruiseDAO().findByIdNotBookined(cruiseId, userId, locale)
				.orElseThrow(() -> new CruiseNotFoundException("Not bookined cruise not found with id:" + cruiseId)));
	}

	@Override
	public List<CruiseDTO> getUserCruises(Long userId, Locale locale) {
		return daoFactory.getCruiseDAO().findUserCruisesByOrders(userId, locale)
				.orElseThrow(() -> new CruiseNotFoundException("Not found getUserCruises cruise list")).stream()
				.map(c -> cruiseConverter.toDto(c)).collect(toList());
	}
}
