/**
 * 
 */
package tim.game.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import tim.game.Back;
import tim.game.ai.data.PlayerData;
import tim.game.ai.data.ResourceInfo;
import tim.game.hud.Mediator;

/**
 * @author tfontaine
 *
 */
public class GameApplicationFactory {
	
	private static GameApplicationFactory INSTANCE;
	
	private Back back;
	private Mediator mediator;
	private ResourceInfo resourceInfo;
	
	//playerData stored with the data and playerName
	private Map<String, PlayerData> playerData;

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
		back = new Back();
		mediator = new Mediator();
		resourceInfo = ResourceInfo.getInstance();
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

}
