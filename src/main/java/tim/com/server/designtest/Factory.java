/**
 * 
 */
package tim.com.server.designtest;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/**
 * @author tim
 *
 */
public class Factory {

	/**
	 * 
	 */
	public Factory() {
		
	}
	
	public NetworkConnection createConnection(Socket socket, BlockingQueue<InEvent> queue) {
		NetworkConnection c = new NetworkConnection(queue, socket);
		return c;
	}

}
