/**
 * 
 */
package tim.game.hud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import tim.data.back.TileInfo;
import tim.game.Back;
import tim.game.Logic;
import tim.game.ai.data.ResourceInfo;
import tim.game.buttons.GameButton;
import tim.game.factory.GameApplicationFactory;
import tim.game.usercentric.Actor;
import tim.game.usercentric.WorkerActor;
import tim.game.usercentric.InterfaceTranslator;
import tim.rose.buttons.actions.BuildAction;

/**
 * @author tim
 *
 */
public class UnitPanel extends JPanel implements ActionListener {
	
	InterfaceTranslator translator;
	private TileInfo tileInfo;
	private ResourceInfo resourceInfo;
	
	public UnitPanel(TileInfo tileInfo) {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		Back back = applicationFactory.getBack();
		translator = applicationFactory.getInterfaceTranslator();
		resourceInfo = applicationFactory.getResourceInfo();
		this.tileInfo = tileInfo;
		build();
	}

	/**
	 * 
	 */
	private void build() {
//		List<String> buildings = translator.getPossibleActions();
//		resourceInfo.getUnitActions(tileInfo.getSelection().getSelected().getType());
		List<String> buildings = tileInfo.getPossibleActions();
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
		GameButton button = (GameButton) e.getSource();
		
		//put the event in a queue and process later
			Logic.addToEventQueue(button);
			if (button.getRoseAction() != null) {
				Logic.addToEventQueue(button.getRoseAction());
			}
	}

	public void setTileInfo(TileInfo tileInfo) {
		this.tileInfo = tileInfo;
	}

}
