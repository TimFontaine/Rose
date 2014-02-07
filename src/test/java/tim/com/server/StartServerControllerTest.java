/**
 * 
 */
package tim.com.server;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author tim
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class StartServerControllerTest {
	
	@Mock
	public ServerSocket serverSocket;
	
	@Mock 
	private PreGameController nextState;
	
	@Mock
	private StateContext stateContext;
	
	@InjectMocks
	public StartServerController controller;

	@Test
	public void testSocketInitiated() throws IOException {
		controller.startGameFase(stateContext);
		verify(serverSocket).bind(any(InetSocketAddress.class));
	}
	
	@Test
	public void testSwitchToPreGameState() {
		controller.startGameFase(stateContext);
		verify(stateContext).switchState(any(PreGameController.class));
	}

}
