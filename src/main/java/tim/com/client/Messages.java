/**
 * 
 */
package tim.com.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author tfontaine
 *
 */
public class Messages {
	
	static Properties messageBundle;
	
	public static void loadMessages() throws IOException {
		messageBundle = new Properties();
		InputStream is = new FileInputStream("data\\actions.properties");
		messageBundle.load(is);
	}

	/**
	 * @param acceleratorKey
	 */
	public static String message(String acceleratorKey) {
		System.out.println("loading key:" + acceleratorKey);
		return messageBundle.getProperty(acceleratorKey);
	}
}
