/**
 * 
 */
package tim.data.back;

import java.util.HashMap;

import tim.com.client.controller.GUI;

/**
 * @author tim
 *
 */
public class ClientConfiguration {
	
	private ClientSpecification clientSpecification;
	
	private HashMap<String, String> messages;
	
	private GUI gui;

	/**
	 * 
	 */
	public ClientConfiguration() {
	}

	public ClientSpecification getClientSpecification() {
		return clientSpecification;
	}

	public void setClientSpecification(ClientSpecification clientSpecification) {
		this.clientSpecification = clientSpecification;
	}

	public HashMap<String, String> getMessages() {
		return messages;
	}

	public void setMessages(HashMap<String, String> messages) {
		this.messages = messages;
	}

	public GUI getGui() {
		return gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

}
