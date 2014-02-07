/**
 * 
 */
package tim.com.server.network;

import java.net.Socket;
import java.util.List;

import tim.com.client.network.command.RoseCommand;
import tim.com.client.network.command.client.GameEventCommand;
import tim.com.client.shared.service.GameCallHandler;

/**
 * @author tim
 *
 */
public interface PlayerConnection {

	/**
	 * @author tim
	 *
	 */
	public interface Factory {
		PlayerConnection create(Socket socket);
	}

	public abstract void sendMessage(String result);

	public abstract void sendMessage(List<GameEventCommand> eventList);

	public abstract void sendMessage(RoseCommand command);
	
	public abstract void switchCallHandler(GameCallHandler gameCallHandler);
	
	public void process(Socket socket);

}