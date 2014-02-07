/**
 * 
 */
package tim.com.server;


import javax.inject.Inject;

import tim.com.client.Game;
import tim.com.client.shared.RosePlayer;
import tim.com.server.network.PlayerConnection;

/**
 * @author tim
 *
 */
public class ServerPlayer extends RosePlayer {
	
	private PlayerConnection connection;

	/**
	 * @param game
	 */
	public ServerPlayer(Game game) {
		super(game);
	}
	

	public ServerPlayer(PlayerConnection playerConnection) {
		super(null);
	}

	public PlayerConnection getConnection() {
		return connection;
	}

	public void setConnection(PlayerConnection connection) {
		this.connection = connection;
	}

}
