/**
 * 
 */
package tim.com.server.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.enterprise.util.Nonbinding;
import javax.inject.Inject;

import com.softwaremill.common.cdi.autofactory.CreatedWith;
import com.softwaremill.common.cdi.autofactory.FactoryParameter;

import tim.com.client.network.EventMessage;
import tim.com.client.network.GameEvent;
import tim.com.client.network.Message;
import tim.com.client.network.MessageHandler;
import tim.com.client.network.RoseMessage;
import tim.com.client.network.command.RoseCommand;
import tim.com.client.network.command.client.GameEventCommand;
import tim.com.client.shared.service.GameCallHandler;
import tim.com.server.di.ConnectionName;
import tim.com.server.di.annotations.NetworkConnection;

/**
 * @author tim
 * a bridge pattern between the inGame controller and messagehandler 
 */
@CreatedWith(PlayerConnection.Factory.class)
public class PlayerNetworkConnection implements PlayerConnection {
	
	private Socket socket;
	
	final BlockingQueue<Message> incoming = new ArrayBlockingQueue<Message>(5);
	final BlockingQueue<Message> outgoing = new ArrayBlockingQueue<Message>(5);
	
	private GameCallHandler gameCallHandler;
	
	@Inject
	public PlayerNetworkConnection(@FactoryParameter Socket socket, DummyService dummyService) {
		this.socket = socket;
	}
	
	/**
	 * @param accept
	 */
//	public PlayerNetworkConnection(Socket clientSocket, final GameCallHandler eventHandler) {
//		this.socket = clientSocket;
//		
//		//the producer
//		MessageHandler messageHandler = new MessageHandler(socket, incoming, outgoing);
//		
//		Thread consumer = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					while (true) {
//						Message message = incoming.take();
//						System.out.println("message received on server");
//						message.getCommand().handle(eventHandler);
//					}
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
//		});
//		consumer.start();
//		
//	}
//
//	/**
//	 * 
//	 */
//	private void readMessages() {
//		try {
//			Message message = incomingQueue.take();
////			System.out.println("server receives messsage");
////			message.getCommand().handle(inGameController);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
	
	/* (non-Javadoc)
	 * @see tim.com.server.PlayerConnection#sendMessage(java.lang.String)
	 */
	@Override
	public void sendMessage(String result) {
		Message message = new Message();
		message.setResult(result);
		try {
			System.out.println("server sending message back");
			outgoing.put(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see tim.com.server.PlayerConnection#sendMessage(java.util.List)
	 */
	@Override
	public void sendMessage(List<GameEventCommand> eventList) {
		EventMessage eventMessage = new EventMessage();
		eventMessage.setEventList(eventList);
		try {
			System.out.println("server sending message back");
			outgoing.put(eventMessage);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see tim.com.server.PlayerConnection#sendMessage(tim.com.client.network.command.RoseCommand)
	 */
	@Override
	public void sendMessage(RoseCommand command) {
		Message message = new Message();
		message.setCommand(command);
		try {
			System.out.println("server sending message back");
			outgoing.put(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public GameCallHandler getGameCallHandler() {
		return gameCallHandler;
	}

	/* (non-Javadoc)
	 * @see tim.com.server.network.PlayerConnection#switchCallHandler(tim.com.client.shared.service.GameCallHandler)
	 */
	@Override
	public void switchCallHandler(GameCallHandler gameCallHandler) {
		this.gameCallHandler = gameCallHandler;
		
	}

	/* (non-Javadoc)
	 * @see tim.com.server.network.PlayerConnection#process(java.net.Socket)
	 */
	@Override
	public void process(Socket socket) {
		this.setSocket(socket);
	}

	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * @param socket the socket to set
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

//	private void handleMessage(RoseMessage message) {
//		//invoke the controller for message handling
//		//message.handle(message.getRoseCommand());
//		RoseCommand command = message.getRoseCommand();
//		System.out.println("incoming message with id:" + message.getMessageId());
//		RoseCommandResponse commandResponse = commandHandler.handleCommand(command);
//		RoseNetworkResponse networkResponse = buildNetworkResponse(message, commandResponse);
//		System.out.println("network response message has id on server:" + networkResponse.getActionMessageId());
//		sendResponse(networkResponse);
//		System.out.println("handling response");
//	}
//	
//	private RoseNetworkResponse buildNetworkResponse(RoseMessage message, RoseCommandResponse commandResponse) {
//		RoseNetworkResponse networkResponse = new RoseNetworkResponse();
//		networkResponse.setActionMessageId(message.getMessageId());
//		networkResponse.setResponse(commandResponse);
//		return networkResponse;
//	}
//	
//	private void sendResponse(RoseNetworkResponse response) {
//		ObjectOutputStream oos;
//		try {
//			oos = new ObjectOutputStream(socket.getOutputStream());
//			oos.writeObject(response);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
