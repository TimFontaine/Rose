/**
 * 
 */
package tim.data.back;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tfontaine
 *
 */
public class TileItemContainer {
	
	private List<TileItem> items;

	/**
	 * 
	 */
	public TileItemContainer() {
		items = new ArrayList<TileItem>();
	}
	
	public void addTileItem(TileItem tileItem) {
		items.add(tileItem);
	}
	
	public boolean contains(TileItem tileItem) {
		return items.contains(tileItem);
	}

}
