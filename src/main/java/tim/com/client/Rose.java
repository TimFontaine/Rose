/**
 * 
 */
package tim.com.client;

import java.io.IOException;

/**
 * @author tfontaine
 *
 */
public class Rose {

	/**
	 * 
	 */
	public Rose() {
		RoseClient roseClient = new RoseClient();
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Messages.loadMessages();
		new Rose();
	}

}
