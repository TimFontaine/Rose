/**
 * 
 */
package tim.com.server;

import java.util.List;

import javax.inject.Inject;

import tim.com.client.Game;
import tim.com.client.network.command.GameCommand;
import tim.com.client.network.command.RoseCommand;
import tim.com.client.shared.Player;
import tim.com.client.shared.service.GameCallHandler;
import tim.com.server.di.NetworkServer;
import tim.com.server.di.Root;
import tim.com.server.network.PlayerConnection;

/**
 * @author tim
 *
 */
public class PreGameCallHandler extends GameCallHandler {
	
	

	private PreGameController preGameController;
	private NetworkServer networkServer;
	

	/**
	 * 
	 */
	@Inject
	public PreGameCallHandler(NetworkServer networkServer, PreGame preGame ) {
		this.preGameController = preGameController;
	}
	
	public void getGame(Player player) {
		GameCommand command = new GameCommand(game);
		sendToPlayer(player, command);
	}
	
	/**
	 * @param player
	 */
	private void sendToPlayer(Player player, RoseCommand command) {
		ServerPlayer serverPlayer = (ServerPlayer) player;
		PlayerConnection connection = serverPlayer.getConnection();
		connection.sendMessage(command);
	}
}
