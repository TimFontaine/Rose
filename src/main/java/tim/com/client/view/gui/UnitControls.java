/**
 * 
 */
package tim.com.client.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import tim.com.client.RoseClient;
import tim.com.client.controller.GUI;
import tim.com.client.network.ClientConnection;
import tim.com.client.shared.Node;
import tim.com.client.shared.Unit;
import tim.com.client.view.RoseButton;
import tim.data.back.ClientSpecification;
import tim.data.back.Specification;
import tim.namespacetest.client.GameActionProp;
import tim.namespacetest.types.Ability;
import tim.namespacetest.types.PropertiesList;
import tim.namespacetest.types.Source;
import tim.namespacetest.types.TileItem;

/**
 * @author tim
 *
 */
public class UnitControls extends JPanel {
	
	RoseClient client;
	
	public UnitControls(RoseClient client) {
		this.client = client;
		setLayout(new MigLayout());
		setBackground(Color.red); 
		setPreferredSize(new Dimension(200, 200));
		setVisible(true);
//		setSize(100,100);
//		setBounds(50,50,50,50);
	}
	
	public void update(Unit unit) {
		System.out.println("update unitcontrols");
		removeAll();
		if (unit != null) {
	
			ClientSpecification clientSpecification = client.getClientSpecification();
			//default, load the name of the default unit buttons
			List<String> defaults = clientSpecification.getDefaultUnitActions();
			
			for (String defaultAction : defaults) {
				GameActionProp prop = clientSpecification.findGameActionProp(defaultAction);
				RoseButton button = new RoseButton(prop, client.getActionManager() );
				add(button);
			}
			
			List<Ability> abilities = unit.getUnitType().getAbility();
			Ability ab = abilities.get(0);
			List<GameActionProp> props = clientSpecification.getGameActionAbilityProps().get(ab.getAbilityType());
			for (GameActionProp prop: props) {
				RoseButton button = new RoseButton(prop, client.getActionManager() );
				add(button);
			}
		}
		revalidate();
        repaint();
	}
	
	public String findProperty(List<PropertiesList> propertiesList, String name) {
		for (PropertiesList property : propertiesList) {
			if (name.equals(property.getKey())) {
				return property.getValue();
			}
		}
		return "dummy";
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
