/**
 * 
 */
package tim.com.server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author tim
 *
 */
public class ServerThread implements Runnable {
	
	Socket socket;

	/**
	 * @param accept
	 */
	public ServerThread(Socket clientSocket) {
		this.socket = clientSocket;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			while (true) {
				Object o = ois.readObject();
				System.out.println("object received");
				wait(30);
			}
		} catch (Exception e) {
			
		}
		
	}

}
