package model.dao.impl;

import model.dao.DAOFactory;
import model.dao.UserDAO;

public class DAOFactoryImpl implements DAOFactory{
	
	public DAOFactoryImpl(){    }
	
	@Override
	public UserDAO getUserDAO() {
		System.out.println("\n\n-----UserDAO-----\n\n");

		return new UserDAOImpl();
	}

}
