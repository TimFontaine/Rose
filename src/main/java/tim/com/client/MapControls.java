/**
 * 
 */
package tim.com.client;

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
		ActionManager actionManager = client.getActionManager();
		
		setLayout(new MigLayout());
		JButton button = new JButton("hello");
		add(button);
		button.setAction(new BuildAction(client.getInGameController()));
		List<TileImprovementType> improvementList = client.getGame().getSpecification().getTileImprovementTypeList();
		for (TileImprovementType type : improvementList) {
			JButton b = new JButton("boe");
			String id = type.getId();
			RoseAction action = actionManager.getRoseAction(id);
			b.setAction(action);
			add(b); 
		}
		
		
	}

	public void addToCanvas(Canvas canvas){
		this.setSize(this.getPreferredSize());
		this.setLocation(100, 100);
		canvas.add(this);
	}
}
