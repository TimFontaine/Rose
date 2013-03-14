/**
 * 
 */
package tim.game.usercentric;

import java.util.ArrayList;
import java.util.List;

import tim.data.unit.Unit;

/**
 * @author tfontaine
 *
 */
public class PlayerData {
	
	private List<Unit> units;

	/**
	 * 
	 */
	public PlayerData() {
		setUnits(new ArrayList<Unit>());
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

}
