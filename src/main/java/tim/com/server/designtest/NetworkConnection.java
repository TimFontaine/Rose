/**
 * 
 */
package tim.com.server.designtest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;


/**
 * @author tim
 *
 */
public class NetworkConnection implements Connection {
	
	Socket socket;
	public BlockingQueue<InEvent> incomingQueue;

	private Thread receiver;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	/**
	 * 
	 */
	@Inject
	public NetworkConnection(@Assisted BlockingQueue<InEvent> incoming, @Assisted final Socket socket ) {
		this.socket = socket;
		this.incomingQueue = incoming;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
//		sender = new Thread( new Runnable() {
//			
//			@Override
//			public void run() {
//				while (!Thread.interrupted()) {
//					try {
//						OutEvent event = outgoingQueue.take();
//						Message message = new Message();
//						message.setOutEvent(event);
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
					ois = new ObjectInputStream(socket.getInputStream());
					while (true) {
					Message o = (Message) ois.readObject();
					incomingQueue.put(o.getInEvent());
				
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
	
	public void sendEvent(OutEvent e) {
			Message message = new Message();
			message.setOutEvent(e);
			//send the message;
			try {
				oos.writeObject(message);
			} catch (IOException exception) {
				exception.printStackTrace();
			}
	}

}
