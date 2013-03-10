/**
 * 
 */
package tim.game.hud;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import tim.game.Logic;
import tim.game.buttons.GameButton;
import tim.rose.buttons.actions.LocateAction;
import tim.rose.buttons.actions.RoseAction;

/**
 * @author tfontaine
 *
 */
public class POIPanel extends JPanel implements ActionListener  {

	ButtonGroup buttonGroup;
	
	/**
	 * 
	 */
	public POIPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		build();
	}

	private void build() {
		buttonGroup= new ButtonGroup();
		JRadioButton oil = new JRadioButton("oil");
		JRadioButton factory = new JRadioButton("factory");
		buttonGroup.add(oil);
		buttonGroup.add(factory);
		oil.setActionCommand("oil");
		factory.setActionCommand("factory");
		GameButton search = new GameButton(this);
		search.setText("search");
		LocateAction action = new LocateAction();
		search.setRoseAction(action);
		add(oil);
		add(factory);
		add(search);
		this.setMinimumSize(new Dimension(200, 300));
		this.setSize(getPreferredSize().width, getPreferredSize().height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GameButton button = (GameButton) e.getSource();
		LocateAction locateAction = (LocateAction) button.getRoseAction();
		locateAction.setItemName(buttonGroup.getSelection().getActionCommand());
		Logic.addToEventQueue(button.getRoseAction());
	}

}
