/**
 * 
 */
package tim.com.client.network;

import tim.com.server.InGameController;

/**
 * @author tim
 *
 */
public interface RoseMessage {

	/**
	 * @param controller
	 */
	void handle(InGameController controller);
	
}
