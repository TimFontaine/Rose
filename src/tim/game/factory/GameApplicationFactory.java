/**
 * 
 */
package tim.game.factory;

import tim.game.Back;
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

	/**
	 * 
	 */
	private GameApplicationFactory() {
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
