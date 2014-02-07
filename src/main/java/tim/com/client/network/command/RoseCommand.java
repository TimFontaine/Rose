/**
 * 
 */
package tim.com.client.network.command;

import tim.com.client.shared.service.GameCallHandler;
import tim.com.server.InGameController;
import tim.com.server.ServerGameEventHandler;

/**
 * @author tim
 *
 */
public interface RoseCommand {

	/**
	 * @param controller
	 */
	void handle(GameCallHandler eventHandler);
	
}
