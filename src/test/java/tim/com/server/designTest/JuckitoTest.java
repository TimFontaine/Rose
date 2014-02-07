/**
 * 
 */
package tim.com.server.designTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.inject.Inject;

import org.hamcrest.core.AnyOf;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.mockito.internal.util.reflection.Whitebox;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import tim.com.server.designtest.InEvent;
import tim.com.server.designtest.Network;
import tim.com.server.designtest.NetworkConnection;
import tim.com.server.designtest.Root;
import tim.com.server.designtest.RoseServerFactory;
import tim.com.server.designtest.RoseServerModule;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;
/**
 * @author tim
 *
 */
@RunWith(JukitoRunner.class)
public class JuckitoTest {
	
	@Inject
	Network network;
	
	Root root;

	@Test 
	public void basic() throws IOException {
		
		
		
		ServerSocket socket = mock(ServerSocket.class);
		Whitebox.setInternalState(network, "serverSocket", socket);
		Socket clientSocket = mock(Socket.class, RETURNS_MOCKS);
		when(socket.accept()).thenReturn(clientSocket);
		network.waitForNewPlayers();
		assertThat(network.getPlayers().size()).isEqualTo(1);
		NetworkConnection c = (NetworkConnection) network.getPlayers().values().toArray()[0];
		Socket s = (Socket) Whitebox.getInternalState(c, "socket");
		assertThat(s).isEqualTo(clientSocket);
	}
	
	 public static class A extends JukitoModule {
	        @Override
	        protected void configureTest() {
//	        	bindMock(NetworkConnection.class);
//	        	install(new FactoryModuleBuilder().build(RoseServerFactory.class));
	        }
	    }
	
	@Before
	public void setup(RoseServerFactory f, NetworkConnection d) {
		when(f.create(any(Socket.class), any(BlockingQueue.class))).thenReturn(d);
//		Injector injector = Guice.createInjector(new RoseServerModule());
//	    root = injector.getInstance(Root.class);
		root = new Root();
//	    network = injector.getInstance(Network.class);
//		network = new Network(root);
		network.setMaxPlayers(1);
		
//		when(network.barFactory.create(anyString())).thenReturn(bar);
	}

}
