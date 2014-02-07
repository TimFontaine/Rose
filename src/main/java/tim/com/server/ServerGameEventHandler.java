/**
 * 
 */
package tim.com.server;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import tim.com.client.Game;
import tim.com.client.network.EventMessage;
import tim.com.client.network.GameEvent;
import tim.com.client.network.command.client.GameEventCommand;
import tim.com.client.shared.Player;
import tim.com.client.shared.ServerUnit;
import tim.com.client.shared.Unit;
import tim.com.client.shared.service.GameCallHandler;
import tim.com.server.annotation.InGame;
import tim.com.server.network.PlayerConnection;
import tim.data.back.Direction;

/**
 * @author tim
 * dispatches incoming game calls to the correct game unit
 */
@InGame
public class ServerGameEventHandler extends GameCallHandler {
	
	private Game game;

	/**
	 * 
	 */
	@Inject
	public ServerGameEventHandler(Game game) {
		this.game = game;
	}
	
	@Override
	public void move(String unitId, Direction direction) {
		List<GameEventCommand> eventList = new ArrayList<GameEventCommand>();
		
		ServerUnit unit = (ServerUnit)game.getCurrentPlayer().getUnitById(unitId);
		unit.move(direction, eventList);
		
		//send to all players
		List<Player> players = game.getPlayers();
		for (Player player : players) {
			ServerPlayer serverPlayer = (ServerPlayer) player;
			PlayerConnection connection = serverPlayer.getConnection();
			
			connection.sendMessage(eventList);
		}
		
	}
	

}
