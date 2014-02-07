/**
 * 
 */
package tim.com.client.shared.service;

import java.util.List;


import tim.com.client.Game;
import tim.com.client.shared.Player;
import tim.data.back.Direction;

import com.sun.xml.internal.ws.server.UnsupportedMediaException;

/**
 * @author tim
 *
 */
public abstract class GameCallHandler {
	
	protected Game game;

	/**
	 * 
	 */
	public GameCallHandler() {
	}
	
	public void move(String unitId, Direction direction) {
		throw new UnsupportedOperationException();
	}
	
	public void getGame(Player player) {
		throw new UnsupportedMediaException();
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public void startGame(Game game) {
		throw new UnsupportedOperationException();
	}
	
	

}
