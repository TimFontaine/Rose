/**
 * 
 */
package tim.com.client;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import tim.data.back.BuildingType;
import tim.data.back.Specification;

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
		
		Specification specification = roseClient.getGame().getSpecification();
		for (BuildingType buildingType : specification.getBuildingTypeList()) {
			JButton buildingButtuon = new JButton(buildingType.getId());
			add(buildingButtuon);
		}
	}

}
