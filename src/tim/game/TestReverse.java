/**
 * 
 */
package tim.game;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author tfontaine
 *
 */
public class TestReverse {

	/**
	 * 
	 */
	public TestReverse() {
		// TODO Auto-generated constructor stub
	}
	
	 public static void main(String[] args) {
	      // create array list object
	      ArrayList arrlst = new ArrayList();
	      
	      // populate the list
	      arrlst.add("A");
	      arrlst.add("B");
	      arrlst.add("C");
	      arrlst.add("D");
	      arrlst.add("E");

	      System.out.println("The initial list is :"+arrlst);
	      
	      // reverse the list
	      Collections.reverse(arrlst);
	      
	      System.out.println("The Reverse List is :"+arrlst);
	   }

}
