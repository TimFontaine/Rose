/**
 * 
 */
package tim.com.server.di;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import tim.com.client.network.command.RoseCommand;
import tim.com.client.shared.service.GameCallHandler;
import tim.com.server.InGameController;
import tim.com.server.ServerPlayer;
import tim.com.server.di.annotations.NetworkConnection;
import tim.com.server.network.PlayerConnection;

/**
 * @author tim
 *
 */
public class NetworkServer {

	ServerSocket serverSocket;
	
	private List<PlayerConnection> playerConnections;
	
	@Inject
    private PlayerConnection.Factory productShipperFactory;
	
//	@Inject @Any
//	private Instance<PlayerConnection> connectionInstances;
	
	private int maxPlayers = 1;
	
	public NetworkServer() {
		System.out.println("init networkserver");
		playerConnections = new ArrayList<PlayerConnection>();
		try {
			serverSocket = new ServerSocket(4444);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void waitForNewPlayers() {
		while (maxPlayers > playerConnections.size()) {
			try {
				System.out.println("trying to create client socket");
				Socket clientSocket = serverSocket.accept();
				System.out.println("socket created");
				//PlayerConnection playerConnection = connectionInstances.select(new AnnotationLiteral<NetworkConnection>(){}).get();
				PlayerConnection playerConnection = productShipperFactory.create(clientSocket);
				
				playerConnection.process(clientSocket);
				playerConnections.add(playerConnection);
//				PlayerConnection connection = createPlayerConnection(clientSocket);
//				ServerPlayer player = new ServerPlayer(game);
//				player.setConnection(connection);
//				players.add(player);
//				game.setCurrentPlayer(player);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			System.out.println("closing socket");
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
	
//	public void stopAcceptingNetSockets() {
//		listening = false;
//		try {
//			serverSocket.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void sendToAllPlayers(RoseCommand command) {
		for (PlayerConnection playerConnection: playerConnections) {
			playerConnection.sendMessage(command);
		}
	}

	/**
	 * @param inGameController
	 */
	public void setCallHandlerForPlayers(GameCallHandler gameCallHandler) {
		for (PlayerConnection playerConnection: playerConnections) {
			playerConnection.switchCallHandler(gameCallHandler);
		}
	}


	public List<PlayerConnection> getPlayerConnections() {
		return playerConnections;
	}


	public void setPlayerConnections(List<PlayerConnection> playerConnections) {
		this.playerConnections = playerConnections;
	}
	

	
}
