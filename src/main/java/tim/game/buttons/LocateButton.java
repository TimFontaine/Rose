/**
 * 
 */
package tim.game.buttons;

import java.awt.event.ActionListener;

import tim.game.factory.GameApplicationFactory;
import tim.game.hud.Mediator;

/**
 * @author tfontaine
 *
 */
public class LocateButton extends GameButton {
	
	Mediator mediator;

	/**
	 * 
	 */
	public LocateButton(ActionListener listener) {
		super(listener);
		mediator = GameApplicationFactory.getInstance().getMediator();
	}
	
	@Override
	public void doAction() {
		mediator.openLocatePanel();
	}

}
