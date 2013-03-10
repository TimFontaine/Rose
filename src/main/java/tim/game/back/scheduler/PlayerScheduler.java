/**
 * 
 */
package tim.game.back.scheduler;

import java.util.List;

import tim.data.unit.Unit;

/**
 * @author tim
 *
 */
public interface PlayerScheduler {
	
	public void doAction();

	/**
	 * @param grids
	 */
	public void setGrids(List<Grid> grids);

	/**
	 * @param units
	 */
	public void setUnits(List<Unit> units);

}
