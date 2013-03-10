/**
 * 
 */
package tim.data.unit;

import java.awt.Point;

/**
 * @author tfontaine
 *
 */
public interface TransferResource {
	
	public void receiveResource(String name, int amount);
	
	public void giveResource(String name, int amount);
	
	public Point getLocation();

}
