/**
 * 
 */
package tim.game.ai.data;

/**
 * @author tfontaine
 *
 */
public class MutableResource {
	
	private Resource type;
	private int amount;
	
	public enum Resource {
		NONE,
		IRON,
		OIL;
	}

	/**
	 * 
	 */
	public MutableResource(Resource resource) {
	}
	
	public void update(int amount) {
		this.amount += amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public Resource getType() {
		return type;
	}

}
