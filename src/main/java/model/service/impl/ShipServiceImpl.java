package model.service.impl;

import converter.impl.ShipConverter;
import model.dao.DAOFactory;
import model.dao.impl.DAOFactoryImpl;
import model.dto.ShipDTO;
import model.entity.Ship;
import model.service.ShipService;
import util.exception.ShipNotFoundException;

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
