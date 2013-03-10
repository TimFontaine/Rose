/**
 * 
 */
package tim.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import tim.data.back.Node;

/**
 * @author tfontaine
 *
 */
public class RandomTiles {
	
	
	
	/**
	 * 
	 */
		  public static void shuffleList(List<Node> a) {
		    int n = a.size();
		    Random random = new Random();
		    random.nextInt();
		    for (int i = 0; i < n; i++) {
		      int change = i + random.nextInt(n - i);
		      swap(a, i, change);
		    }
		  }
		  
		  public static void shuffleList(int[] a) {
			    int n = a.length;
			    Random random = new Random();
			    random.nextInt();
			    for (int i = 0; i < n; i++) {
			      int change = i + random.nextInt(n - i);
			      swap(a, i, change);
			    }
			  }


		  private static void swap(List<Node> a, int i, int change) {
		    Node helper = a.get(i);
		    a.set(i, a.get(change));
		    a.set(change, helper);
		  }
		  
		  private static void swap(int[] a, int i, int change) {
			    int helper = a[i];
			    a[i] = a[change];
			    a[change]= helper;
			  }

		 
}
