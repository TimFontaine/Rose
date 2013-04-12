/**
 * 
 */
package tim.com.client;

import java.awt.Point;

import tim.core.InputManager;
import tim.data.back.Node;
import tim.game.Player;

/**
 * @author tfontaine
 *
 */
public class RoseClient {
	
	private InGameController inGameController;
	private ActionManager actionManager;
	
	private Game game;
	
	private Player player;

	/**
	 * 
	 */
	public RoseClient() {	
		game = new Game();
		player= new RosePlayer(game);
		Unit unit = new Unit("builder");
		Node node = game.getMap().getNode(5, 5);
//		node.addUnit(unit);
		unit.setOwner(player);
		unit.setLocation(node);
		((RosePlayer)player).explore(node);
		GUI gui = new GUI(this);
		
		actionManager = new ActionManager();
		inGameController = new InGameController(this, gui);
		
		actionManager.initializeActions(inGameController);
		gui.startGUI(GUI.determineFullScreenSize());
		
		gui.setActiveUnit(unit);
		
		
	}

	public ActionManager getActionManager() {
		return actionManager;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
