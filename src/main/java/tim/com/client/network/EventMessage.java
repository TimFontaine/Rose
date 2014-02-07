/**
 * 
 */
package tim.com.client.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tim.com.client.network.command.client.GameEventCommand;
import tim.com.client.shared.EventSet;

/**
 * @author tim
 *
 */
public class EventMessage extends Message {
	
	private List<GameEventCommand> eventList;

	public List<GameEventCommand> getEventList() {
		return eventList;
	}

	public void setEventList(List<GameEventCommand> eventList) {
		this.eventList = eventList;
	}


}
