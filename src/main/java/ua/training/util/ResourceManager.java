package ua.training.util;

import java.util.Locale;
import java.util.ResourceBundle;

public final class ResourceManager {
    private static ResourceManager instance;
	
    private final String VALIDATION_PATH = "locale";
    private final String REGULAR_EXPRESION_PATH = "regularExpression";
    private final String DB_QUERY = "query";
    private final String DB_CONFIG = "db_configuration";
    private final String PAGINATION_TOTAL = "pagination.total";

    private ResourceBundle regularExpressionBundle;
    private ResourceBundle dbQueryBundle;
    private ResourceBundle localeBundle;
    private ResourceBundle dbConfigurationBundle;
	public static Integer total;

    private ResourceManager() {
    	regularExpressionBundle = ResourceBundle.getBundle(REGULAR_EXPRESION_PATH, Locale.getDefault());
    	dbQueryBundle = ResourceBundle.getBundle(DB_QUERY, Locale.getDefault());
    	localeBundle = ResourceBundle.getBundle(VALIDATION_PATH, Locale.getDefault());
    	dbConfigurationBundle = ResourceBundle.getBundle(DB_CONFIG, Locale.getDefault());
    	total = Integer.parseInt((String) getDbConfigurationBundle().getObject(PAGINATION_TOTAL));
    }
    
    public static ResourceManager getInstance() {
    	 if (instance == null)
    		 instance = new ResourceManager();
         return instance;
    }
    
    public ResourceBundle getDbQueryBundle() {
		return dbQueryBundle;
	}
    
    public ResourceBundle getDbConfigurationBundle() {
		return dbConfigurationBundle;
	}
    
    public ResourceBundle getRegularExpressionBundle() {
		return regularExpressionBundle;
	}
    
    public void changeLocale(Locale locale) {
    	localeBundle = ResourceBundle.getBundle(VALIDATION_PATH, locale);
    }

}

