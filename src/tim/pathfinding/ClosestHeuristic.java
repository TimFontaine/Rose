/**
 * 
 */
package tim.pathfinding;

/**
 * @author tim
 *
 */
public class ClosestHeuristic implements Heuristic {
	
	/* (non-Javadoc)
	 * @see tim.pathfinding.Heuristic#getEstimatedDistanceToGoal(int, int, int, int)
	 */
	@Override
	public int getEstimatedDistanceToGoal(int startX, int startY, int destinationX, int destinationY) {
		return Math.abs(startX - destinationX)  + (Math.abs(startY - destinationY)) ;
	}
}
