package model.service;

import java.time.LocalDate;
import java.util.List;

import model.dto.CruiseDTO;

public interface CruiseService {
	List<CruiseDTO> getAvailableCruises();
	public List<CruiseDTO> getFiltredCruises(LocalDate startDate, int minDuration, int maxDuration);
	public List<CruiseDTO> getFiltredCruises(int minDuration, int maxDuration);
	public CruiseDTO getCruiseDTO(Long id);
	public CruiseDTO getCruiseByIdNotBookined(Long cruiseId, Long userId);
	public List<CruiseDTO> getUserCruises(Long userId);
}
