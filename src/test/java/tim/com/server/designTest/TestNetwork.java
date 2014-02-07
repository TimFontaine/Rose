/**
 * 
 */
package tim.com.server.designTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.inject.Inject;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import tim.com.client.shared.Player;
import tim.com.server.designtest.NetworkConnection;
import tim.com.server.designtest.Network;
import tim.com.server.designtest.OutEvent;
import tim.com.server.designtest.Root;
import tim.com.server.designtest.RoseServerFactory;
import tim.com.server.designtest.RoseServerModule;
import tim.com.server.di.google.Payment;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.fest.assertions.Assertions.assertThat;


/**
 * @author tim
 *
 */
@RunWith(JukitoRunner.class)
public class TestNetwork {

	@Inject
	Network network;
	@Inject
	Root root;
	
	@Before
	public void setup() {
//		when(f.create(any(Socket.class), any(BlockingQueue.class))).thenReturn(d);
		network.setMaxPlayers(1);
	}
	
	@Test
	public void testCreateConnection(
			RoseServerFactory f, NetworkConnection d) throws IOException {
		when(f.create(any(Socket.class), any(BlockingQueue.class))).thenReturn(d);
		ServerSocket socket = mock(ServerSocket.class);
		Whitebox.setInternalState(network, "serverSocket", socket);
		Socket clientSocket = mock(Socket.class, RETURNS_MOCKS);
		when(socket.accept()).thenReturn(clientSocket);
		network.waitForNewPlayers();
		assertThat(network.getPlayers().size()).isEqualTo(1);
//		NetworkConnection c = (NetworkConnection) network.getPlayers().values().toArray()[0];
//		Socket s = (Socket) Whitebox.getInternalState(c, "socket");
//		assertThat(s).isEqualTo(clientSocket);
	}
	
	@Test
	public void testSendMessageToSpecificPlayer(NetworkConnection connection, Player player) throws InterruptedException {
		//create player and connection
		Whitebox.getInternalState(network, "players");
		Map<Player, NetworkConnection> players = (Map<Player, NetworkConnection>) Whitebox.getInternalState(network, "players");
		players.put(player, connection);
		OutEvent outEvent = new OutEvent(player);
		
		root.getOutGoing().put(outEvent);
		verify(connection, timeout(100)).sendEvent(outEvent);
	}
	
	@Test
	public void testSendMessageToAllPlayers(NetworkConnection connection1, NetworkConnection connection2) throws InterruptedException {
//		Network networkThread = Mockito.spy(new Network(root));
//		Root root = mock(Root.class);
//		Network network = new Network(root);
//		when(root.getOutGoing().take()).thenReturn(outEvent);
		//create player and connection
		Player player1 = mock(Player.class);
		Player player2 = mock(Player.class);
		Whitebox.getInternalState(network, "players");

		Map<Player, NetworkConnection> players = (Map<Player, NetworkConnection>) Whitebox.getInternalState(network, "players");
		players.put(player1, connection1);
		players.put(player2, connection2);
		
		
		OutEvent outEvent = new OutEvent(null);
		root.getOutGoing().put(outEvent);
		
		verify(connection1, timeout(100)).sendEvent(outEvent);
		verify(connection2, timeout(100)).sendEvent(outEvent);
	}
	
	@After
	public void shutDown() {
		Map<Player, NetworkConnection> players = (Map<Player, NetworkConnection>) Whitebox.getInternalState(network, "players");
		players.clear();
	}
	
	 public static class A extends JukitoModule {
	        @Override
	        protected void configureTest() {
	        	bindMock(NetworkConnection.class);
//	        	install(new FactoryModuleBuilder().build(RoseServerFactory.class));
	        }
	    }

}
