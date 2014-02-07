/**
 * 
 */
package tim.com.server.test.tools;

import tim.com.client.network.command.RoseCommand;
import tim.com.client.shared.service.GameCallHandler;

/**
 * @author tim
 *
 */
public class DummyCommand implements RoseCommand {

	/**
	 * 
	 */
	public DummyCommand() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see tim.com.client.network.command.RoseCommand#handle(tim.com.client.shared.service.GameCallHandler)
	 */
	@Override
	public void handle(GameCallHandler eventHandler) {
		// TODO Auto-generated method stub

	}

}
