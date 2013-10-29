/**
 * 
 */
package tim.com.client.network;

import java.io.Serializable;

import tim.com.client.shared.Unit;
import tim.com.server.InGameController;

/**
 * @author tim
 *
 */
public class BuildCityMessage implements RoseMessage, Serializable {
	
	Unit unit;
	
	public BuildCityMessage(Unit unit) {
		this.unit = unit;
	}

	/* (non-Javadoc)
	 * @see tim.com.client.network.RoseMessage#handle()
	 */
	@Override
	public void handle(InGameController controller) {
		// TODO Auto-generated method stub
		
	}

}
