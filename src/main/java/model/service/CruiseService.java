package model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import model.dto.CruiseDTO;

public interface CruiseService {
	List<CruiseDTO> getAvailableCruises(int page, Locale locale);
	List<Long> getPages();
	List<Long> getPages(int minDuration, int maxDuration);
	List<Long> getPages(LocalDate start, int minDuration, int maxDuration);
	public List<CruiseDTO> getFiltredCruises(LocalDate startDate, int minDuration, int maxDuration, int page, Locale locale);
	public List<CruiseDTO> getFiltredCruises(int minDuration, int maxDuration, int page, Locale locale);
	public CruiseDTO getCruiseDTO(Long id, Locale locale);
	public CruiseDTO getCruiseByIdNotBookined(Long cruiseId, Long userId, Locale locale);
	public List<CruiseDTO> getUserCruises(Long userId, Locale locale);
}
