/**
 * 
 */
package tim.com.client;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import tim.com.client.controller.GUI;
import tim.data.back.GameSpecification;
import tim.data.back.Specification;
import tim.namespacetest.types.BuildingType;

/**
 * @author tfontaine
 *
 */
public class BuildPanel extends JPanel {
	
	private RoseClient roseClient;
	
	private GUI gui;

	/**
	 * 
	 */
	public BuildPanel(RoseClient roseClient, GUI gui) {
		this.roseClient = roseClient;
		this.gui = gui;
		
		setLayout(new MigLayout());
		
		GameSpecification specification = roseClient.getGame().getGameSpecification();
		for (BuildingType buildingType : specification.getBuildingTypeList()) {
			JButton buildingButtuon = new JButton(buildingType.getName());
			add(buildingButtuon);
		}
	}

}
