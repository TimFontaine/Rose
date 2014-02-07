/**
 * 
 */
package tim.com.server.di;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.inject.Inject;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import tim.com.client.network.command.RoseCommand;
import tim.com.server.network.PlayerConnection;
import tim.com.server.network.PlayerNetworkConnection;
import tim.com.server.test.tools.DummyCommand;
import static org.mockito.Mockito.*;
import static org.fest.assertions.Assertions.assertThat;

/**
 * @author tim
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class NetworkServerTest {
	
	NetworkServer networkServer;
	
	@Mock ServerSocket serverSocket;
	
	@Mock PlayerNetworkConnection playerNetworkConnection;
	
	Weld weld;
	
	@Inject 
	PlayerConnection.Factory factory;
	
	/**
	 * 
	 */
	public NetworkServerTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Before
	public void setup() {
		 weld = new Weld();
		   WeldContainer container = weld.initialize();
		networkServer = container.instance().select(NetworkServer.class).get();
		networkServer.serverSocket = serverSocket;
	}
	
	@After 
	public void shutDown() {
		try {
			System.out.println("closing connection");
			networkServer.serverSocket.close();
		} catch(Exception e) {
			
		}
		 weld.shutdown();
	}
	
	@Test
	public void waitForNewPlayers() throws IOException {
		System.out.println("running test");
		Socket socket = new Socket();
		when(serverSocket.accept()).thenReturn(socket);
		
		networkServer.waitForNewPlayers();
		assertThat(networkServer.getPlayerConnections().size()).isEqualTo(1);
//		PlayerNetworkConnection p = (PlayerNetworkConnection)networkServer.getPlayerConnections().get(0);
//		assertThat(p.getSocket()).isEqualTo(socket);
	}
	
	@Test
	public void sendToAllPlayers() {
		//setup playerConnectoin
		//add 2 players
		networkServer.getPlayerConnections().add(playerNetworkConnection);
		networkServer.sendToAllPlayers(any(RoseCommand.class));
		verify(playerNetworkConnection).sendMessage(any(RoseCommand.class));
	}
	
	@Test
	public void testFactory() {
//		Socket socket = new Socket();
//		factory.create(socket);
	}

}
