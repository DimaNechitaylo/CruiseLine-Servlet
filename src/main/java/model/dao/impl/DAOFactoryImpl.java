package model.dao.impl;

import model.dao.DAOFactory;
import model.dao.OrderDAO;
import model.dao.ShipDAO;
import model.dao.UserDAO;

import model.dao.CruiseDAO;
import model.dao.PortDAO;
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
