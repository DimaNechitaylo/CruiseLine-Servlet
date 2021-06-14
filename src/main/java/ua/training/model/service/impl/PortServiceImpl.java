package ua.training.model.service.impl;

import java.util.Locale;

import ua.training.model.dao.DAOFactory;
import ua.training.model.dao.impl.DAOFactoryImpl;
import ua.training.model.entity.Port;
import ua.training.model.service.PortService;
import ua.training.util.exception.PortNotFoundException;

public class PortServiceImpl implements PortService {

    private final DAOFactory daoFactory;

	public PortServiceImpl() { 
		daoFactory = new DAOFactoryImpl();
    }

	@Override
	public Port getPort(Long id, Locale locale) {
		return daoFactory.getPortDAO().getPort(id, locale)
				.orElseThrow(() -> new PortNotFoundException("Port with id = "+id+" not found"));
	}

	@Override
	public Port getPort(String name, Locale locale) {
		return daoFactory.getPortDAO().getPort(name, locale)
				.orElseThrow(() -> new PortNotFoundException("Port with name = "+name+" not found"));
	}

}
