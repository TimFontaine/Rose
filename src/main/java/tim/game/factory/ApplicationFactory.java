/**
 * 
 */
package tim.game.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author tim
 *
 */
public class ApplicationFactory {
	static ApplicationFactory factory;
	static Properties properties;

	/**
	 * 
	 */
	private ApplicationFactory() {
		InputStream is = getClass().getResourceAsStream("config.properties");
		properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void getInstance() {
		if (properties.getProperty("environment").equals("TEST")) {
			
		} else if (properties.getProperty("environment").equals("DEBUG")) {
			
		}
	}

}
