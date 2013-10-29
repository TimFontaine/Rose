/**
 * 
 */
package tim.com.client.network;

import tim.com.client.shared.Unit;

/**
 * @author tim
 *
 */
public class ServerLogic {
	
	Client client;
	
	/**
	 * 
	 */
	public ServerLogic(Client client) {
		this.client = client;
	}
	
	public void buildCity(Unit unit) {
		BuildCityMessage message = new BuildCityMessage(unit);
		client.sendMessage(message);
	}
}
