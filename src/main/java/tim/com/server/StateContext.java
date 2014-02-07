/**
 * 
 */
package tim.com.server;

/**
 * @author tim
 *
 */
public class StateContext {
	
	private StateLike stateLike;
	
	
	public StateContext() {
//		switchState(new StartServerController());
	}
	
	public void switchState(StateLike stateLike) {
		this.stateLike = stateLike;
	}
	
//	public void startGameState(){
//		stateLike.startGameFase(this);
//	}
	

}
