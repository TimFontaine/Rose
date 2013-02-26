/**
 * 
 */
package tim.pathfinding;

/**
 * @author tfontaine
 *
 */
public class RoadHeuristic implements Heuristic {

	/**
	 * 
	 */
	public RoadHeuristic() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see tim.pathfinding.Heuristic#getEstimatedDistanceToGoal(int, int, int, int)
	 */
	@Override
	public int getEstimatedDistanceToGoal(int startX, int startY,
			int destinationX, int destinationY) {
		// TODO Auto-generated method stub
		return Math.abs(startX - destinationX) + Math.abs(startY - destinationY);
	}

}
