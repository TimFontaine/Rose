/**
 * 
 */
package tim.game.ai;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author tfontaine
 *
 */
public class SortedSetTest {

	/**
	 * 
	 */
	public SortedSetTest() {
		SortedSet<Integer> set = new TreeSet<Integer>(new MyComp());
		set.add(4);
		set.add(3);
		set.add(5);
		
		for (Integer i : set) {
			System.out.println(i);
		}
	}
	
	public static void main(String args[]) {
		new SortedSetTest();
	}
	
	class MyComp implements Comparator<Integer> {

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Integer o1, Integer o2) {
			if (o1 < o2) {
				return -1;
			} else if (o2 < o1) {
				return 1;
			}  
			return 0;
		}
		
	}

}
