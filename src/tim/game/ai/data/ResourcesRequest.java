/**
 * 
 */
package tim.game.ai.data;

import java.awt.Point;
import java.util.Map;
import java.util.Set;

/**
 * @author tfontaine
 *
 */
public class ResourcesRequest implements Comparable<ResourcesRequest> {
	
	public static final int MAX_PRIORITY = 100;
	public static final int OPTIONAL = -1;
	
	private int priority;
	private Map<String, Integer> request;
	private RequestType requestType;
	private Point location;
	
	/**
	 * 
	 */
	public ResourcesRequest() {
		// TODO Auto-generated constructor stub
	}

	public Map<String, Integer> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Integer> request) {
		this.request = request;
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
		if (priority < o.getPriority()) {
			return -1;
		} else if (priority > o.getPriority()) {
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

}
