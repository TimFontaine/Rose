package tim.game.usercentric;

import java.awt.Point;
/**
 * 
 */
import java.util.List;

/**
 * @author tfontaine
 *
 */
public interface Actor {
	
	public void move(int x, int y);
	public void attack(Point point);
	public void specialAction(SpecialAction action);
	public void handleMultiOrder();
	

	/**
	 * @return
	 */
	public ActorData getData();
	/**
	 * @return
	 */
	public Point getLocation();
	
	public String getType();
	
	public void setComplexOrder(ComplexOrder order);
}
