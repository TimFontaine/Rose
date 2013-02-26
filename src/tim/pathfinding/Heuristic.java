package tim.pathfinding;

public interface Heuristic {

	public abstract int getEstimatedDistanceToGoal(int startX, int startY,
			int destinationX, int destinationY);

}