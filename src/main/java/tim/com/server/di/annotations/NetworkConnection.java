/**
 * 
 */
package tim.com.server.di.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @author tim
 *
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface NetworkConnection {

}
