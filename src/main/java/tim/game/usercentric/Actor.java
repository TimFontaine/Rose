package tim.game.usercentric;

import java.awt.Point;
/**
 * 
 */
import java.util.List;

import tim.data.unit.Unit;

/**
 * @author tfontaine
 * this is the interface exposed to players
 */
public interface Actor {
	
	public void initTurn();
	public void move(int x, int y);
	public void attack(Point point);
	public void specialAction(SpecialAction action);
	public void handleMultiOrder();
	

	/**
	 * @return
	 */
	public Unit getUnit();
	
	public void setComplexOrder(ComplexOrder order);
}
