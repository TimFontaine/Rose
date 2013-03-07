/**
 * 
 */
package tim.data.back;

import tim.game.ai.ResourcesData;

/**
 * @author tfontaine
 *
 */
public class Storage extends Building {
	
	ResourcesData resourcesData;

	/**
	 * @param name
	 */
	public Storage(String name) {
		super(name);
		resourcesData = new ResourcesData();
	}

	/* (non-Javadoc)
	 * @see tim.data.back.Thing#doLogic()
	 */
	@Override
	public void doLogic() {
	}

}
