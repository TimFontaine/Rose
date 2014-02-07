/**
 * 
 */
package tim.com.server.di;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import tim.com.client.Game;
import tim.com.client.shared.service.GameCallHandler;
import tim.com.server.GameState;
import tim.com.server.InGameController;
import tim.com.server.PreGameCallHandler;
import tim.com.server.PreGameController;
import tim.com.server.ServerGameEventHandler;
import tim.com.server.annotation.InGame;
import tim.com.server.annotation.PreGame;

/**
 * @author tim
 * act as mediator
 */
public class Root {
	
//	@Inject @PreGame GameState preGameController;
//	@Inject @InGame GameState inGameController;
//	@Inject Game game;
//	
//	@Inject @PreGame GameCallHandler preGameCallHandler;
//	@Inject @InGame GameCallHandler serverGameEventHandler;
	
	@Inject NetworkServer networkServer;

	
	/**
	 * 
	 */
	public Root() {
		System.out.println("root initiazed");
	}

	@PostConstruct
	public void ddd(){
		networkServer.toString();
	}
	
	
//
//	public GameState getPreGameController() {
//		return preGameController;
//	}
//
//
//	public GameState getInGameController() {
//		return inGameController;
//	}
//
//
//	public Game getGame() {
//		return game;
//	}
//
//
//	public GameCallHandler getPreGameCallHandler() {
//		return preGameCallHandler;
//	}
//
//
//	public NetworkServer getNetworkServer() {
//		return networkServer;
//	}
//
//
//	public GameCallHandler getServerGameEventHandler() {
//		return serverGameEventHandler;
//	}
//
//
//	public void setServerGameEventHandler(
//			ServerGameEventHandler serverGameEventHandler) {
//		this.serverGameEventHandler = serverGameEventHandler;
//	}



}
