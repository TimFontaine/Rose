/**
 * 
 */
package tim.com.client;

import tim.com.client.controller.ActionManager;
import tim.com.client.controller.GUI;
import tim.com.client.game.InGameController;
import tim.com.client.network.Client;
import tim.com.client.network.ServerLogic;
import tim.com.client.shared.Node;
import tim.com.client.shared.RosePlayer;
import tim.com.client.shared.Unit;
import tim.com.server.Server;
import tim.game.Player;
import tim.namespacetest.types.Source;
import tim.namespacetest.types.UnitType;

/**
 * @author tfontaine
 *
 */
public class RoseClient {
	
	private InGameController inGameController;
	private ActionManager actionManager;
	
	private Game game;
	
	private Player player;
	
	private ServerLogic serverLogic;
	
	private Server server;

	/**
	 * 
	 */
	public RoseClient() {	
		game = new Game();
		player= new RosePlayer(game);
		server = new Server();
		new Thread(server).start();
		serverLogic = new ServerLogic(new Client());
		UnitType builderType = game.getSpecification().getUnitTypesList().get(0);
		Unit unit = new Unit(builderType, player);
		Node node = game.getMap().getNode(5, 5);
		Source source = new Source();
		Node sourceNode = game.getMap().getNode(3, 3);
		sourceNode.setSource(source);
		
//		node.addUnit(unit);
		unit.setOwner(player);
		unit.setLocation(node);
		((RosePlayer)player).explore(node);
		GUI gui = new GUI(this);
		
		actionManager = new ActionManager(gui);
		inGameController = new InGameController(this, gui);
		
		actionManager.initializeActions(this);
		actionManager.initializeSpecialActions(this, gui, inGameController);
		gui.startGUI(GUI.determineFullScreenSize());
		gui.setupMouseListenersForCanvas();
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

	public InGameController getInGameController() {
		return inGameController;
	}
	
	public void updateActions() {
		actionManager.updateActions();
	}

	/**
	 * @return
	 */
	public ServerLogic getServer() {
		return serverLogic;
	}

}
