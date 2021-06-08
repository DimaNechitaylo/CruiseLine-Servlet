package model.service;

import java.time.LocalDate;
import java.util.List;

import model.dto.CruiseDTO;

public interface CruiseService {
	List<CruiseDTO> getAvailableCruises(int page);
	List<Long> getPages();
	List<Long> getPages(int minDuration, int maxDuration);
	List<Long> getPages(LocalDate start, int minDuration, int maxDuration);
	public List<CruiseDTO> getFiltredCruises(LocalDate startDate, int minDuration, int maxDuration, int page);
	public List<CruiseDTO> getFiltredCruises(int minDuration, int maxDuration, int page);
	public CruiseDTO getCruiseDTO(Long id);
	public CruiseDTO getCruiseByIdNotBookined(Long cruiseId, Long userId);
	public List<CruiseDTO> getUserCruises(Long userId);
}
