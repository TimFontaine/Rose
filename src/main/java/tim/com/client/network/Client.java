/**
 * 
 */
package tim.com.client.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author tim
 *
 */
public class Client {
	
	ObjectOutputStream oos;
	
	public Client() {
		Socket socket = null; 
		try {
			socket = new Socket("localhost", 4444);
			OutputStream os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void sendMessage(RoseMessage message) {
		try {
			oos.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
