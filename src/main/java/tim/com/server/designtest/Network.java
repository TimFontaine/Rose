/**
 * 
 */
package tim.com.server.designtest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.inject.Inject;

import tim.com.client.Game;
import tim.com.client.shared.Player;
import tim.com.server.ServerPlayer;
import tim.com.server.network.PlayerConnection;

/**
 * @author tim
 *
 */
public class Network {

	Root root;
	
	Map<Player, Connection> players= new HashMap<Player, Connection>();

	private int maxPlayers;

	private ServerSocket serverSocket;

	private BlockingQueue<InEvent> inComing;
	
	Thread thread;
	
	@Inject RoseServerFactory factory;
	
	
	/**
	 * 
	 */
	@Inject
	public Network(final Root root) {
		this.root = root;
		final ArrayBlockingQueue<OutEvent> outGoing = root.getOutGoing();
		inComing = root.getIncoming();
		
		try {
			serverSocket = new ServerSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					OutEvent event = root.getOutGoing().take();
					handleEvent(event);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			private void handleEvent(OutEvent event) {
				if (event.getPlayer() == null) {
					System.out.println(players.size());
					for (Connection c : players.values()) {
						c.sendEvent(event);
					}
				} else {
					Player p = event.getPlayer();
					Connection c = players.get(p);
					c.sendEvent(event);
					
				}
			}
		});
		thread.start();
		
	}
	
	public void waitForNewPlayers() {
		while (maxPlayers > players.size()) {
			try {
				Socket clientSocket = serverSocket.accept();
				ServerPlayer player = new ServerPlayer(new Game());
				Connection c = factory.create(clientSocket, inComing);
				players.put(player, c);
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
	
	public Map<Player, Connection> getPlayers() {
		return players;
	}
	
	
	public void shutDown() {
		thread.interrupt();
	}
	

}
