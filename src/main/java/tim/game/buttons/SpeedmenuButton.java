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
public class SpeedmenuButton extends GameButton  {

	Mediator mediator;
	
	/**
	 * 
	 */
	public SpeedmenuButton(ActionListener listener) {
		super(listener);
		this.mediator = GameApplicationFactory.getInstance().getMediator();
	}

	@Override
	public void doAction() {
		mediator.openSpeedMenu();
	}

}
