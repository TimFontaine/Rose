/**
 * 
 */
package tim.com.client.network;

import java.io.Serializable;

import tim.com.client.network.command.RoseCommand;

/**
 * @author tim
 *
 */
public class RoseMessage implements Serializable {
	
	
	private String messageId;
	private RoseCommand roseCommand;
	

	/**
	 * 
	 */
	public RoseMessage() {
		// TODO Auto-generated constructor stub
	}



	public RoseCommand getRoseCommand() {
		return roseCommand;
	}


	public void setRoseCommand(RoseCommand roseCommand) {
		this.roseCommand = roseCommand;
	}



	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}



	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

}
