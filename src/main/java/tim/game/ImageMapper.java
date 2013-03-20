/**
 * 
 */
package tim.game;

import java.util.HashMap;

/**
 * @author tfontaine
 *
 */
public class ImageMapper {

	HashMap<String, String> map;
	
	/**
	 * 
	 */
	public ImageMapper() {
		map = new HashMap<String, String>();
		map.put("house", "rect");
		map.put("path", "path");
		map.put("block", "cross");
		map.put("autoroute", "double-road");
		map.put("worker", "builder");
		map.put("construction", "underconstruction");
		map.put("infantry", "soldier");
	}

	public String getImageName(String name) {
		String imageName = map.get(name);
		if (imageName == null) {
			return name;
		}
		return imageName;
	}
	
}
