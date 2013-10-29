/**
 * 
 */
package tim.com.client.view.panels;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import tim.com.client.RoseClient;
import tim.com.client.shared.City;
import tim.namespacetest.types.BuildingType;

/**
 * @author tim
 *
 */
public class BuildingProductionPanel extends JPanel {
	
	public BuildingProductionPanel(final City city, final RoseClient client, final CityPanel parent) {
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> buildingList = new JList<String>();
		buildingList.setModel(model);
		
		for (BuildingType type : client.getGame().getSpecification().getBuildingTypeList()) {
			model.addElement(type.getName());
		}
	}
}
