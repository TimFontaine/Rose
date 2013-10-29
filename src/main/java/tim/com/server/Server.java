/**
 * 
 */
package tim.com.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author tim
 *
 */
public class Server implements Runnable{

	ServerSocket socket = null;
	
	public Server()  {
		
		try {
		
			 socket = new ServerSocket(4444);
			// TODO Auto-generated catch block
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		boolean listening = true;
		while (listening) {
			try {
				new Thread(new ServerThread(socket.accept())).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
