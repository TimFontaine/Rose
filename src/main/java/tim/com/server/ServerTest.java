/**
 * 
 */
package tim.com.server;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import tim.com.server.di.Root;

/**
 * @author tim
 *
 */
public class ServerTest {

	/**
	 * 
	 */
	public ServerTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 Weld weld = new Weld();
		   WeldContainer container = weld.initialize();
		   container.instance().select(Root.class).get();
		   weld.shutdown();

	}

}
