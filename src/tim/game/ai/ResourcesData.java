/**
 * 
 */
package tim.game.ai;

import tim.game.ai.data.ResourceInfo;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class ResourcesData {
	
	protected int[] resources;
	protected int maxStorage = 100;
	protected int totalStorage;
	
	ResourceInfo resourceInfo;

	/**
	 * 
	 */
	public ResourcesData() {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		resourceInfo = applicationFactory.getResourceInfo();
		resources = new int[resourceInfo.NUM_RESOURCES];
	}
	
	public void addResource(int key, int amount) {
		int available = resources[key];
		resources[key] = available + amount;
		updateTotalStorage(amount);
	}
	
	public void retreiveResource(int key, int amount) {
		int available = resources[key];
		resources[key] = available - amount;
		//debug
		if (resources[key] <0 ) {
			System.out.println("error: negative resources for" + 
			resourceInfo.getResourceByKey(key) + ":" + resources[key]);
		}
		updateTotalStorage(-amount);
	}
	
	public void retreiveMultipleResources(int[] resourseSet) {
		for (int i = 0; i < resourseSet.length; i++ ) {
			retreiveResource(i, resourseSet[i]);
		}
	}
	
	
	public boolean hasResourcesAvailable(int[] required) {
		for (int key=0; key<required.length;key++) {
			int amount = required[key];
			if (resources[key] < amount) {
				return false;
			}
		}
		return true;	
	}
	
	public int getAvailableResource(int key) {
		return resources[key]; 
	}
	
	public int getFreeStorage() {
		return maxStorage - totalStorage;
	}

	private void updateTotalStorage(int amount) {
		totalStorage += amount;
	}

	/**
	 * @return
	 */
	public int[] getResources() {
		return resources;
	}

}
