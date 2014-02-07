/**
 * 
 */
package tim.com.server.designTest;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import tim.com.server.designtest.InEvent;
import tim.com.server.designtest.NetworkConnection;

/**
 * @author tim
 *
 */
public interface RoseServerTestFactory {
	DummyConnection create(final Socket socket, BlockingQueue<InEvent> incoming);
}
