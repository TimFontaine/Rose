/**
 * 
 */
package tim.com.client.network.command;

import tim.com.client.Game;
import tim.com.client.shared.service.GameCallHandler;

/**
 * @author tim
 *
 */
public class StartGameCommand implements RoseCommand {
	
	Game game;

	/**
	 * 
	 */
	public StartGameCommand() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see tim.com.client.network.command.RoseCommand#handle(tim.com.server.GameCallHandler)
	 */
	@Override
	public void handle(GameCallHandler eventHandler) {
		eventHandler.setGame(game);
	}

}
