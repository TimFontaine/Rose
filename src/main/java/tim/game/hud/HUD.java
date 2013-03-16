/**
 * 
 */
package tim.game.hud;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

/**
 * @author tim
 *
 */
public class HUD extends JPanel {
	
	public HUD() {
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
		setLayout(flowLayout);
		setBackground(Color.white);
	}

}
