/**
 * 
 */
package tim.com.server;

import tim.com.server.annotation.InGame;
import tim.com.server.network.PlayerConnection;
import tim.data.back.Direction;

/**
 * @author tim
 *
 */
@InGame
public class InGameController implements StateLike, GameState {
	
	PlayerConnection activePlayer;
	
	public InGameController() {
	}

	/**
	 * @param unitId
	 * @param direction
	 */
	public void moveUnit(String unitId, Direction direction) {
		System.out.println("server doing stuff for moveing unit");
		activePlayer.sendMessage("hello move");
	}

	public PlayerConnection getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(PlayerConnection activePlayer) {
		this.activePlayer = activePlayer;
	}

	/* (non-Javadoc)
	 * @see tim.com.server.StateLike#startGameFase(tim.com.server.StateContext)
	 */
	@Override
	public void startGameFase(StateContext stateContext) {
		// TODO Auto-generated method stub
		
	}

}
