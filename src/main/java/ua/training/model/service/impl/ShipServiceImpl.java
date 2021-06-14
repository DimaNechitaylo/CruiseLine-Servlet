package ua.training.model.service.impl;

import ua.training.converter.impl.ShipConverter;
import ua.training.model.dao.DAOFactory;
import ua.training.model.dao.impl.DAOFactoryImpl;
import ua.training.model.dto.ShipDTO;
import ua.training.model.entity.Ship;
import ua.training.model.service.ShipService;
import ua.training.util.exception.ShipNotFoundException;

public class ShipServiceImpl implements ShipService {

	private final ShipConverter shipConverter;
	private final DAOFactory daoFactory;

	public ShipServiceImpl() { 
			daoFactory = new DAOFactoryImpl();
			shipConverter = new ShipConverter();
	    }

	@Override
	public ShipDTO getShip(Long id) {
		Ship ship = daoFactory.getShipDAO().getShip(id)
				.orElseThrow(() -> new ShipNotFoundException("Ship not found with id -" + id));
		return shipConverter.toDto(ship);
	}

}
