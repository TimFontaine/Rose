/**
 * 
 */
package tim.game.hud;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import tim.game.usercentric.InterfaceTranslator.Selection;

/**
 * @author tim
 *
 */
public class GameViewPanel extends JPanel implements ActionListener {
	
	public GameViewPanel() {
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
		setLayout(flowLayout);
		setBackground(Color.white);
	}
	
	
	public void update(Selection selection) {
		removeAll();
		switch (selection) {
		case UNIT:
			add(new UnitPanel());
			break;
		case NONE:
			add(new PlayerState());
			break;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
