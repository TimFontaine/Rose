/**
 * 
 */
package tim.game.hud;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tim.game.buttons.GameButton;
import tim.game.buttons.SpeedmenuButton;

/**
 * @author tfontaine
 *
 */
public class EventPanel extends JPanel {
	
	JTextArea area;

	public EventPanel(Mediator mediator) {
		mediator.registerEventPanel(this);
		build();
	}

	private void build() {
		this.setLayout(new FlowLayout());
		area = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(area);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setSize(100, 100);
		scrollPane.setPreferredSize(new Dimension(100,100));
		scrollPane.setEnabled(false);
		area.setEditable(false);
//		add(area);
		add(scrollPane);
	}
	
	public void addEvent(String text) {
		area.append(text + "\n");
	}
}
