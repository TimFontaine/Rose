/**
 * 
 */
package tim.data.back;

import java.util.List;

import tim.data.unit.Unit;
import tim.game.factory.RoseObjectFactory;

/**
 * @author tfontaine
 *
 */
public class Factory extends Building {

	private int iron;
	private static final int UNIT_COST = 100;
	private List<Unit> waitingUnits;
	
	/**
	 * @param name
	 */
	public Factory(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public int getIron() {
		return iron;
	}

	public void setIron(int iron) {
		this.iron = iron;
	}
	
	public void receiveIron(int iron) {
		this.iron += iron;
	}
	
	public void doLogic() {
		boolean test = testCanBuild();
		if (test) {
			buildUnit();
		}
//		if (!waitingUnits.isEmpty()) {
//			Unit unit = waitingUnits.get(0);
//			if (back.getMap().getNode(getX(), y).getUnit() == null) {
//				back.addUnit(player, unit);
//				waitingUnits.remove(unit);
//			}
//			
//		}
	}
	
	private void buildUnit() {
		Unit unit = (Unit) RoseObjectFactory.getInstance().getRoseObject("builder");
		unit.setName("builder");
		unit.setType("builder");
		unit.setPlayer(player);
		unit.setOil(100);
		unit.setX(x);
		unit.setY(y);
//		if (back.getMap().getNode(x, y).getUnit() != null) {
//			waitingUnits.add(unit);
//		} else {
			back.addUnit(player, unit);
//		}
		iron-=UNIT_COST;
	}

	private boolean testCanBuild() {
		if (iron > UNIT_COST) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see tim.data.back.Building#getRequirements()
	 */
	@Override
	public Requirement getRequirements() {
		Requirement requirement = new Requirement();
		
		return requirement;
	}

}
