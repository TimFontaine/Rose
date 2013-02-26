/**
 * 
 */
package tim.data.unit;

import java.util.List;

import tim.data.back.Event;
import tim.data.back.EventCode;
import tim.data.back.Path;
import tim.game.ai.GotoJob;

/**
 * @author tfontaine
 *
 */
public class OilTruck extends Unit {
	
	
	/**
	 * 
	 */
	public OilTruck(String name) {
		super(name);
	}

	@Override
	public void doLogic() {
		System.out.println("oiltruck do logic");
		//are we there yet?
		if (job.isFinished()) {
			System.out.println("job finished, deliver oil");
			//give oil
			Unit target = order.destinationUnit;
			target.setOil(20);
			
			addOilDeliviryEvent();
			state = UnitState.TURN_FINISHED;
			player.orderFinished(this, order);
			order = null;
			job = null;
			return;
		}
		job.doAction();
	}
	
	
	
	public void initJob() {
		System.out.println("oiltruck init job");
		if (order != null && order.eventCode == EventCode.GIVE_OIL) {
			//handle event;
			job = new GotoJob(this, order.path);
		}
		
	}

	private void addOilDeliviryEvent() {
		Event event = new Event();
		event.setSource(this);
		event.setDescription("oil delivered");
		event.setCode(EventCode.OIL_DELIVERED);
		back.getEvents().add(event);
	}

	@Override
	public List<Path> getUsedRoutes() {
		// TODO Auto-generated method stub
		return null;
	}

}
