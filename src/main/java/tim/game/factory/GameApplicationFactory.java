/**
 * 
 */
package tim.game.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import tim.game.Back;
import tim.game.RoseRules;
import tim.game.ai.data.PlayerData;
import tim.game.ai.data.ResourceInfo;
import tim.game.hud.Mediator;
import tim.game.usercentric.InterfaceTranslator;
import tim.pathfinding.AStar;
import tim.pathfinding.ClosestHeuristic;

/**
 * @author tfontaine
 *
 */
public class GameApplicationFactory {
	
	private static GameApplicationFactory INSTANCE;
	
	private Back back;
	private RoseRules roseRules;
	private Mediator mediator;
	private ResourceInfo resourceInfo;
	
	//playerData stored with the data and playerName
	private Map<String, PlayerData> playerData;
	
	private InterfaceTranslator interfaceTranslator;
	
	/**
	 * 
	 */
	private GameApplicationFactory() {
//		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
//		Properties properties = new Properties();
//		try {
//			properties.load(is);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public static GameApplicationFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GameApplicationFactory();
			INSTANCE.init();
		}
		return INSTANCE;
	}
	
	/**
	 * 
	 */
	private void init() {
		resourceInfo = ResourceInfo.getInstance();
		back = new Back();
		roseRules = new RoseRules();
		mediator = new Mediator();
		interfaceTranslator = new InterfaceTranslator();
	}

	public Mediator getMediator() {
		return mediator;
	}
	
	public Back getBack() {
		return back;
	}

	public ResourceInfo getResourceInfo() {
		return resourceInfo;
	}

	public InterfaceTranslator getInterfaceTranslator() {
		return interfaceTranslator;
	}

	public RoseRules getRoseRules() {
		return roseRules;
	}
	
}
