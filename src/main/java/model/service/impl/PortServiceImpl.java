package model.service.impl;

import org.apache.log4j.Logger;

import model.dao.DAOFactory;
import model.dao.impl.DAOFactoryImpl;
import model.entity.Port;
import model.service.PortService;
import util.exception.PortNotFoundException;

public class PortServiceImpl implements PortService {
    private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    private final DAOFactory daoFactory;

	public PortServiceImpl() { 
		daoFactory = new DAOFactoryImpl();
    }

	@Override
	public Port getPort(Long id) {
		return daoFactory.getPortDAO().getPort(id)
				.orElseThrow(() -> new PortNotFoundException("Port with id = "+id+" not found"));
	}

	@Override
	public Port getPort(String name) {
		return daoFactory.getPortDAO().getPort(name)
				.orElseThrow(() -> new PortNotFoundException("Port with name = "+name+" not found"));
	}

}
