/**
 * 
 */
package tim.game;

import tim.data.back.Node;

/**
 * @author tfontaine
 *
 */
public class MapBack {

	private static MapBack INSTANCE;
	private Map map;
	
	/**
	 * 
	 */
	private MapBack() {
		map = Back.getInstance().getMap();
	}
	
	public static MapBack getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MapBack();
		}
		return INSTANCE;
	}
	
	public Node getNode(int x, int y) {
		return map.getNode(x, y);
	}

}
