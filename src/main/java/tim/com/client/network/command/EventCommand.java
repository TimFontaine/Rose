/**
 * 
 */
package tim.com.client.network.command;

import java.util.List;

import tim.com.client.network.GameEvent;
import tim.com.client.network.command.client.GameEventCommand;
import tim.com.client.shared.service.GameCallHandler;
import tim.com.server.ServerGameEventHandler;

/**
 * @author tim
 *
 */
public class EventCommand implements RoseCommand {
	
	private List<GameEventCommand> eventList;

	/**
	 * 
	 */
	public EventCommand() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see tim.com.client.network.command.RoseCommand#handle(tim.com.server.ServerGameEventHandler)
	 */
	@Override
	public void handle(GameCallHandler eventHandler) {
		for (GameEventCommand gameEventCommand: eventList) {
			gameEventCommand.handle(eventHandler);
		}
	}

	/**
	 * @return the eventList
	 */
	public List<GameEventCommand> getEventList() {
		return eventList;
	}


}
