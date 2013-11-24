/**
 * 
 */
package tim.com.client.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import tim.com.client.RoseClient;
import tim.com.client.controller.GUI;
import tim.com.client.shared.City;
import tim.com.client.shared.Node;
import tim.com.client.shared.RosePlayer;
import tim.com.client.shared.Unit;
import tim.core.ResourceManager;
import tim.data.back.TileItem;
import tim.data.back.TileItemContainer;
import tim.game.Map;
import tim.game.ai.data.MutableResource.Resource;
import tim.namespacetest.types.TerrainType;

/**
 * @author tfontaine
 *
 */
public class MapViewer {
	
	private static int tile_size = 50;
	
	private RoseClient roseClient;
	
	private GUI gui;
	
	ResourceManager resourceManager;
	
	private Unit activeUnit;
	
	private Node focus;

	/**
	 * 
	 */
	public MapViewer(RoseClient roseClient, GUI gui) {
		this.roseClient = roseClient;
		this.gui = gui;
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
		AffineTransform baseTransform = g.getTransform();
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
					drawTerrain(g, node);
					drawTileItems(g, node);
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
		g.setTransform(baseTransform);
	}
	
	/**
	 * @param node
	 */
	private void drawTerrain(Graphics2D g, Node node) {
		TerrainType type = node.getTerrainType();
		if (type != null) {
			String id = type.getName();
			Image terrainImage = resourceManager.getImage(id);
			g.drawImage(terrainImage, 0, 0, null);
			if (node.getSource() != null) {
				Image sourceImage = resourceManager.getImage(node.getSource().getName());
				g.drawImage(sourceImage,0,0,null);
			}
			
		}
	}

	/**
	 * @param node
	 */
	private void drawTileItems(Graphics2D g, Node node) {
		TileItemContainer container = node.getItemContainer();
		if (!container.isEmpty()) {
			List<TileItem> tileItems = container.getTileItems();
			for (TileItem tileItem : tileItems) {
				drawTileItem(g, tileItem);
			}
		}
	}

	/**
	 * @param g
	 * @param tileItem
	 */
	private void drawTileItem(Graphics2D g, TileItem tileItem) {
		String id = tileItem.getId();
		Image image =resourceManager.getImage(id);
		g.drawImage(image, 0, 0, null);
	}

	/** 
	 * @param g
	 * @param node
	 */
	private void drawCity(Graphics2D g, Node tile) {
		City city = tile.getCity();
		g.drawImage(resourceManager.getImage("factory"), 0, 0, null);
	}
	
	public  void setFocus(Node tile) {
		focus = tile;
		gui.refresh();
	}

	private void drawUnit(Graphics2D g, Unit unit) {
		
		g.drawImage(resourceManager.getImage(unit.getUnitType().getName()), 0, 0, null);
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

	public Unit getActiveUnit() {
		return activeUnit;
	}

	public void setActiveUnit(Unit activeUnit) {
		this.activeUnit = activeUnit;
		roseClient.updateActions();
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public Node convertToMapTile(int x, int y) {
		int tileX = x / tile_size;
		int tileY = y / tile_size;
		Node node = roseClient.getGame().getMap().getNode(tileX, tileY);
		return node;
	}

	/**
	 * @param tile
	 */
	public void setSelectedTile(Node tile) {
		if (noActiveUnitAt(tile)) {
			if (tile.containsUnit()) {
				Unit unit = tile.getUnits().get(0);
				activeUnit = unit;
			}
			if (tile.getCity() != null) {
				//open city window
				gui.getCanvas().showCityPanel(tile);
			}
		} else {
			//tile with active unit is selected, check for other units
		}
		gui.updateMapControls();
		roseClient.updateActions();
	}
	
	public boolean noActiveUnitAt(Node tile) {
		return activeUnit == null || activeUnit.getTile() != tile;
	}
	
}
