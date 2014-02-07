/**
 * 
 */
package tim.com.client.network;

import tim.com.client.network.command.server.MoveCommand;
import tim.com.client.shared.EventSet;
import tim.com.client.shared.Unit;
import tim.com.client.shared.eventState.MoveState;
import tim.data.back.Direction;

/**
 * @author tim
 *
 */
public class ServerLogic {
	
	ClientConnection client;
	
	ClientConnector serverConnection = new ClientConnector();  
	
	
	/**
	 * 
	 */
	public ServerLogic(ClientConnection client) {
		this.client = client;
	}
	
	public void buildCity(Unit unit) {
//		BuildCityMessage message = new BuildCityMessage(unit);
//		client.sendActionMessage(message);
	}

	/**
	 * @param id
	 * @param direction
	 * @TODO return the eventset with changes
	 */
	public EventSet move(Unit unit, Direction direction) {
		MoveCommand moveCommand = new MoveCommand(unit.getId(), direction);
		EventSet result = serverConnection.sendMessage(moveCommand);
		System.out.println("client move is succes:" + result);
//		MoveState moveState = MoveState.valueOf(result);
		return result;
	}
}
