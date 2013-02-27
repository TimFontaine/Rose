/**
 * 
 */
package tim.data.ai;

import tim.data.back.MapItem;
import tim.data.unit.Unit;

/**
 * @author tfontaine
 *
 */
public class PlayerOrder {
	
	private ActionType action;
	private String typeName;
	private String processorType;
	private int x;
	private int y;
	private int oil;
	private int iron;
	

	/**
	 * 
	 */
	public PlayerOrder() {
		
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}

	public int getOil() {
		return oil;
	}


	public void setOil(int oil) {
		this.oil = oil;
	}


	public int getIron() {
		return iron;
	}


	public void setIron(int iron) {
		this.iron = iron;
	}


	public String getProcessorType() {
		return processorType;
	}


	public void setProcessorType(String processorType) {
		this.processorType = processorType;
	}


	public ActionType getAction() {
		return action;
	}


	public void setAction(ActionType action) {
		this.action = action;
	}

}
