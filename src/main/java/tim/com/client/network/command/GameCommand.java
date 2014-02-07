/**
 * 
 */
package tim.com.client.network.command;

import tim.com.client.Game;
import tim.com.client.shared.service.GameCallHandler;
import tim.com.server.ServerGameEventHandler;

/**
 * @author tim
 *
 */
public class GameCommand implements RoseCommand {

	/**
	 * 
	 */
	public GameCommand(Game game) {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see tim.com.client.network.command.RoseCommand#handle(tim.com.server.ServerGameEventHandler)
	 */
	@Override
	public void handle(GameCallHandler eventHandler) {
		// TODO Auto-generated method stub

	}

}
