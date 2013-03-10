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
//	private int oil;
//	private int iron;
	private int[] resources;
	private Map<String, Object> info;
	

	/**
	 * 
	 */
	public PlayerOrder() {
		info = new HashMap<String, Object>();
	}
	
	public void addInfo(String key, Object value) {
		info.put(key, value);
	}
	
	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public int[] getResources() {
		return resources;
	}

	public void setResources(int[] resources) {
		this.resources = resources;
	}

}
