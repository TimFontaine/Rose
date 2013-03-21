/**
 * 
 */
package tim.game.usercentric;

import tim.game.ai.data.MutableResource.Resource;

/**
 * @author tfontaine
 *
 */
public interface ResourceEntity {

	public Resource provides();
	
	public int retreive();
}
