package model.service;

import java.time.LocalDate;
import java.util.List;

import model.dto.CruiseDTO;

public interface CruiseService {
	List<CruiseDTO> getAvailableCruises();
	public List<CruiseDTO> filterByDate(LocalDate date);
	public List<CruiseDTO> filter(LocalDate start, Long minDuration, Long maxDuration);
	public List<CruiseDTO> filter(Long minDuration, Long maxDuration);
	public CruiseDTO getCruiseDTO(Long id);
	public CruiseDTO getCruiseByIdNotBookined(Long cruiseId, Long userId);
	public List<CruiseDTO> getUserCruises(Long userId);
}
