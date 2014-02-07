/**
 * 
 */
package tim.com.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import tim.com.client.network.Message;
import tim.com.server.designtest.InEvent;

/**
 * @author tim
 *
 */
public class Connection {
	
	Socket socket;
//	public BlockingQueue<Message> outgoingQueue;
	public BlockingQueue<InEvent> incomingQueue;

	private Thread sender;
	private Thread receiver;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	/**
	 * 
	 */
	public Connection(ArrayBlockingQueue<InEvent> incoming, Socket socket ) {
		this.socket = socket;
		this.incomingQueue = incoming;
//		this.outgoingQueue = outgoing;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
//			System.out.println("ois created");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
//		sender = new Thread( new Runnable() {
//			
//			@Override
//			public void run() {
//				while (!Thread.interrupted()) {
//					try {
//						Message message = outgoingQueue.take();
//						//send the message;
//						try {
//							oos.writeObject(message);
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		});
//		sender.start();
		
		receiver = new Thread( new Runnable() {
			
			@Override
			public void run() {
				try {
					while (true) {
					Message o = (Message) ois.readObject();
					incomingQueue.put(o.getCommand());
				
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		receiver.start();
	}

}
