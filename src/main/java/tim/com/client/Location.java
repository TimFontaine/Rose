/**
 * 
 */
package tim.com.client;

import java.awt.Point;

/**
 * @author tfontaine
 * 
 * a place where a locatable can be put
 *
 */
public interface Location {
	
	public void add(Locatable locatable);
	
	public void remove(Locatable locatable);
	
	public boolean contains(Locatable locatable);

	/**
	 * @return
	 */
	public Point getPosition();
	

}
