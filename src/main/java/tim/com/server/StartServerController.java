/**
 * 
 */
package tim.com.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;

import javax.inject.Inject;

/**
 * @author tim
 * do stuff to start the server(setup network connection, read specs
 */
public class StartServerController implements StateLike {
	
	ServerSocket serverSocket;
	
	PreGameController nextState;

	private int port = 5555;

	private StateContext stateContext;
	
	/**
	 * 
	 */
	public StartServerController(ServerSocket serverSocket, PreGameController nextState) {
		this.serverSocket = serverSocket;
		this.nextState = nextState;
	}

	/**
	 * 
	 */
	private void bootServer() {
		SocketAddress address = new InetSocketAddress("localhost", port);
		try {
			serverSocket.bind(address);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stateContext.switchState(nextState);
		
	}

	/* (non-Javadoc)
	 * @see tim.com.server.StateLike#startGameFase(tim.com.server.StateContext)
	 */
	@Override
	public void startGameFase(StateContext stateContext) {
		this.stateContext = stateContext;
		bootServer();
	}

}
