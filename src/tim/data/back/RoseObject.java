/**
 * 
 */
package tim.data.back;

/**
 * @author tfontaine
 *
 */
public abstract class RoseObject {
	
	private String type;
	private String name;
	private String imageName;

	/**
	 * 
	 */
	public RoseObject(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
