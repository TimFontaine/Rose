/**
 * 
 */
package tim.game.ai.job;

import java.awt.Point;

import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public abstract class Job {
	
	protected Back back;
	protected boolean finished;
	protected Point player;
	protected Unit unit;
	
	protected boolean started;

	/**
	 * 
	 */
	public Job() {
		back = GameApplicationFactory.getInstance().getBack();
	}
	
	public abstract void start();
	
	public abstract void doAction();

	public boolean isFinished() {
		return finished;
	}
	
	protected boolean started() {
		return started;
	}

}
