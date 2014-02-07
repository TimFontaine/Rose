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
public class Message implements Serializable {
	
	private String result;
	
	private RoseCommand command;
	
	private RoseEvent event;

	/**
	 * 
	 */
	public Message() {
		// TODO Auto-generated constructor stub
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public RoseCommand getCommand() {
		return command;
	}

	public void setCommand(RoseCommand command) {
		this.command = command;
	}

}
