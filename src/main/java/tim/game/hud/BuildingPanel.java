/**
 * 
 */
package tim.game.hud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import tim.data.building.Building;
import tim.game.Back;
import tim.game.buttons.GameButton;
import tim.game.factory.GameApplicationFactory;
import tim.game.usercentric.CentricWorker;
import tim.game.usercentric.InterfaceTranslator;
import tim.rose.buttons.actions.BuildAction;

/**
 * @author tim
 *
 */
public class BuildingPanel extends JPanel implements ActionListener {
	
InterfaceTranslator translator;
	
	public BuildingPanel() {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		Back back = applicationFactory.getBack();
		translator = back.getTrans();
		build();
	}

	/**
	 * 
	 */
	private void build() {
		Building building = translator.getSelectedBuilding();
		List<String> buildings = building.getPossibleActions();
		for (String name : buildings) {
			GameButton button = new GameButton(this);
			button.setRoseAction(new BuildAction(name));
			button.setIcon(name);
			add(button);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
