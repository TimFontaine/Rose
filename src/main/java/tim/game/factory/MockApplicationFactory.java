/**
 * 
 */
package tim.game.factory;

import tim.game.Back;
import tim.game.ai.data.ResourceInfo;

/**
 * @author tfontaine
 *
 */
public class MockApplicationFactory {
	
	private static MockApplicationFactory INSTANCE;
	
	private static Back back;

	/**
	 * 
	 */
	private  MockApplicationFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static MockApplicationFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MockApplicationFactory();
		}
		return INSTANCE;
	}
	
	
	public Back getBack() {
		return new Back();
	}
	
	public ResourceInfo getResourceInfo() {
		return ResourceInfo.getInstance();
	}

}
