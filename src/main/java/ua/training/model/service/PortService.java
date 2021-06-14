package ua.training.model.service;

import java.util.Locale;

import ua.training.model.entity.Port;

public interface PortService {
	Port getPort(Long id, Locale locale);
	Port getPort(String name, Locale locale);
}
