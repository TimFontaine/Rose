package tim.game.usercentric;

import java.awt.Point;
/**
 * 
 */

/**
 * @author tfontaine
 *
 */
public interface Actor {
	
	public void move(int x, int y);
	public void specialAction(SpecialAction action);
	

	/**
	 * @return
	 */
	public ActorData getData();
}
