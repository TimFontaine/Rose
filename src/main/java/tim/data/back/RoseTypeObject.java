/**
 * 
 */
package tim.data.back;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author tfontaine
 *
 */
public abstract class RoseTypeObject {
	
	String id;

	/**
	 * 
	 */
	public RoseTypeObject() {
//		this.id = id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@XmlAttribute(name="id")
	public String getId() {
		return id;
	}

}
