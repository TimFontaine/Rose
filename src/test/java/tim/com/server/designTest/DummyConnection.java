/**
 * 
 */
package tim.com.server.designTest;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import tim.com.server.Connection;
import tim.com.server.designtest.InEvent;
import tim.com.server.designtest.OutEvent;

/**
 * @author tim
 *
 */
public class DummyConnection implements tim.com.server.designtest.Connection {

	/* (non-Javadoc)
	 * @see tim.com.server.designtest.Connection#sendEvent(tim.com.server.designtest.OutEvent)
	 */
	@Override
	public void sendEvent(OutEvent e) {
		// TODO Auto-generated method stub
		
	}

}
