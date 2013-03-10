/**
 * 
 */
package tim.data.back;


/**
 * @author tim
 *
 */
public class Event {

	private String description;
	private RoseObject source;
	private int code;

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public RoseObject getSource() {
		return source;
	}

	public void setSource(RoseObject source) {
		this.source = source;
	}
}
