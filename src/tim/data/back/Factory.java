/**
 * 
 */
package tim.data.back;

import java.util.List;

import tim.data.unit.TransferResource;
import tim.data.unit.Unit;
import tim.game.ai.data.RequestType;
import tim.game.ai.data.ResourcesRequest;
import tim.game.factory.RoseObjectFactory;

/**
 * @author tfontaine
 *
 */
public class Factory extends Building {

	private int iron;
	private int oil;
	private static final int UNIT_COST = 100;
	
	private int producedUnits;
	
	/**
	 * @param name
	 */
	public Factory(String name) {
		super(name);
		System.out.println("new factory");
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
			System.out.println("producedunits is:" + producedUnits);
//			if (producedUnits <1) {
				System.out.println("created resourcerequest");
				int iron_request = UNIT_COST - iron;
				requestMap.put("iron", iron_request);
				resourcesRequest = new ResourcesRequest();
				resourcesRequest.setPriority(ResourcesRequest.MAX_PRIORITY);
				resourcesRequest.setResource(requestMap);
				resourcesRequest.setRequestType(RequestType.RESOURCES);
				resourcesRequest.setLocation(getLocation());
//			}
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
		producedUnits++;
	}

	private boolean testCanBuild() {
		if (iron >= UNIT_COST) {
			return true;
		}
		return false;
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

	public int getProducedUnits() {
		return producedUnits;
	}

	public void setProducedUnits(int producedUnits) {
		this.producedUnits = producedUnits;
	}

}
