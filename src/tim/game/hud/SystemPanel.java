/**
 * 
 */
package tim.game.hud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

import tim.game.Logic;
import tim.game.buttons.GameButton;
import tim.rose.buttons.actions.ExitAction;
import tim.rose.buttons.actions.LoadAction;
import tim.rose.buttons.actions.RoseAction;
import tim.rose.buttons.actions.SaveAction;

/**
 * @author tfontaine
 *
 */
public class SystemPanel extends JPanel implements ActionListener{
	
	private Mediator mediator;
	
	public SystemPanel(Mediator mediator) {
		this.mediator = mediator;
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		this.setBackground(Color.WHITE);
		build();
	}

	private void build() {
//		this.setSize(400,400);
		GameButton save = new GameButton(this);
		save.setText("Save");
//		save.setMinimumSize(new Dimension(200, 200));
		save.setMaximumSize(new Dimension(150, 50));
		save.setRoseAction(new SaveAction());
		add(save);
		
		GameButton load = new GameButton(this);
		load.setText("Load");
		load.setMaximumSize(new Dimension(150, 50));
		load.setSize(200, 200);
		load.setRoseAction(new LoadAction());
		add(load);
		
		GameButton exit = new GameButton(this);
//		exit.setPreferredSize(new Dimension(200, 200));
		exit.setMaximumSize(new Dimension(150, 50));
//		exit.setSize(200, 200);
		exit.setText("Exit");
		exit.setRoseAction(new ExitAction());
		add(exit);
		
		this.setSize(200,170);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GameButton button = (GameButton) e.getSource();
		RoseAction action = button.getRoseAction();
		Logic.addToEventQueue(action);
	}
}
