/**
 * 
 */
package tim.game.ai;

import java.awt.Point;

import tim.game.Back;

/**
 * @author tfontaine
 *
 */
public abstract class Job {
	
	protected Back back;
	protected boolean finished;
	protected Point player;

	/**
	 * 
	 */
	public Job() {
		back = Back.getInstance();
	}
	
	public abstract void doAction();

	public boolean isFinished() {
		return finished;
	}

}
