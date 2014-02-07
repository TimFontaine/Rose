/**
 * 
 */
package tim.com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import tim.com.client.Game;
import tim.com.client.shared.Player;
import tim.data.back.GameSpecification;
import tim.data.back.Specification;

/**
 * @author tim
 *
 */
public class Server implements Runnable{

	ServerSocket serverSocket = null;
	
	Specification specification = new Specification();
	GameSpecification gameSpecification = specification.loadGameSpecification();
	Game game = new Game(gameSpecification);
	private List<Player> playerList;
	InGameController inGameController;
	ServerGameEventHandler eventHandler;
	
	public Server()  {
		inGameController = new InGameController();
		eventHandler = new ServerGameEventHandler(game);
		
		playerList = new ArrayList<Player>();
		
		
		try {
		
			 serverSocket = new ServerSocket(4444);
			// TODO Auto-generated catch block
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
	}
	
}
