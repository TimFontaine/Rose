/**
 * 
 */
package tim.game.ai.data;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tfontaine
 *
 */
public class ResourcesRequest implements Comparable<ResourcesRequest> {
	
	public static final int MAX_PRIORITY = 100;
	public static final int OPTIONAL = -1;
	
	private int priority;
	private Map<String, Integer> resource;
	private Map<String, Object> info;//extra info as from/to location
	private RequestType requestType;
	private Point location;
	
	/**
	 * 
	 */
	public ResourcesRequest() {
		info = new HashMap<String, Object>();
	}


	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ResourcesRequest o) {
		if (priority > o.getPriority()) {
			return -1;
		} else if (priority < o.getPriority()) {
			return 1;
		}
		return 0;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}


	public Map<String, Integer> getResource() {
		return resource;
	}


	public void setResource(Map<String, Integer> resource) {
		this.resource = resource;
	}


	public Map<String, Object> getInfo() {
		return info;
	}


	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}

}
