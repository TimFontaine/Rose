/**
 * 
 */
package tim.com.server.di.google;

import java.util.Date;


/**
 * @author tim
 *
 */
public interface PaymentFactory {
	Payment create(Date startDate, String amount);

}
