/**
 * 
 */
package tim.com.client;

import org.omg.CORBA.FREE_MEM;

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
	 */
	public static void main(String[] args) {
		new Rose();
	}

}
