package util;

import java.util.Locale;
import java.util.ResourceBundle;

public final class ResourceManager {
    private static ResourceManager instance;
	
    private final String VALIDATION_PATH = "locale";
    private final String REGULAR_EXPRESION_PATH = "regularExpression";
    private final String DB_QUERY = "query";

    private ResourceBundle regularExpressionBundle;
    private ResourceBundle dbQueryBundle;
    private ResourceBundle localeBundle;

    private ResourceManager() {
    	regularExpressionBundle = ResourceBundle.getBundle(REGULAR_EXPRESION_PATH, Locale.getDefault());
    	dbQueryBundle = ResourceBundle.getBundle(DB_QUERY, Locale.getDefault());
    	localeBundle = ResourceBundle.getBundle(VALIDATION_PATH, Locale.getDefault());
    }
    
    public static ResourceManager getInstance() {
    	 if (instance == null)
    		 instance = new ResourceManager();
         return instance;
    }
    
    public ResourceBundle getDbQueryBundle() {
		return dbQueryBundle;
	}
    
    public ResourceBundle getRegularExpressionBundle() {
		return regularExpressionBundle;
	}
    
    public void changeLocale(Locale locale) {
    	localeBundle = ResourceBundle.getBundle(VALIDATION_PATH, locale);
    }

}

