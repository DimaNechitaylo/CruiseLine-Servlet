package ua.training.model.dao.impl;

import ua.training.model.dao.CruiseDAO;
import ua.training.model.dao.DAOFactory;
import ua.training.model.dao.OrderDAO;
import ua.training.model.dao.PortDAO;
import ua.training.model.dao.ShipDAO;
import ua.training.model.dao.UserDAO;
public class DAOFactoryImpl implements DAOFactory{

	public DAOFactoryImpl(){    }
	
	@Override
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	@Override
	public CruiseDAO getCruiseDAO() {
		return new CruiseDAOImpl();
	}

	@Override
	public ShipDAO getShipDAO() {
		return new ShipDAOImpl();
	}

	@Override
	public OrderDAO getOrderDAO() {
		return new OrderDAOImpl();
	}

	@Override
	public PortDAO getPortDAO() {
		return new PortDAOImpl();
	}

}
