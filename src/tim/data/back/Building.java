/**
 * 
 */
package tim.data.back;

import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

import tim.data.unit.Truck;
import tim.data.unit.Unit;
import tim.game.factory.RoseObjectFactory;

/**
 * @author tfontaine
 *
 */
public abstract class Building extends Thing {

	/**
	 * 
	 */
	public Building(String name) {
		super(name);
	}
	
	public abstract Requirement getRequirements();

}
