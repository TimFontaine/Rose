/**
 * 
 */
package tim.data.back;

import java.util.List;

import tim.data.unit.TransferResource;
import tim.data.unit.Unit;
import tim.game.factory.RoseObjectFactory;

/**
 * @author tfontaine
 *
 */
public class Factory extends Building {

	private int iron;
	private int oil;
	private static final int UNIT_COST = 100;
	
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
		} else {
			//resources needed
			int iron_request = UNIT_COST - iron;
			requestMap.put("iron", iron_request);
		}
	}
	
	private void buildUnit() {
		Unit unit = (Unit) RoseObjectFactory.getInstance().getRoseObject("worker");
		unit.setName("worker");
		unit.setType("worker");
		unit.setPlayer(player);
		unit.setOil(100);
		unit.setX(x);
		unit.setY(y);
//		if (back.getMap().getNode(x, y).getUnit() != null) {
//			waitingUnits.add(unit);
//		} else {
			back.addUnit(unit);
//		}
		iron-=UNIT_COST;
		System.out.println("factory has build unit");
	}

	private boolean testCanBuild() {
		if (iron >= UNIT_COST) {
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


	/* (non-Javadoc)
	 * @see tim.data.unit.TransferResource#receiveResource(java.lang.String, int)
	 */
	@Override
	public void receiveResource(String name, int amount) {
		if ("iron".equals(name)) {
			iron+=amount;
		} else if ("oil".equals(name)) {
			oil+=amount;
		}
		
	}

	/* (non-Javadoc)
	 * @see tim.data.unit.TransferResource#giveResource(java.lang.String, int)
	 */
	@Override
	public void giveResource(String name, int amount) {
		// TODO Auto-generated method stub
		
	}

}
