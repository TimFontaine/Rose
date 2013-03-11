/**
 * 
 */
package tim.game.back.scheduler;

import tim.game.ai.data.MutableResource;
import tim.game.ai.data.MutableResource.Resource;

/**
 * @author tfontaine
 *
 */
public class ResourceOrder extends Order {
	
	private Resource resource;
	private int amount;
	

	/**
	 * 
	 */
	public ResourceOrder() {
		// TODO Auto-generated constructor stub
	}


	public Resource getResource() {
		return resource;
	}


	public void setResource(Resource resource) {
		this.resource = resource;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}

}
