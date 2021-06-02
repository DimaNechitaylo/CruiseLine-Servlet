package model.service;

import model.entity.Port;

public interface PortService {
	public Port getPort(Long id);
	public Port getPort(String name);
}
