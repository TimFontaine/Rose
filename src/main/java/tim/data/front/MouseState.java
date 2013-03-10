/**
 * 
 */
package tim.data.front;

/**
 * @author tfontaine
 *
 */
public enum MouseState {
	
	FREE,
	BUSY,
	SELECTED;
	
	String item;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
}
