/**
 * 
 */
package tim.com.client.network.command.server;

import java.io.Serializable;

import tim.com.client.network.command.RoseCommand;
import tim.com.client.shared.service.GameCallHandler;
import tim.com.server.InGameController;
import tim.com.server.ServerGameEventHandler;
import tim.data.back.Direction;

/**
 * @author tim
 *
 */
public class MoveCommand implements RoseCommand, Serializable {
	
	String unitId;
	Direction direction;
	

	/**
	 * 
	 */
	public MoveCommand(String unitId, Direction direction) {
		this.direction = direction;
		this.unitId = unitId;
	}

	/* (non-Javadoc)
	 * @see tim.com.client.network.RoseMessage#handle(tim.com.server.InGameController)
	 */
//	@Override
	public void handle(ServerGameEventHandler eventHandler) {
		eventHandler.move(unitId, direction);
	}

	/* (non-Javadoc)
	 * @see tim.com.client.network.command.RoseCommand#handle(tim.com.client.shared.service.GameCallHandler)
	 */
	@Override
	public void handle(GameCallHandler eventHandler) {
		// TODO Auto-generated method stub
		
	}

}
