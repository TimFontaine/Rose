/**
 * 
 */
package tim.game.usercentric;

import java.util.ArrayList;
import java.util.List;


/**
 * @author tfontaine
 *
 */
public class PlayerData {
	
	private List<Actor> actors;

	/**
	 * 
	 */
	public PlayerData() {
		setActors(new ArrayList<Actor>());
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> units) {
		this.actors = units;
	}
	
	public void addActor(Actor actor) {
		actors.add(actor);
	}
	
	public void removeActor(Actor actor) {
		actors.remove(actor);
	}

}
