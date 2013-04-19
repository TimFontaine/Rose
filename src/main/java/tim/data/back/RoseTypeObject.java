/**
 * 
 */
package tim.data.back;

/**
 * @author tfontaine
 *
 */
public abstract class RoseTypeObject {
	
	String id;

	/**
	 * 
	 */
	public RoseTypeObject(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

}
