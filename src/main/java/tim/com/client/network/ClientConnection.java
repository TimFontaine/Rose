/**
 * 
 */
package tim.com.client.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import tim.com.client.network.command.RoseCommand;

/**
 * @author tim
 *
 */
public class ClientConnection {
	
	ObjectOutputStream oos;
	InputStream is;
	ObjectInputStream ois;
	
//	ResponseThread responseThread;
	
	public ClientConnection() {
		Socket socket = null; 
		
		
		try {
			socket = new Socket("localhost", 4444);
			OutputStream os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
			InputStream is = socket.getInputStream();
//			responseThread = new ResponseThread(is);
//			Thread thread = new Thread(responseThread);
//			thread.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
//	public RoseNetworkResponse sendActionMessage(RoseCommand command) {
//		RoseMessage message = new RoseMessage();
//		String id = UUID.randomUUID().toString();
//		message.setMessageId(id);
//		message.setRoseCommand(command);
//		sendMessage(message);
//		System.out.println("message send with id:" + id);
//		RoseNetworkResponse response = responseThread.askResponse(id);
//		return response;
//	}
//	
	private void sendMessage(RoseMessage message) {
		try {
			oos.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
