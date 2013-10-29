/**
 * 
 */
package tim.com.client.shared;

import java.awt.Point;

/**
 * @author tfontaine
 * 
 * a place with coordinates where objects can be put
 *
 */
public interface Location {
	
	public void add(Locatable locatable);
	
	public void remove(Locatable locatable);
	
	public boolean contains(Locatable locatable);

	/**
	 * @return
	 */
	public Node getPosition();
	

}
