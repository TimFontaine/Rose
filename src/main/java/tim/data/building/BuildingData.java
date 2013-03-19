/**
 * 
 */
package tim.data.building;

import java.awt.Point;

import tim.data.back.BuildingState;
import tim.game.ai.ResourceContainer;

/**
 * @author tfontaine
 *
 */
public class BuildingData {
	
	private String type;
	private Point location;
	/**
	 * TODO remove and move to the graphic layer
	 */
	private String imageName;
	private BuildingState state;
	
	private ResourceContainer resourceContainer;
	
	private String orderName;

	/**
	 * 
	 */
	public BuildingData() {
		resourceContainer = new ResourceContainer();
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public BuildingState getState() {
		return state;
	}

	public void setState(BuildingState state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public ResourceContainer getResourceContainer() {
		return resourceContainer;
	}

	public void setResourceContainer(ResourceContainer resourceContainer) {
		this.resourceContainer = resourceContainer;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

}
