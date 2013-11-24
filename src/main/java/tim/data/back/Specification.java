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

import tim.namespacetest.client.ClientConfig;
import tim.namespacetest.client.GameActionProp;
import tim.namespacetest.client.GameActionProps;
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
	
	//Game
	
	private GameSpecification gameSpecification;
	
	
	
	//Client
	private ClientSpecification clientSpecification;
	
	/**
	 * 
	 */
	public Specification() {
//		roseObjects = new HashMap<String, Object>();
//		tileImprovementTypeList = new ArrayList<TileImprovementType>();
//		buildingTypeList = new ArrayList<BuildingType>();
//		terrainTypesList = new ArrayList<TerrainType>();
//		unitTypesList = new ArrayList<UnitType>();
		clientSpecification = new ClientSpecification();
		gameSpecification = new GameSpecification();
		clientSpecification.setGameActionProps(new ArrayList<GameActionProp>());
		clientSpecification.setGameActionAbilityProps(new HashMap<String, List<GameActionProp>>());
		loadGameSpecification();
		loadClientSpecification();

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
	
	public GameSpecification loadGameSpecification() {
		try {
			JAXBContext context = JAXBContext.newInstance(RoseObjects.class);
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		File file = new File("test.xml");
		JAXBElement<RoseObjects> spec = unmarshaller.unmarshal(new StreamSource(file), RoseObjects.class);
		List<TerrainType> terrainTypesList=spec.getValue().getTerrainType();
		List<BuildingType> buildingTypeList =spec.getValue().getBuildingType();
		List<UnitType> unitTypesList =spec.getValue().getUnitType();
		List<TileItem> tileItemList = (spec.getValue().getTileItem());
		List<ResourceType> resourceTypeList =  spec.getValue().getResourceType();
		List<Source> sourceTypeList = spec.getValue().getSourceType();
		
		gameSpecification.setTerrainTypesList(terrainTypesList);
		gameSpecification.setBuildingTypeList(buildingTypeList);
		gameSpecification.setUnitTypesList(unitTypesList);
		gameSpecification.setTileItemList(tileItemList);
		gameSpecification.setResourceTypeList(resourceTypeList);
		gameSpecification.setSourceTypeList(sourceTypeList);
		
//		addGameTypes(tileItemList);
		
//		tileItemList = spec.getValue().get
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return gameSpecification;
	}
	
	public ClientSpecification loadClientSpecification() {
		try {
			JAXBContext context = JAXBContext.newInstance(ClientConfig.class);
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		File file = new File("client.xml");
		JAXBElement<ClientConfig> spec = unmarshaller.unmarshal(new StreamSource(file), ClientConfig.class);
		List<GameActionProp> gameActionProps = spec.getValue().getGameActionProps();
		
//		tileItemList = spec.getValue().get
		List<String> defaultActions = spec.getValue().getUnitDefaultAbility();
		clientSpecification.setDefaultUnitActions(defaultActions);

		handleGameActionProps(gameActionProps);
		clientSpecification.setGameActionProps(gameActionProps);
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return clientSpecification;
	}

	/**
	 * @param gameActionProps
	 */
	private void handleGameActionProps(List<GameActionProp> gameActionProps) {
		for (GameActionProp prop : gameActionProps) {
			if ("other".equals(prop.getGameType())) { 
				//single item
				clientSpecification.getGameActionProps().add(prop);
			} else {
				//grouped item
				String type = prop.getGameType();
				List<GameActionProp> props =  clientSpecification.getGameActionAbilityProps().get(type);
				if (props == null) {
					props = new ArrayList<GameActionProp>();
					clientSpecification.getGameActionAbilityProps().put(prop.getGameType(), props);
				}
				props.add(prop);
			}
		}
	}

//	public void addGameTypes(List<? extends GameType> gameTypeList) {
//		for (GameType gameType : gameTypeList) {
//			roseObjects.put(gameType.getName(), gameType);
//		}
//	}



//	public List<TileImprovementType> getTileImprovementTypeList() {
//		return tileImprovementTypeList;
//	}
//	
//	
//	public List<BuildingType> getBuildingTypeList() {
//		return buildingTypeList;
//	}
//
//
//
//
//	public List<TerrainType> getTerrainTypesList() {
//		return terrainTypesList;
//	}

//	/**
//	 * @param string
//	 * @return
//	 */
//	public Source getSourceType(String name) {
//		for (Source t : sourceTypeList) {
//			if (t.getName().equals(name)) {
//				return t;
//			}
//		}
//		return null;
//	}
	
	
	public <T> T getType(String name, Class<T> type) {
		return null;
	}

	/**
	 * @param string
	 * @return
//	 */
//	public UnitType getUnitType(String name) {
//		for (UnitType t : unitTypesList) {
//			if (t.getName().equals(name)) {
//				return t;
//			}
//		}
//		return null;
//	}
	






//	public List<UnitType> getUnitTypesList() {
//		return unitTypesList;
//	}
//
//
//
//
//	public List<ResourceType> getResourceTypeList() {
//		return resourceTypeList;
//	}




//	/**
//	 * @return the tileItemList
//	 */
//	public List<TileItem> getTileItemList() {
//		return tileItemList;
//	}
//
//
//
//
//	/**
//	 * @param tileItemList the tileItemList to set
//	 */
//	public void setTileItemList(List<TileItem> tileItemList) {
//		this.tileItemList = tileItemList;
//	}




//	/**
//	 * @return the sourceTypeList
//	 */
//	public List<Source> getSourceTypeList() {
//		return sourceTypeList;
//	}
//
//
//
//
//	/**
//	 * @param sourceTypeList the sourceTypeList to set
//	 */
//	public void setSourceTypeList(List<Source> sourceTypeList) {
//		this.sourceTypeList = sourceTypeList;
//	}

	public ClientSpecification getClientSpecification() {
		return clientSpecification;
	}

	public GameSpecification getGameSpecification() {
		return gameSpecification;
	}

}
