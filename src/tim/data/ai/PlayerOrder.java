/**
 * 
 */
package tim.data.ai;

import java.util.HashMap;
import java.util.Map;

import tim.data.back.MapItem;
import tim.data.unit.Unit;

/**
 * @author tfontaine
 *
 */
public class PlayerOrder {
	
	private int priority;
	
	private ActionType action;
	private String typeName;
	private String processorType;
	private int x;
	private int y;
	private int oil;
	private int iron;
	private Map<String, Object> info;
	

	/**
	 * 
	 */
	public PlayerOrder() {
		info = new HashMap<String, Object>();
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


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Map<String, Object> getInfo() {
		return info;
	}

	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}

}
