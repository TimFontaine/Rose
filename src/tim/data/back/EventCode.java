/**
 * 
 */
package tim.data.back;

/**
 * @author tfontaine
 *
 */
public interface EventCode {
	public static final int OIL_EMPTY = 1;
	public static final int GIVE_OIL = 2;
	public static final int OIL_DELIVERED = 3;
	
	public static final int UNIT_CANNOT_MOVE = 4;
	public static final int TILE_BLOCKED = 5;
	
	public static final int SUCCESS = 6;
	public static final int RESOURCE_PICKUP = 7;
	public static final int RESOURCE_DELIVERY = 8;
	public static final int TURN_FINISHED = 9;
}
