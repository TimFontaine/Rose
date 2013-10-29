/**
 * 
 */
package tim.com.client.view.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;





import tim.com.client.RoseClient;
import tim.com.client.controller.GUI;
import tim.com.client.game.InGameController;
import tim.com.client.shared.City;
import tim.data.back.Specification;
import tim.namespacetest.types.BuildingType;
import tim.namespacetest.types.Resource;
import tim.namespacetest.types.UnitType;
import net.miginfocom.swing.MigLayout;

/**
 * @author tfontaine
 *
 */
public class CityPanel extends JPanel implements ActionListener {
	
	private RoseClient roseClient;
	
	private GUI gui;
	
	private City city;
	
	private UnitPanel unitPanel;
	
	private ProducablePanel producablePanel;
	
	private Map<String, Resource> resourceType;
	
	public CityPanel(RoseClient roseClient, final GUI gui, City city) {
		this.roseClient = roseClient;
		this.gui = gui;
		this.city = city;
		setLayout(new MigLayout());
		setFocusable(false);
		this.setSize(this.getPreferredSize());
		unitPanel = new UnitPanel(roseClient, gui, city);
		producablePanel = new ProducablePanel(city, roseClient, this);
		
		add(producablePanel, "wrap");
		add(unitPanel, "wrap");
		JButton close = new JButton("close");
		close.addMouseListener(new MouseListener() {
			
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
				gui.close(CityPanel.this);
			}
		});
		add(close);
		System.out.println("citypanel has preffersize of x:" + getPreferredSize().width + " ,y:" + getPreferredSize().height );
		setSize(600,600);
		repaint();
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
	
	public void updateProduction() {
		unitPanel.update();
	}

}
