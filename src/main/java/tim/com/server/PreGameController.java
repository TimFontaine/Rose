/**
 * 
 */
package tim.com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import tim.com.client.Game;
import tim.com.client.shared.Player;
import tim.com.client.shared.service.GameCallHandler;
import tim.com.server.annotation.PreGame;
import tim.com.server.di.Root;
import tim.com.server.network.PlayerConnection;
import tim.com.server.network.PlayerNetworkConnection;
import tim.data.back.GameSpecification;
import tim.data.back.Specification;

/**
 * @author tim
 *
 */
public class PreGameController implements StateLike, GameState, tim.com.server.PreGame {
	
	
	ServerSocket serverSocket;
	
	
	GameCallHandler eventHandler;
	
	List<Player> players = new ArrayList<>();

	@Inject
	private Game game;

	private StateContext stateContext;
	
	Root root;

	/**
	 * 
	 */
	@Inject
	public PreGameController(Root root) {
		this.serverSocket = serverSocket;
		this.root = root;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	private void waitForNewPlayers() {
		boolean listening = true;
		while (listening) {
			try {
				Socket clientSocket = serverSocket.accept();
				PlayerConnection connection = createPlayerConnection(clientSocket);
				ServerPlayer player = new ServerPlayer(game);
				player.setConnection(connection);
				players.add(player);
				game.setCurrentPlayer(player);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 
	 */
	public void startGame() {
//		StateLike inGameController = new InGameController();
//		stateContext.switchState(inGameController);
//		InGameController inGameController = root.getInGameController();
//		GameCallHandler gameCallHandler = root.getServerGameEventHandler();
//		root.getNetworkServer().setCallHandlerForPlayers(gameCallHandler);
	}
	
	private Game initGame() {
		Specification specification = new Specification();
		GameSpecification gameSpecification = specification.loadGameSpecification();
		Game game = new Game(gameSpecification);
		return game;
	}
	
	/*
	 * a mini factory to setup a player network connection
	 */
	private PlayerConnection createPlayerConnection(Socket socket) {
//		PlayerConnection player = new PlayerNetworkConnection(socket, eventHandler);
//		return player;
		return null;
	}

	/* (non-Javadoc)
	 * @see tim.com.server.StateLike#startGameFase(tim.com.server.StateContext)
	 */
	@Override
	public void startGameFase(StateContext stateContext) {
		this.stateContext = stateContext;
		//create facade for network
//		eventHandler = new PreGameCallHandler(this);
		//setup an basic game
		game = initGame();
		//let other players connect
		waitForNewPlayers();
	}
}
