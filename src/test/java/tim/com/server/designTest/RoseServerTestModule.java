/**
 * 
 */
package tim.com.server.designTest;


import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * @author tim
 *
 */
public class RoseServerTestModule extends AbstractModule {

	/**
	 * 
	 */
	public RoseServerTestModule() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().build(RoseServerTestFactory.class));
	}

}
