/**
 * 
 */
package tim.data.unit;

import tim.data.back.Path;

/**
 * @author tfontaine
 *
 */
public class UnitOrder {
	
	Path path;
	Unit destinationUnit;
	int eventCode;
	

	/**
	 * 
	 */
	public UnitOrder(Path path, Unit destinationUnit, int eventCode ) {
		this.path = path;
		this.destinationUnit = destinationUnit;
		this.eventCode = eventCode;
	}

}
