/**
 * 
 */
package tim.com.server.designTest;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import tim.com.server.designtest.NetworkConnection;
import tim.com.server.designtest.Factory;
import tim.com.server.designtest.InEvent;

/**
 * @author tim
 *
 */
public class TestFactory extends Factory {

	/**
	 * 
	 */
	public TestFactory() {
	}
	
	@Override
	public NetworkConnection createConnection(Socket socket, BlockingQueue<InEvent> queue) {
		
	}

}
