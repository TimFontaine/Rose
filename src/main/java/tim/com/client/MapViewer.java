/**
 * 
 */
package tim.com.client;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import tim.core.ResourceManager;
import tim.data.back.Node;
import tim.game.Map;
import tim.game.ai.data.MutableResource.Resource;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class MapViewer {
	
	private static int tile_size = 50;
	
	RoseClient roseClient;
	
	ResourceManager resourceManager;
	
	private Unit activeUnit;

	/**
	 * 
	 */
	public MapViewer(RoseClient roseClient) {
		this.roseClient = roseClient;
		resourceManager = ResourceManager.getInstance();
	}

	/**
	 * @param g2d
	 */
	public void display(Graphics2D g2d) {
		drawMap(g2d);
	}

	/**
	 * @param g2d
	 */
	private void drawMap(Graphics2D g) {
		Map map = roseClient.getGame().getMap();
		Rectangle bounds = g.getClipBounds();
		
		int tilesOnScreenX = (bounds.width / 50) + 1;
		int tilesOnScreeny = (bounds.height/ 50) + 1;

//		g.setColor(Color.black);
//        g.fillRect(bounds.x, bounds.y,
//                   bounds.width, bounds.height);
		System.out.println("draw");
		for (int i= 0; i<tilesOnScreenX; i++) {
			for (int j=0; j<tilesOnScreeny; j++) {
				g.drawRect(i * 50, j * 50, 50, 50);
				Node node = map.getNode(i, j);
				boolean explored = ((RosePlayer)roseClient.getPlayer()).isTileExplored(node);
				if (!explored) {
					g.drawImage(resourceManager.getImage("unexplored"), i* tile_size, j*tile_size, null);
				} else {
					Unit unit = getUnitInFront(node);
					if (unit != null) {
						System.out.println("draw unit");
						g.drawImage(resourceManager.getImage(unit.getType()), i* tile_size, j*tile_size, null);
					}
				}
			}
		}
		
	}

	/**
	 * @param node
	 * @return
	 */
	private Unit getUnitInFront(Node tile) {
		if (tile.containsUnit()) {
			return tile.getUnits().get(0);
		}
		return null;
	}

	public tim.com.client.Unit getActiveUnit() {
		return activeUnit;
	}

	public void setActiveUnit(Unit activeUnit) {
		this.activeUnit = activeUnit;
	}
	
}
