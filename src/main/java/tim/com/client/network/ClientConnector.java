/**
 * 
 */
package tim.com.client.network;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import tim.com.client.network.command.RoseCommand;
import tim.com.client.shared.EventSet;

/**
 * @author tim
 *
 */
public class ClientConnector {

	private Socket socket;
	
	private MessageHandler messageHandler;
	
	private BlockingQueue<Message> incomingMessages;
	private BlockingQueue<Message> outgoingMessages;
	
	/**
	 * 
	 */
	public ClientConnector() {
		incomingMessages = new ArrayBlockingQueue<>(5);
		outgoingMessages = new ArrayBlockingQueue<>(5);
		
		try {
			socket = new Socket("localhost", 4444);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageHandler = new MessageHandler(socket, incomingMessages, outgoingMessages);
	}
	
	public EventSet sendMessage(RoseCommand command) {
		Message message = new Message();
		message.setCommand(command);
		try {
			outgoingMessages.put(message);
			//TODO test of type of incoming message
			EventMessage returnMessage = (EventMessage) incomingMessages.take();
			return new EventSet();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
