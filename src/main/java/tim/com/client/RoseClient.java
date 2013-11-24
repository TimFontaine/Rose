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
import tim.data.back.ClientConfiguration;
import tim.data.back.ClientSpecification;
import tim.data.back.GameSpecification;
import tim.data.back.Specification;
import tim.game.Player;
import tim.namespacetest.client.ClientConfig;
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
	
	private GUI gui;
	
	private Specification specification;
	
	private ClientSpecification clientSpecification;

	/**
	 * 
	 */
	public RoseClient() {	
		Specification specification = new Specification();
		GameSpecification gameSpecification = specification.loadGameSpecification();
		game = new Game(gameSpecification);
		player= new RosePlayer(game);
		server = new Server();
		
		clientSpecification = specification.loadClientSpecification();
		game.setGameSpecification(gameSpecification);
//		ClientConfiguration clientConfiguration = loadClientConfigution();
		new Thread(server).start();
		serverLogic = new ServerLogic(new Client());
		UnitType builderType = gameSpecification.getUnitTypesList().get(0);
		Unit unit = new Unit(builderType, player);
		Node node = game.getMap().getNode(5, 5);
		Source source = new Source();
		Node sourceNode = game.getMap().getNode(3, 3);
		sourceNode.setSource(source);
		
//		node.addUnit(unit);
		unit.setOwner(player);
		unit.setLocation(node);
		((RosePlayer)player).explore(node);
		gui = new GUI(this);
		
		actionManager = new ActionManager(clientSpecification, gui);
		inGameController = new InGameController(this, gui);
		
		actionManager.initializeActions(this);
		actionManager.initializeSpecialActions(this, gui, inGameController);
		gui.startGUI(GUI.determineFullScreenSize());
		gui.setupMouseListenersForCanvas();
		gui.setActiveUnit(unit);
	}

//	/**
//	 * @return
//	 */
//	private ClientConfiguration loadClientConfigution() {
//		ClientConfiguration clientConfiguration = new ClientConfiguration();
//		Specification specification = new Specification();
//		ClientSpecification clientSpecification = specification.loadClientSpecification();
//		clientConfiguration.setClientSpecification(clientSpecification);
//		clientConfiguration.setGui(gui);
//		clientConfiguration.setGui(gui);
//		return clientConfiguration;
//	}

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

	public Specification getSpecification() {
		return specification;
	}

	/**
	 * @return the clientSpecification
	 */
	public ClientSpecification getClientSpecification() {
		return clientSpecification;
	}

	

}
