/**
 * 
 */
package tim.com.client.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author tim
 *
 */
public class MessageHandler {
	
	Socket socket;
	public BlockingQueue<Message> outgoingQueue;
	public BlockingQueue<Message> incomingQueue;

	private Thread sender;
	private Thread receiver;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	/**
	 * @throws IOException 
	 * 
	 */
	public MessageHandler(final Socket socket, BlockingQueue<Message> incoming, BlockingQueue<Message> outgoing) {
		this.socket = socket;
		this.incomingQueue = incoming;
		this.outgoingQueue = outgoing;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
//			System.out.println("ois created");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		sender = new Thread( new Runnable() {
			
			@Override
			public void run() {
				while (!Thread.interrupted()) {
					try {
						Message message = outgoingQueue.take();
						//send the message;
						try {
							oos.writeObject(message);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		sender.start();
		
		receiver = new Thread( new Runnable() {
			
			@Override
			public void run() {
				try {
					while (true) {
					Message o = (Message) ois.readObject();
					incomingQueue.put(o);
				
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		receiver.start();
	}

}
