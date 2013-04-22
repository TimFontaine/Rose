/**
 * 
 */
package tim.com.client;

import java.awt.Point;

import tim.data.back.Direction;
import tim.data.back.Node;
import tim.data.back.TileImprovement;
import tim.data.back.TileImprovementType;
import tim.data.back.TileInfo.Selection;
import tim.game.Player;

/**
 * @author tfontaine
 *
 */
public class InGameController {
	
	private RoseClient client;
	
	private GUI gui;
	
	

	/**
	 * 
	 */
	public InGameController(RoseClient client, GUI gui) {
		this.gui = gui;
		this.client = client;
	}
	
	public void move(Direction direction) {
		Unit unit = gui.getActiveUnit();
		move(unit, direction);
	}
	
	public void move(Unit unit, Direction direction) {
		Point newLocation = translateMove(direction);
		boolean legal = testMoveLegal(newLocation);
		if (!legal) {
			return;
		}
		Node node = client.getGame().getMap().getNode(newLocation.x, newLocation.y);
		unit.setLocation(node);
		
		//move done, refesh screen
		gui.fullRefresh();
	}
	
	/**
	 * do basic user checks
	 */
	public void buildCity() {
		Unit unit = gui.getActiveUnit();
		buildCity(unit);
		
		gui.fullRefresh();
	}
	
	/**
	 * @TODO move to server layer
	 * @param unit
	 */
	private void buildCity(Unit unit) {
		Node tile = unit.getTile();
		Player player = client.getPlayer();
		City city = new City(player, tile);
		city.placeCity();
	}

	/**
	 * @TODO move to class unit
	 * @param newLocation
	 * @return
	 */
	private boolean testMoveLegal(Point location) {
		return client.getGame().getMap().isValid(location.x, location.y);
	}

	/**
	 *@TODO move this function to unit or node/tile class
	 */
	private Point translateMove(Direction direction) {
		Point newLocation = gui.getActiveUnit().getLocation().getPosition();
		switch (direction) {
		case DOWN:
			newLocation.y++;
			break;
		case UP:
			newLocation.y--;
			break;
		case LEFT:
			newLocation.x--;
			break;
		case RIGHT:
			newLocation.x++;
			break;
		default:
			break;
		}
		
		return newLocation;
	}

	/**
	 * @param string
	 */
	public void buildingOrder(City city, String buildingName) {
		city.contstructBuilding(buildingName);
	}

	/**
	 * @param type
	 */
	public void buildTileImprovement(TileImprovementType type) {
		Unit unit = gui.getActiveUnit();
		Node tile = unit.getTile();
		//test can improvement be build
		
		//test unit has resource....
		TileImprovement tileImprovement = new TileImprovement(type);
		tile.addImprovement(tileImprovement);
		
	}

}
