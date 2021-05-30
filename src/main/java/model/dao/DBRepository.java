package model.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import controller.Servlet;

public class DBRepository {
	private static Logger logger = Logger.getLogger(Servlet.class.getName());

	protected Connection connection;

	public DBRepository() {
			try {
				connection = DBManager.getInstance().getConnection();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException
					| SQLException e) {
				logger.error(e + " during db connection");
			}
		
	}
}
