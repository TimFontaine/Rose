/**
 * 
 */
package tim.data.back;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

import tim.namespacetest.types.GameType;
import tim.namespacetest.types.ResourceType;
import tim.namespacetest.types.RoseObjects;
import tim.namespacetest.types.Source;
import tim.namespacetest.types.TerrainType;
import tim.namespacetest.types.BuildingType;
import tim.namespacetest.types.TileItem;
import tim.namespacetest.types.UnitType;


/**
 * @author tfontaine
 *
 */
public class Specification {
	
	private List<TerrainType> terrainTypesList;
	
	private List<TileImprovementType> tileImprovementTypeList;
	
	private List<BuildingType> buildingTypeList;
	
	private List<UnitType> unitTypesList;
	
	private List<ResourceType> resourceTypeList;
	
	private List<TileItem> tileItemList;
	
	private List<Source> sourceTypeList;
	
	private Map<String, Object> roseObjects;
	
	
	

	/**
	 * 
	 */
	public Specification() {
		roseObjects = new HashMap<String, Object>();
		tileImprovementTypeList = new ArrayList<TileImprovementType>();
		buildingTypeList = new ArrayList<BuildingType>();
		terrainTypesList = new ArrayList<TerrainType>();
		unitTypesList = new ArrayList<UnitType>();
		try {
			JAXBContext context = JAXBContext.newInstance(RoseObjects.class);
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		File file = new File("test.xml");
		JAXBElement<RoseObjects> spec = unmarshaller.unmarshal(new StreamSource(file), RoseObjects.class);
		terrainTypesList=spec.getValue().getTerrainType();
		buildingTypeList =spec.getValue().getBuildingType();
		unitTypesList =spec.getValue().getUnitType();
		setTileItemList(spec.getValue().getTileItem());
		resourceTypeList = spec.getValue().getResourceType();
		sourceTypeList = spec.getValue().getSourceType();
		tileItemList = spec.getValue().getTileItem();
		addGameTypes(tileItemList);
		
//		tileItemList = spec.getValue().get
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		readerMap = new HashMap<String, Reader<? extends RoseTypeObject>>();
//		readerMap.put("terrain-types", new Reader<TerrainType>(TerrainType.class, terrainTypesList));
//		readerMap.put("tileimprovement-types", new Reader<TileImprovementType>(TileImprovementType.class , tileImprovementTypeList));
//		readerMap.put("building-types", new Reader<BuildingType>(BuildingType.class , buildingTypeList));
//		
//		File file = new File("test.xml");
//		try {
//			InputStream is = new FileInputStream(file);
//			load(is);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}

	public void addGameTypes(List<? extends GameType> gameTypeList) {
		for (GameType gameType : gameTypeList) {
			roseObjects.put(gameType.getName(), gameType);
		}
	}



	public List<TileImprovementType> getTileImprovementTypeList() {
		return tileImprovementTypeList;
	}
	
	
	public static void main(String args[]) throws FileNotFoundException, XMLStreamException {
		
	}
	
	
	


	public List<BuildingType> getBuildingTypeList() {
		return buildingTypeList;
	}




	public List<TerrainType> getTerrainTypesList() {
		return terrainTypesList;
	}

	/**
	 * @param string
	 * @return
	 */
	public Source getSourceType(String name) {
		for (Source t : sourceTypeList) {
			if (t.getName().equals(name)) {
				return t;
			}
		}
		return null;
	}
	
	
	public <T> T getType(String name, Class<T> type) {
		return null;
	}

	/**
	 * @param string
	 * @return
	 */
	public UnitType getUnitType(String name) {
		for (UnitType t : unitTypesList) {
			if (t.getName().equals(name)) {
				return t;
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





	public List<UnitType> getUnitTypesList() {
		return unitTypesList;
	}




	public List<ResourceType> getResourceTypeList() {
		return resourceTypeList;
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

}
