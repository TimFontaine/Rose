/**
 * 
 */
package tim.data.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import tim.namespacetest.types.BuildingType;
import tim.namespacetest.types.GameType;
import tim.namespacetest.types.ResourceType;
import tim.namespacetest.types.Source;
import tim.namespacetest.types.TerrainType;
import tim.namespacetest.types.TileItem;
import tim.namespacetest.types.UnitType;

/**
 * @author tim
 *
 */
public class GameSpecification {
	
	private List<TerrainType> terrainTypesList;
	
	private List<TileImprovementType> tileImprovementTypeList;
	
	private List<BuildingType> buildingTypeList;
	
	private List<UnitType> unitTypesList;
	
	private List<ResourceType> resourceTypeList;
	
	private List<TileItem> tileItemList;
	
	private List<Source> sourceTypeList;
	
	private Map<String, GameType> roseObjects;//game logic specific types
	
	public GameSpecification () {
		roseObjects = new HashMap<String, GameType>();
	}
	
	public enum TileType {
		CITY,
		MINE,
		ROAD;
	}
	
	public Object getGameSpecificType(String name, Class c) {
		for (Entry<String, ? extends GameType> entry : roseObjects.entrySet()) {
			if (entry.getKey().equals(name)) {
				if (entry.getValue().getClass() == c) {
					return entry.getValue();
				} else {
					System.out.println("retreiving object name with wrong class ");
				}
			}
		}
		return null;
		
	}

	/**
	 * @param string
	 * @return
	 */
	public TerrainType getTerrainType(String id) {
		for (TerrainType t : terrainTypesList) {
			if (t.getName().equals(id)) {
				return t;
			}
		}
		return null;
	}
	
	/**
	 * @param string
	 * @return
	 */
	public TileItem getTileItem(String name) {
		for (TileItem t : tileItemList) {
			if (t.getName().equals(name)) {
				return t;
			}
		}
		return null;
	}

	/**
	 * @return the terrainTypesList
	 */
	public List<TerrainType> getTerrainTypesList() {
		return terrainTypesList;
	}

	/**
	 * @param terrainTypesList the terrainTypesList to set
	 */
	public void setTerrainTypesList(List<TerrainType> terrainTypesList) {
		this.terrainTypesList = terrainTypesList;
	}

	/**
	 * @return the tileImprovementTypeList
	 */
	public List<TileImprovementType> getTileImprovementTypeList() {
		return tileImprovementTypeList;
	}

	/**
	 * @param tileImprovementTypeList the tileImprovementTypeList to set
	 */
	public void setTileImprovementTypeList(List<TileImprovementType> tileImprovementTypeList) {
		this.tileImprovementTypeList = tileImprovementTypeList;
	}

	/**
	 * @param tileImprovementTypeList2
	 */
	private void addToGameObjects(
			List<? extends GameType> roseTypeObjectList) {
		for (GameType roseTypeObject : roseTypeObjectList) {
			roseObjects.put(roseTypeObject.getName(), roseTypeObject);
		}
	}

	/**
	 * @return the buildingTypeList
	 */
	public List<BuildingType> getBuildingTypeList() {
		return buildingTypeList;
	}

	/**
	 * @param buildingTypeList the buildingTypeList to set
	 */
	public void setBuildingTypeList(List<BuildingType> buildingTypeList) {
		this.buildingTypeList = buildingTypeList;
	}

	/**
	 * @return the unitTypesList
	 */
	public List<UnitType> getUnitTypesList() {
		return unitTypesList;
	}

	/**
	 * @param unitTypesList the unitTypesList to set
	 */
	public void setUnitTypesList(List<UnitType> unitTypesList) {
		this.unitTypesList = unitTypesList;
	}

	/**
	 * @return the resourceTypeList
	 */
	public List<ResourceType> getResourceTypeList() {
		return resourceTypeList;
	}

	/**
	 * @param resourceTypeList the resourceTypeList to set
	 */
	public void setResourceTypeList(List<ResourceType> resourceTypeList) {
		this.resourceTypeList = resourceTypeList;
	}

	/**
	 * @return the tileItemList
	 */
	public List<TileItem> getTileItemList() {
		return tileItemList;
	}

	/**
	 * @param tileItemList the tileItemList to set
	 */
	public void setTileItemList(List<TileItem> tileItemList) {
		this.tileItemList = tileItemList;
		addToGameObjects(tileItemList);
	}

	/**
	 * @return the sourceTypeList
	 */
	public List<Source> getSourceTypeList() {
		return sourceTypeList;
	}

	/**
	 * @param sourceTypeList the sourceTypeList to set
	 */
	public void setSourceTypeList(List<Source> sourceTypeList) {
		this.sourceTypeList = sourceTypeList;
	}

	/** 
	 * @return the roseObjects
	 */
	public Map<String, GameType> getRoseObjects() {
		return roseObjects;
	}

	/**
	 * @param roseObjects the roseObjects to set
	 */
	public void setRoseObjects(Map<String, GameType> roseObjects) {
		this.roseObjects = roseObjects;
	}

}
