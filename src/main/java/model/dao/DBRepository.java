package model.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import controller.Servlet;

public class DBRepository {
	private static Logger logger = Logger.getLogger(Servlet.class.getName());
	public static final ResourceBundle bundle = ResourceBundle.getBundle("query", Locale.getDefault());
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
