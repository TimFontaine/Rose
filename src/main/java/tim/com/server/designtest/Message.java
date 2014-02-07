/**
 * 
 */
package tim.com.server.designtest;

import java.io.Serializable;

/**
 * @author tim
 *
 */
public class Message implements Serializable{
	
	private InEvent inEvent;
	private OutEvent outEvent;

	/**
	 * 
	 */
	public Message() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the outEvent
	 */
	public OutEvent getOutEvent() {
		return outEvent;
	}

	/**
	 * @param outEvent the outEvent to set
	 */
	public void setOutEvent(OutEvent outEvent) {
		this.outEvent = outEvent;
	}

	/**
	 * @return the inEvent
	 */
	public InEvent getInEvent() {
		return inEvent;
	}

	/**
	 * @param inEvent the inEvent to set
	 */
	public void setInEvent(InEvent inEvent) {
		this.inEvent = inEvent;
	}

}
