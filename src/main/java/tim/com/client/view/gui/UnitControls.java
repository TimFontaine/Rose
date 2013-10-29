/**
 * 
 */
package tim.com.client.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import tim.com.client.RoseClient;
import tim.com.client.controller.GUI;
import tim.com.client.network.Client;
import tim.com.client.shared.Node;
import tim.com.client.shared.Unit;
import tim.data.back.Specification;
import tim.namespacetest.types.Source;
import tim.namespacetest.types.TileItem;

/**
 * @author tim
 *
 */
public class UnitControls extends JPanel {
	
	Specification specification;
	
	public UnitControls(RoseClient client) {
		specification = client.getGame().getSpecification();
		setLayout(null);
		setBackground(Color.red); 
		setPreferredSize(new Dimension(200, 200));
		add(new JButton("hello unit controls"));
		setVisible(true);
//		setSize(100,100);
//		setBounds(50,50,50,50);
	}
	
	public void update(Unit unit) {
		System.out.println("update unitcontrols");
//		if (unit == null) {
//			return;
//		}
//		Node node = unit.getTile();
//		if (unit.hasAbility("build")) {
//			for (TileItem tileItem : specification.getTileItemList()) {
//				String name = tileItem.getName();
//				JButton button = new JButton(name);
//				add(button);
//			}
//			
////			if (node.getSource() != null) {
////				
////				JButton button = new JButton("build source");
////				add(button);
////			}
//		}
//		revalidate();
        repaint();
	}
	
	public void paintComponent(Graphics g) {
		System.out.println("painting unitcontrols");
		super.paintComponent(g);
	}

}
