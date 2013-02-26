/**
 * 
 */
package tim.core;

/**
 * @author tfontaine
 *
 */
public class GameAction {
	
	/**
	   * Normal behavior. The isPressed() method returns true as long as the key
	   * is held down.
	   */
	  public static final int NORMAL = 0;
	
	/**
	   * Initial press behavior. The isPressed() method returns true only after
	   * the key is first pressed, and not again until the key is released and
	   * pressed again.
	   */
	  public static final int DETECT_INITAL_PRESS_ONLY = 1;

	  private static final int STATE_RELEASED = 0;

	  private static final int STATE_PRESSED = 1;

	  private static final int STATE_WAITING_FOR_RELEASE = 2;

	private int amount;
	
	private int state;
	
	
	/**
	 * 
	 */
	public GameAction() {
		state = NORMAL;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public synchronized  void press() {
		press(1);
	}
	
	/**
	   * Taps this GameAction. Same as calling press() followed by release().
	   */
	  public synchronized void tap() {
	    press();
	    release();
	  }

	/**
	   * Signals that the key was pressed a specified number of times, or that the
	   * mouse move a spcified distance.
	   */
	  public synchronized void press(int amount) {
	    if (state != STATE_WAITING_FOR_RELEASE) {
	      this.amount += amount;
	      state = STATE_PRESSED;
	    }

	  }
	
	public synchronized int getAmount() {
	    int retVal = amount;
	    if (retVal != 0) {
	        amount = 0;
	    }
	    return retVal;
	}
	
	/**
	   * Signals that the key was released
	   */
	  public synchronized void release() {
	    state = STATE_RELEASED;
	  }
	
	/**
	   * Returns whether the key was pressed or not since last checked.
	   */
	  public synchronized boolean isPressed() {
	    return (getAmount() != 0);
	  }
	
	/**
	* Resets this GameAction so that it appears like it hasn't been pressed.
	*/
	public void reset() {
		state = STATE_RELEASED;
	    amount = 0;
	 }

}
