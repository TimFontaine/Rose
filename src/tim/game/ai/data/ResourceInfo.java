/**
 * 
 */
package tim.game.ai.data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tfontaine
 *
 */
public class ResourceInfo {
	
	private Map<String, String> location;
	private static ResourceInfo INSTANCE;

	/**
	 * 
	 */
	private ResourceInfo() {
		location = new HashMap<String, String>();
		location.put("iron", "mine");
		location.put("oil", "oilwell");
	}
	
	public static ResourceInfo getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ResourceInfo();
		}
		return INSTANCE;
	}
	
	public  String getLocation(String name) {
		return location.get(name);
	}

}
