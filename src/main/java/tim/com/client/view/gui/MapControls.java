/**
 * 
 */
package tim.com.client.view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

import tim.com.client.RoseClient;
import tim.com.client.actions.BuildCityAction;
import tim.com.client.controller.ActionManager;
import tim.com.client.controller.GUI;
import tim.com.client.view.Canvas;
import tim.data.back.TileImprovementType;
import net.miginfocom.swing.MigLayout;

/**
 * @author tfontaine
 *
 */
public class MapControls {
	
	JButton button;
	
	GUI gui;
	
	RoseClient client;
	
	UnitControls unitControls;

	/**
	 * 
	 */
	public MapControls(RoseClient client, GUI gui) {
		this.client = client;
		this.gui = gui;
		ActionManager actionManager = client.getActionManager();
		this.gui = gui;
		
//		List<TileImprovementType> improvementList = client.getGame().getSpecification().getTileImprovementTypeList();
//		for (TileImprovementType type : improvementList) {
//			JButton b = new JButton();
//			String id = type.getId();
//			RoseAction action = actionManager.getRoseAction(id);
//			b.setAction(action);
//			b.setSize(b.getPreferredSize());
//			b.setText(type.getId());
//			add(b); 
//		}
		
	}

	public void addToComponent(Canvas canvas){
		
		unitControls = new UnitControls(client);
		unitControls.setSize(canvas.getWidth(),100);
		unitControls.setLocation(0, canvas.getHeight() - unitControls.getHeight());
		unitControls.setOpaque(true);
		canvas.add(unitControls);
//		unitControls.repaint();
		System.out.println("unitcontrols should be visible");
		
		
	}
	
	public void update() {
		unitControls.update(gui.getActiveUnit());
	}
}
