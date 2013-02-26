/**
 * 
 */
package tim.game.hud;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tim.game.Back;
import tim.game.Logic;
import tim.game.buttons.GameButton;

/**
 * @author tfontaine
 *
 */
public class TravelSpeedMenu extends JPanel implements ActionListener {
	
	Back back;
	
	JTextField textAutoroute;
	JTextField textRoad;
	JTextField textField;
	JButton update;
	JButton close;
	
	Mediator mediator;
	/**
	 * 
	 */
	public TravelSpeedMenu(Mediator mediator) {
		back = Back.getInstance();
		this.mediator = mediator;
		build();
	}

	private void build() {
		JLabel labelField = new JLabel("field:");
		JLabel labelRoad = new JLabel("road:");
		JLabel labelAutoroute = new JLabel("big road");

		textField = new JTextField();
		textRoad = new JTextField();
		textAutoroute = new JTextField();
		
		GridLayout gridLayout = new GridLayout(4, 2);
		this.setLayout(gridLayout);
		
		
		add(labelField);
		add(textField);
		add(labelRoad);
		add(textRoad);
		add(labelAutoroute);
		add(textAutoroute);
		
		update = new JButton("upate");
		close = new JButton("close");
		
		update.addActionListener(this);
		close.addActionListener(this);
		add(update);
		add(close);
		
		
		this.setSize(getPreferredSize().width, getPreferredSize().height);
		
		Map<String, Integer> speedMap = mediator.getSpeedSettings();
		textField.setText(speedMap.get("default").toString());
		textRoad.setText(speedMap.get("road").toString());
		textAutoroute.setText(speedMap.get("double-road").toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		GameButton button = (GameButton) e.getSource();
//		//put the event in a queue and process later
//			Logic.addToEventQueue(button);
		if (e.getSource() == update) {
			Map<String, Integer> speed = new HashMap<String, Integer>();
			speed.put("double-road", Integer.parseInt(textAutoroute.getText()));
			speed.put("road", Integer.parseInt(textRoad.getText()));
			speed.put("default", Integer.parseInt(textField.getText()));
			mediator.updateSpeed(speed);
		} else if (e.getSource() == close) {
			mediator.close(this);
		}
	}
	
	
}
