/**
 * 
 */
package tim.game;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author tim
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties properties = new Properties();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
		try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
