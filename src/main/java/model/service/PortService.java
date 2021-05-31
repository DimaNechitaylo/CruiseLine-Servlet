package model.service;

import java.util.List;

import model.entity.Port;

public interface PortService {
	public List<Port> getAll();
	public Port getPort(String name);
}
