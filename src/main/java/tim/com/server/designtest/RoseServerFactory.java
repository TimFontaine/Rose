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
public interface RoseServerFactory {

	NetworkConnection create(final Socket socket, BlockingQueue<InEvent> incoming);
}
