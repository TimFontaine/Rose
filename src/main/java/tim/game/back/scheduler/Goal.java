/**
 * 
 */
package tim.game.back.scheduler;

/**
 * @author tim
 *
 */
public class Goal {
	
	private int priority;

	/**
	 * 
	 */
	public Goal() {
		priority = 100;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

}
