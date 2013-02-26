/**
 * 
 */
package tim.game.buttons;

import java.awt.event.ActionListener;

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
	public SpeedmenuButton(ActionListener listener, Mediator mediator) {
		super(listener);
		this.mediator = mediator;
	}

	@Override
	public void doAction() {
		mediator.openSpeedMenu();
	}

}
