/**
 * 
 */
package tim.com.server.designtest;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * @author tim
 *
 */
public class RoseServerModule extends AbstractModule {

	/**
	 * 
	 */
	public RoseServerModule() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().build(RoseServerFactory.class));
	}

}
