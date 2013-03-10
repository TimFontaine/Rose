/**
 * 
 */
package tim.data.ai;

/**
 * @author tfontaine
 *
 */
public enum ActionType {
	NONE,
	BUILD,
	RESOURCES, 
	ROAD;
	
	private String info;
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getInfo() {
		return info;
	}
};


