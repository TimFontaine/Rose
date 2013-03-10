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
	
	private int[] fullOperationResources;

	/**
	 * 
	 */
	public ResourcesData() {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		resourceInfo = applicationFactory.getResourceInfo();
		resources = new int[resourceInfo.NUM_RESOURCES];
	}
	
//	public void addResource(int key, int amount) {
//		int available = resources[key];
//		resources[key] = available + amount;
//		updateTotalStorage(amount);
//	}
	
	/*
	 * update a resource with a certain key for amount
	 * amount can be positive or negative
	 */
	public void updateResource(int key, int amount) {
		int available = resources[key];
		resources[key] = available + amount;
		updateTotalStorage(amount);
		if (resources[key] <0 ) {
			System.out.println("error: negative resources for" + 
			resourceInfo.getResourceByKey(key) + ":" + resources[key]);
		}
	}
	
//	public void retreiveResource(int key, int amount) {
//		int available = resources[key];
//		resources[key] = available - amount;
//		//debug
//		if (resources[key] <0 ) {
//			System.out.println("error: negative resources for" + 
//			resourceInfo.getResourceByKey(key) + ":" + resources[key]);
//		}
//		updateTotalStorage(-amount);
//	}
	
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

	public int getTotalStorage() {
		return totalStorage;
	}

	public void setTotalStorage(int totalStorage) {
		this.totalStorage = totalStorage;
	}

	public int getMaxStorage() {
		return maxStorage;
	}

	public void setMaxStorage(int maxStorage) {
		this.maxStorage = maxStorage;
	}

	/**
	 * @return the fullOperationResources
	 */
	public int[] getFullOperationResources() {
		return fullOperationResources;
	}

	/**
	 * @param fullOperationResources the fullOperationResources to set
	 */
	public void setFullOperationResources(int[] fullOperationResources) {
		this.fullOperationResources = fullOperationResources;
	}

}
