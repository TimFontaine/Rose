/**
 * 
 */
package tim.game.back.scheduler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author tim
 *
 */
public class SchedulerTest {
	
	Scheduler scheduler;
	
	@Before
	public void setup() {
		scheduler = new Scheduler();
	}
	
	/*
	 * Test that the setup of the scheduler is done correctly
	 */
	@Test
	public void initTests() {
		assertEquals(1, scheduler.getGrids().size());
	}

}
