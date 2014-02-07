/**
 * 
 */
package tim.com.server.designtest;

/**
 * @author tim
 *
 */
public class PreGameEventHandler extends EventHandler {

	/**
	 * @param root
	 */
	public PreGameEventHandler(Root root) {
		super(root);
	}

	/* (non-Javadoc)
	 * @see tim.com.server.designtest.GameCalls#startGame()
	 */
	@Override
	public void startGame() {
		root.outGoing.add(new OutEvent());
	}

}
