/**
 * 
 */
package tim.com.client.view.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import tim.com.client.RoseClient;
import tim.com.client.controller.GUI;
import tim.com.client.shared.City;
import tim.com.client.shared.Unit;
import net.miginfocom.swing.MigLayout;

/**
 * @author tim
 *
 */
public class UnitPanel extends JPanel implements ActionListener {
	
	private City city;

	JList<String> list;
	
	DefaultListModel<String> model;
	
	GUI gui;
	
	public UnitPanel(RoseClient client, final GUI gui, final City city) {
		this.city = city;
		this.gui = gui;
		setLayout(new MigLayout());
		list = new JList<String>();
		model = new DefaultListModel<String>();
		
		
		list.setModel(model);
		updateModel();
		add(list);
		list.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = list.getSelectedIndex();
				Unit unit = city.getUnits().get(index);
				gui.setActiveUnit(unit);
//				gui.refresh();
			}
		});
		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		//a unit is clicked
		if (event.getActionCommand().equals("unit")) {
		}
		
	}
	
	private void updateModel() {
		model.clear();
		for (Unit unit : city.getUnits()) {
			model.addElement(unit.getUnitType().getName());
		}
	}
	
	public void update() {
		//update model
		updateModel();
	}

}
