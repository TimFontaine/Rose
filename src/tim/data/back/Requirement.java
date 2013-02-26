/**
 * 
 */
package tim.data.back;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tfontaine
 *
 */
public class Requirement {
	
	private Map<String, Integer> need;

	/**
	 * 
	 */
	public Requirement() {
		setNeed(new HashMap<String, Integer>());
	}

	public Map<String, Integer> getNeed() {
		return need;
	}

	public void setNeed(Map<String, Integer> need) {
		this.need = need;
	}

}
