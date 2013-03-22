/**
 * 
 */
package tim.game.hud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import tim.game.Back;
import tim.game.Logic;
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
	
	public UnitPanel() {
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		Back back = applicationFactory.getBack();
		translator = applicationFactory.getInterfaceTranslator();
		build();
	}

	/**
	 * 
	 */
	private void build() {
		List<String> buildings = translator.getPossibleUnitActions();
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

}
