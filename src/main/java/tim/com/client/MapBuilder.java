/**
 * 
 */
package tim.com.client;

import tim.data.back.GameSpecification;
import tim.data.back.Specification;
import tim.game.Map;
import tim.namespacetest.types.TerrainType;

/**
 * @author tim
 *
 */
public class MapBuilder {
	
	public Map build(GameSpecification specification) {
		TerrainType plain = specification.getTerrainType("plain");
		TerrainType mountain = specification.getTerrainType("mountain");
		Map map = new Map(50,50);
		for (int i = 0; i< map.getWidth(); i++) {
			for (int j = 0; j<map.getHeight(); j++) {
				map.getNode(i, j).setTerrainType(plain);
			}
		}
		for(int i=6;i<12;i++) {
			map.getNode(i, 10).setTerrainType(mountain);
		}
		
		
		return map;
	}
}
