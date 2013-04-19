/**
 * 
 */
package tim.data.back;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.xml.sax.XMLReader;

import tim.rose.buttons.actions.LoadAction;

/**
 * @author tfontaine
 *
 */
public class Specification {
	
	private Map<String, Reader<? extends RoseTypeObject>> readerMap;
	
	private List<TileImprovementType> tileImprovementTypeList;
	
	private List<BuildingType> buildingTypeList;
	
	
	

	/**
	 * 
	 */
	public Specification() {
		tileImprovementTypeList = new ArrayList<TileImprovementType>();
		buildingTypeList = new ArrayList<BuildingType>();
		
		readerMap = new HashMap<String, Reader<? extends RoseTypeObject>>();
		readerMap.put("tileimprovement-types", new Reader<TileImprovementType>(TileImprovementType.class , tileImprovementTypeList));
		readerMap.put("building-types", new Reader<BuildingType>(BuildingType.class , buildingTypeList));
		
		File file = new File("test.xml");
		try {
			InputStream is = new FileInputStream(file);
			load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}




	public List<TileImprovementType> getTileImprovementTypeList() {
		return tileImprovementTypeList;
	}
	
	
	public static void main(String args[]) throws FileNotFoundException, XMLStreamException {
		new Specification();
	}
	
	public void load(InputStream is) {
		XMLInputFactory  inputFactory = XMLInputFactory.newInstance();
		try {
			XMLStreamReader xsr = inputFactory.createXMLStreamReader(is);
			xsr.nextTag();
			load(xsr); 
			
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	/**
	 * @param xsr
	 * @throws XMLStreamException 
	 */
	private void load(XMLStreamReader xsr) throws XMLStreamException {

		while (xsr.nextTag() != XMLStreamConstants.END_ELEMENT) {
			String name = xsr.getLocalName();
			Reader<? extends RoseTypeObject> reader = readerMap.get(name);
			System.out.println(xsr.getLocalName());
			if (reader != null) {
				reader.readChildren(xsr);
			}
//			xsr.nextTag();//goto end tag
			System.out.println("size:" + tileImprovementTypeList.size());
		}
	}
	
	
	private class Reader<T> {
		private List<T> typeList;
		Class<T> classType;
		
		public Reader(Class<T> classType, List<T> typeList) {
			this.typeList = typeList;
			this.classType = classType;
		}
		
		public void readChildren(XMLStreamReader xsr) throws XMLStreamException {
			while (xsr.nextTag() != XMLStreamConstants.END_ELEMENT) {
				if (xsr.getEventType() == XMLStreamConstants.START_ELEMENT) {
					T roseTypeObject = getType(xsr, classType);
					typeList.add(roseTypeObject);
				}
				if (xsr.getEventType() == XMLStreamConstants.END_ELEMENT) {
					System.out.println("end" + xsr.getEventType() + xsr.getLocalName());
				}
				xsr.nextTag();//goto end tag
			}
		}
	}
	
	

	/**
	 * @param <T>
	 * @param xsr
	 * @return 
	 * @throws XMLStreamException 
	 */
	private <T> T getType(XMLStreamReader xsr, Class<T> classType) throws XMLStreamException {
		String id = xsr.getAttributeValue(null, "id");
		try {
			Constructor<T> constructor = classType.getConstructor(String.class);
			T t = constructor.newInstance(id);
			return t;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}




	public List<BuildingType> getBuildingTypeList() {
		return buildingTypeList;
	}

}
