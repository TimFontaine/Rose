/**
 * 
 */
package tim.com.server.di;

/**
 * @author tim
 *
 */
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

import java.math.BigDecimal;

/**
*
* @author peter
*/
public interface TaxCalculation {
        /**
         * Calculates the tax based on monthly income
         * @param monthlyIncome
         * @return
         */
        public BigDecimal calculateBasedOnMonthlyIncome(BigDecimal monthlyIncome);
}
