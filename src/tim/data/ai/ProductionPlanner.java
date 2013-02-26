/**
 * 
 */
package tim.data.ai;

/**
 * @author tfontaine
 *
 */
public class ProductionPlanner {

	/**
	 * 
	 */
	public ProductionPlanner() {
		// TODO Auto-generated constructor stub
	}
	
	public PlayerOrder doAction() {
		PlayerOrder playerOrder = new PlayerOrder();
		playerOrder.setTypeName("factory");
		playerOrder.setX(14);
		playerOrder.setY(14);
		playerOrder.setIron(30);
		playerOrder.setOil(20);
		playerOrder.setProcessorType("worker");
		return playerOrder;
	}

}
