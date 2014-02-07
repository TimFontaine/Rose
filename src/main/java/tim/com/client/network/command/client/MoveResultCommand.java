/**
 * 
 */
package tim.com.client.network.command.client;

import java.awt.Point;

import tim.com.client.network.command.RoseCommand;
import tim.com.client.shared.service.GameCallHandler;
import tim.com.server.InGameController;
import tim.com.server.ServerGameEventHandler;

/**
 * @author tim
 *
 */
public class MoveResultCommand implements GameEventCommand {
	
	//the new location of the unit
	private Point newLocation;
	//the id of the unit
	private String unitId;

	/**
	 * 
	 */
	public MoveResultCommand() {
		// TODO Auto-generated constructor stub
	}

	

	public Point getNewLocation() {
		return newLocation;
	}

	public void setNewLocation(Point newLocation) {
		this.newLocation = newLocation;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}



	/* (non-Javadoc)
	 * @see tim.com.client.network.command.RoseCommand#handle(tim.com.server.GameCallHandler)
	 */
	@Override
	public void handle(GameCallHandler eventHandler) {
		
	}



	

}
