package model.service.impl;

import java.time.LocalDate;
import java.util.List;

import model.dto.CruiseDTO;
import model.entity.Cruise;
import model.service.CruiseService;

public class CruiseServiceImpl implements CruiseService {

	@Override
	public List<CruiseDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CruiseDTO> filterByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CruiseDTO> filter(LocalDate start, Long minDuration, Long maxDuration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CruiseDTO> filter(Long minDuration, Long maxDuration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CruiseDTO getCruiseDTO(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CruiseDTO save(CruiseDTO CruiseDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cruise getCruiseByIdNotBookined(Long cruiseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CruiseDTO> getUserCruises() {
		// TODO Auto-generated method stub
		return null;
	}

}
