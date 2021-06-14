package model.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import util.ResourceManager;

public class DBManager {
	private final String connectionUrl;
	private final String user;
	private final String password;
	private static DBManager manager;
	
	 private DBManager() {
	        ResourceBundle bundle = ResourceManager.getInstance().getDbConfigurationBundle();
	        connectionUrl = bundle.getString("connection.url");
	        user = bundle.getString("user");
	        password = bundle.getString("password");
	    }
	 public static DBManager getInstance() {
	        if (manager == null){
	            manager = new DBManager();
	        }
	        return manager;
	    }

	    public synchronized Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
	    	Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
	        return manager.createConnection();
	    }

	    private Connection createConnection() throws SQLException {
	        return DriverManager.getConnection(connectionUrl, user, password);
	    }
}
