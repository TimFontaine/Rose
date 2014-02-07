/**
 * 
 */
package tim.com.client.shared;

import java.util.Map;

import tim.com.client.network.GameEvent;

/**
 * @author tim
 *
 */
public class EventSet {
	
	private String response;
	private Map<String, GameEvent> gameEvents;

	/**
	 * 
	 */
	public EventSet() {
		// TODO Auto-generated constructor stub
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Map<String, GameEvent> getGameEvents() {
		return gameEvents;
	}

	public void setGameEvents(Map<String, GameEvent> gameEvents) {
		this.gameEvents = gameEvents;
	}

}
