/**
 * 
 */
package tim.com.client;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import tim.data.back.TileImprovementType;
import tim.rose.buttons.actions.BuildAction;

import net.miginfocom.swing.MigLayout;

/**
 * @author tfontaine
 *
 */
public class MapControls extends JPanel {

	/**
	 * 
	 */
	public MapControls(RoseClient client) {
		this.setFocusable(false);
		ActionManager actionManager = client.getActionManager();
		
		setLayout(new MigLayout());
		this.setSize(this.getPreferredSize());
		JButton button = new JButton();
		button.setAction(new BuildAction(client.getInGameController()));
		button.setSize(button.getPreferredSize());
		button.setText("city");
		add(button);
		
		List<TileImprovementType> improvementList = client.getGame().getSpecification().getTileImprovementTypeList();
		for (TileImprovementType type : improvementList) {
			JButton b = new JButton();
			String id = type.getId();
			RoseAction action = actionManager.getRoseAction(id);
			b.setAction(action);
			b.setSize(b.getPreferredSize());
			b.setText(type.getId());
			add(b); 
		}
		
		
	}

	public void addToCanvas(Canvas canvas){
		this.setSize(this.getPreferredSize());
		this.setLocation(100, 100);
		canvas.add(this);
	}
}
