/**
 * 
 */
package tim.com.server.designtest;

import java.util.concurrent.ArrayBlockingQueue;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

/**
 * @author tim
 *
 */
@Singleton
public class Root {
	
	private ArrayBlockingQueue<InEvent> incoming = new ArrayBlockingQueue<InEvent>(10);
	private ArrayBlockingQueue<OutEvent> outGoing = new ArrayBlockingQueue<OutEvent>(10);
	
	private Factory factory;

	/**
	 * 
	 */
	public Root() {
	}
	
	public static void main(String args[]) {
		Injector injector = Guice.createInjector(new RoseServerModule());
	    Root billingService = injector.getInstance(Root.class);

	}

	public ArrayBlockingQueue<InEvent> getIncoming() {
		return incoming;
	}

	public ArrayBlockingQueue<OutEvent> getOutGoing() {
		return outGoing;
	}

	/**
	 * @return
	 */
	public Factory getFactory() {
		return factory;
	}


}
