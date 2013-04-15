/**
 * 
 */
package tim.com.client;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

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
		
		int tilesOnScreenX = (bounds.width / tile_size) + 1;
		int tilesOnScreeny = (bounds.height/ tile_size) + 1;

//		g.setColor(Color.black);
//        g.fillRect(bounds.x, bounds.y,
//                   bounds.width, bounds.height);
		AffineTransform rowTransform = null;
		for (int i= 0; i<tilesOnScreenX; i++) {
			rowTransform = g.getTransform();
			for (int j=0; j<tilesOnScreeny; j++) {
				g.drawRect(0, 0, 50, 50);
				Node node = map.getNode(i, j);
				boolean explored = ((RosePlayer)roseClient.getPlayer()).isTileExplored(node);
				if (!explored) {
					g.drawImage(resourceManager.getImage("unexplored"), 0, 0, null);
				} else {
					if (node.getCity() != null) {
						drawCity(g, node);
					}
					Unit unit = getUnitInFront(node);
					if (unit != null) {
						drawUnit(g, unit);
					}
				}
				g.translate(0, tile_size);
				
			}
			g.setTransform(rowTransform);
			g.translate(tile_size, 0);
			
		}
		
	}
	
	/**
	 * @param g
	 * @param node
	 */
	private void drawCity(Graphics2D g, Node tile) {
		City city = tile.getCity();
		g.drawImage(resourceManager.getImage("factory"), 0, 0, null);
		
	}

	private void drawUnit(Graphics2D g, Unit unit) {
		
		g.drawImage(resourceManager.getImage(unit.getType()), 0, 0, null);
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
