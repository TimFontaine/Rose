/**
 * 
 */
package tim.com.client.view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tim.com.client.RoseClient;
import tim.com.client.shared.City;
import tim.com.client.shared.Unit;
import tim.data.back.GameSpecification;
import tim.data.back.Specification;
import tim.namespacetest.types.BuildingType;
import tim.namespacetest.types.Resource;
import tim.namespacetest.types.ResourceType;
import tim.namespacetest.types.UnitType;
import net.miginfocom.swing.MigLayout;

/**
 * @author tim
 *
 */
public class ProducablePanel extends JPanel implements ActionListener {
	
	JTabbedPane productionPane;
	JTabbedPane resourcePane;
	
	CityPanel cityPanel;
	
	private final RoseClient client;
	
	private final City city;
	
	public ProducablePanel(final City city, final RoseClient client, final CityPanel parent) {
//		setLayout(new GridLayout(1, 1));
		setLayout(new MigLayout("fill"));
		this.cityPanel = parent;
		this.client = client;
		this.city = city;
		productionPane = new JTabbedPane();
		final GameSpecification specification = client.getGame().getGameSpecification();
		
		JPanel unitTab = constructUnitTab();
		JPanel resourceTab = constructResourcesTab();
		JPanel buildingsTab = constructBuildingsTab();
		final JList<String> buildingList = new JList<String>();
		
		productionPane.addTab("units", unitTab);
		productionPane.addTab("resources", resourceTab);
//		productionPane.setSize(300,300);
		add(productionPane,"grow");
		
//		unitList.addListSelectionListener(new ListSelectionListener() {
		productionPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
//			
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				int selected = unitList.getSelectedIndex();
//				System.out.println("selected is:  " + specification.getUnitTypesList().get(selected).getName());
//			}
//		});
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(Color.BLUE);
		setPreferredSize(new Dimension(500,500));
		System.out.println("before pack size is: " + productionPane.getSize());
	}
	

	/**
	 * @return
	 */
	private JPanel constructBuildingsTab() {
		JPanel panel = new JPanel();
		return panel;
	}


	/**
	 * @return
	 */
	private JPanel constructUnitTab() {
		JPanel panel = new JPanel();
		final JList<String> unitList = new JList<String>();
		
		DefaultListModel<String> unitModel = new DefaultListModel<String>();
		for (UnitType type : client.getGame().getGameSpecification().getUnitTypesList()) {
			unitModel.addElement(type.getName());
		}
		
		panel.add(unitList);
		unitList.setModel(unitModel);
		
unitList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("mouse clicked");
				int index = unitList.getSelectedIndex();
				UnitType type = client.getGame().getGameSpecification().getUnitTypesList().get(index);
				city.orderUnit(type);
				cityPanel.updateProduction();
			}
		});
		
		return panel;
	}
	
	/**
	 * @return
	 */
	private JPanel constructResourcesTab() {
		JPanel panel = new JPanel();
		final JList<String> resourceList = new JList<String>();
		
		DefaultListModel<String> resourceModel = new DefaultListModel<String>();
		for (ResourceType type : client.getGame().getGameSpecification().getResourceTypeList()) {
			resourceModel.addElement(type.getName());
		}
		
		panel.add(resourceList);
		panel.setSize(200, 200);
		resourceList.setModel(resourceModel);
		
		resourceList.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				System.out.println("mouse clicked");
//				int index = resourceList.getSelectedIndex();
//				UnitType type = client.getGame().getSpecification().getUnitTypesList().get(index);
//				Unit unit = new Unit(type, client.getPlayer());
//				unit.setLocation(city.getPosition());
////				city.addUnit(unit);
//				cityPanel.updateProduction();
			}
		});
		
		return panel;
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
