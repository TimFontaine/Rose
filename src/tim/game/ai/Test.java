/**
 * 
 */
package tim.game.ai;

import tim.data.back.Path;
import tim.data.unit.Unit;
import tim.game.Back;
import tim.game.MapBuilder;

/**
 * @author tfontaine
 *
 */
public class Test {

	/**
	 * 
	 */
	public Test() {
		Task task = new Task();
		task.addDestination("mine");
		task.addDestination("factory");
		
		MapBuilder builder = new MapBuilder();
		Back back = Back.getInstance();
		back.nextPlayer();
		PlayerAI p = (PlayerAI) back.getActivePlayer();
		Unit unit = p.getUnits().get(0);
		Path path = back.findNearestObject(unit, "mine");
		unit.setX(2);
		unit.setY(2);
		Path path2 = back.findNearestObject(unit, "factory");
		unit.setX(4);
		unit.setY(2);
		Path path3 = back.findNearestObject(unit, "mine");
		
		
		for (int i = 0; i<100;i++){
			System.out.println(task.getNext());
		}
		
	}
	
	public static void main(String args[]) {
		new Test();
	}

}
