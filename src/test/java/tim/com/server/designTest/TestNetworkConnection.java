/**
 * 
 */
package tim.com.server.designTest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;
import org.mockito.Mock;

import tim.com.client.shared.Player;
import tim.com.server.designtest.InEvent;
import tim.com.server.designtest.NetworkConnection;
import tim.com.server.designtest.OutEvent;
import static org.mockito.Mockito.*;

/**
 * @author tim
 *
 */
public class TestNetworkConnection {
	
	NetworkConnection connection;

	BlockingQueue<InEvent> queue = new ArrayBlockingQueue<InEvent>(5);
	
	Socket socket;
	
	OutputStream os;
	
	/**
	 * @throws IOException 
	 * 
	 */
	public TestNetworkConnection() throws IOException {
		socket = mock(Socket.class, RETURNS_MOCKS);
		os = socket.getOutputStream();
		connection = new NetworkConnection(queue, socket);
	}
	
	@Test
	public void testSendEvent() throws IOException {
		OutEvent e = new OutEvent(null);
		connection.sendEvent(e);
		verify(os).write(any(byte[].class));
		
	}

}
