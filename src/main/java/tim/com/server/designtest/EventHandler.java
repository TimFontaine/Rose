/**
 * 
 */
package tim.com.server.designtest;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author tim
 *
 */
public abstract class EventHandler implements GameCallsServer {
	
	Root root;

	public EventHandler(Root root) {
		
		ArrayBlockingQueue<InEvent> incoming = root.incoming;
		try {
			InEvent inEvent = incoming.take();
			inEvent.handle(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void startGame() {
		throw new UnsupportedOperationException();
	}
}
