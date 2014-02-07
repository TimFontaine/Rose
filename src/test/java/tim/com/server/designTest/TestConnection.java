/**
 * 
 */
package tim.com.server.designTest;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import javax.inject.Inject;

import org.jukito.JukitoModule;
import org.jukito.JukitoRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import com.google.inject.assistedinject.FactoryModuleBuilder;

import tim.com.server.designtest.InEvent;
import tim.com.server.designtest.Network;
import tim.com.server.designtest.NetworkConnection;
import tim.com.server.designtest.RoseServerFactory;

import static org.mockito.Mockito.*;
/**
 * @author tim
 *
 */
@RunWith(JukitoRunner.class)
public class TestConnection {
	
//	 /**
//	   * Guice test module.
//	   */
	public static class A extends JukitoModule {
		@Override
		protected void configureTest() {
			bindMock(Socket.class);
			install(new FactoryModuleBuilder().build(RoseServerFactory.class));
		}
	}
	

	@Before
	public void setup() {
//		connection = factory.create(s, q);
	}
//	
	@Test
	public void testReceiveMessage(RoseServerFactory factory, Socket s, BlockingQueue<InEvent> q) {
		Socket ss = mock(Socket.class, RETURNS_MOCKS);
		NetworkConnection n = factory.create(ss, q);
	}
//	


}
