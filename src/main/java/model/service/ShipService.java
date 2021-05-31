package model.service;

import java.util.List;

import model.dto.ShipDTO;

public interface ShipService {
	public List<ShipDTO> getAll();
	public ShipDTO getShip(Long id);
	public ShipDTO save(ShipDTO shipDto);

}
