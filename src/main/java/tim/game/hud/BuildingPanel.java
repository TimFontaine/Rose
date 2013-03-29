/**
 * 
 */
package tim.game.hud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import tim.data.back.TileInfo;
import tim.data.building.Building;
import tim.game.Back;
import tim.game.Logic;
import tim.game.ai.data.ResourceInfo;
import tim.game.buttons.GameButton;
import tim.game.factory.GameApplicationFactory;
import tim.game.usercentric.WorkerActor;
import tim.game.usercentric.InterfaceTranslator;
import tim.rose.buttons.actions.BuildAction;
import tim.rose.buttons.actions.BuildingOrderAction;

/**
 * @author tim
 *
 */
public class BuildingPanel extends JPanel implements ActionListener {
	
	InterfaceTranslator translator;
	ResourceInfo resourceInfo;
	TileInfo tileInfo;
	
	public BuildingPanel(TileInfo tileInfo) {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		translator = applicationFactory.getInterfaceTranslator();
		resourceInfo = applicationFactory.getResourceInfo();
		this.tileInfo = tileInfo;
		build();
	}

	/**
	 * 
	 */
	private void build() {
		List<String> buildingActions = tileInfo.getPossibleActions();
		for (String name : buildingActions) {
			GameButton button = new GameButton(this);
			button.setRoseAction(new BuildingOrderAction(name));
			button.setIcon(name);
			add(button);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
GameButton button = (GameButton) e.getSource();
		
		//put the event in a queue and process later
			Logic.addToEventQueue(button);
			if (button.getRoseAction() != null) {
				Logic.addToEventQueue(button.getRoseAction());
			}
		
	}
}
