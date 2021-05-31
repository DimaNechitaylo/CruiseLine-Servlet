package model.dao.impl;

import model.dao.DAOFactory;
import model.dao.UserDAO;

public class DAOFactoryImpl implements DAOFactory{
	
	public DAOFactoryImpl(){    }
	
	@Override
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

}
