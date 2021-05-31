package model.service;

import java.time.LocalDate;
import java.util.List;

import model.dto.CruiseDTO;
import model.entity.Cruise;

public interface CruiseService {
	List<CruiseDTO> getAll();
	public List<CruiseDTO> filterByDate(LocalDate date);
	public List<CruiseDTO> filter(LocalDate start, Long minDuration, Long maxDuration);
	public List<CruiseDTO> filter(Long minDuration, Long maxDuration);
	public CruiseDTO getCruiseDTO(Long id);
	public CruiseDTO save(CruiseDTO CruiseDTO);
	public Cruise getCruiseByIdNotBookined(Long cruiseId);
	public List<CruiseDTO> getUserCruises();
}
