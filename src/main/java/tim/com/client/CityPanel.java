/**
 * 
 */
package tim.com.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.omg.CORBA.FREE_MEM;

import tim.data.back.BuildingType;
import tim.data.back.Specification;

import net.miginfocom.swing.MigLayout;

/**
 * @author tfontaine
 *
 */
public class CityPanel extends JPanel implements ActionListener {
	
	private RoseClient roseClient;
	
	private GUI gui;
	
	private City city;
	
	public CityPanel(RoseClient roseClient, GUI gui, City city) {
		this.roseClient = roseClient;
		this.gui = gui;
		this.city = city;
		
		setLayout(new MigLayout());
		setFocusable(false);
		Specification specification = roseClient.getGame().getSpecification();
		for (BuildingType buildingType : specification.getBuildingTypeList()) {
			JButton buildingButtuon = new JButton(buildingType.getId());
			buildingButtuon.addActionListener(this);
			add(buildingButtuon);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if ("farm".equals(command)) {
			System.out.println("building farm");
			getController().buildingOrder(city, "farm");
		} else if ("factory".equals(command)) {
			System.out.println("building factory");
		}
	}
	
	private InGameController getController() {
		return roseClient.getInGameController();
	}

}
