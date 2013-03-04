/**
 * 
 */
package tim.game.hud;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import tim.game.Logic;
import tim.game.buttons.GameButton;
import tim.game.buttons.LocateButton;
import tim.game.buttons.SpeedmenuButton;
import tim.game.factory.GameApplicationFactory;
import tim.rose.buttons.actions.BuildAction;
import tim.rose.buttons.actions.CalcpathAction;
import tim.rose.buttons.actions.LocateAction;

/**
 * @author tfontaine
 *
 */
public class BuildPanel extends JPanel implements ActionListener{
	
	Mediator mediator;

	/**
	 * 
	 */
	public BuildPanel() {
		this.mediator = GameApplicationFactory.getInstance().getMediator();
		build();
	}
	
	private void build() {
		JPanel buildPanel = new JPanel();
		buildBuildPanel(buildPanel);
		
		JPanel optionPanel = new JPanel();
		buildOptionPanel(optionPanel);
		
		JPanel pathPanel = new JPanel();
		buildPathPanel(pathPanel);
		
		add(buildPanel);
		add(pathPanel);
		add(optionPanel);
//		add(eventPanel);
		
		buildEventPanel();
		
		TileInfoPanel tileInfoPanel = new TileInfoPanel();
		add(tileInfoPanel);
	}
	

	private void buildEventPanel() {
		EventPanel eventPanel = new EventPanel();
//		eventPanel.setSize(200, 100);
//		eventPanel.setMaximumSize(new Dimension(200,100));
//		eventPanel.setMinimumSize(new Dimension(200,100));
//		eventPanel.setSize(eventPanel.getPreferredSize());
//		eventPanel.setPreferredSize(new Dimension(200,100));
//		eventPanel.setBackground(Color.GREEN);
		
//		JTextArea area = new JTextArea();
//		area.setSize(100, 100);
//		add(area);
		add(eventPanel);
	}

	private void buildOptionPanel(JPanel optionPanel) {
		GameButton button = new SpeedmenuButton(this); 
		button.setIcon("options");
		optionPanel.add(button);
	}

	private void buildBuildPanel(JPanel panel) {
		GameButton button = new GameButton(this);
		button.setRoseAction(new BuildAction("house"));
		button.setIcon("house");
		panel.add(button);
		
		GameButton button2 = new GameButton(this);
		button2.setRoseAction(new BuildAction("block"));
		button2.setIcon("cross");
		panel.add(button2);
		
		GameButton roadButton = new GameButton(this);
		roadButton.setRoseAction(new BuildAction("road"));
		roadButton.setIcon("road");
		panel.add(roadButton);
		
		GameButton autorouteButton = new GameButton(this);
		autorouteButton.setRoseAction(new BuildAction("double-road"));
		autorouteButton.setIcon("double-road");
		panel.add(autorouteButton);
		
		GameButton oilStation = new GameButton(this);
		oilStation.setRoseAction(new BuildAction("oil"));
		oilStation.setIcon("oil");
		panel.add(oilStation);
		
		GameButton mine = new GameButton(this);
		mine.setRoseAction(new BuildAction("mine"));
		mine.setIcon("mine");
		panel.add(mine);
		
		GameButton factory = new GameButton(this);
		factory.setRoseAction(new BuildAction("factory"));
		factory.setIcon("factory");
		panel.add(factory);
		
		
	}

	
	private void buildPathPanel(JPanel pathPanel) {
		GameButton pathButton = new GameButton(this);
		pathButton.setRoseAction(new CalcpathAction());
		pathButton.setIcon("path");
		pathPanel.add(pathButton);
		
		GameButton locateButton = new LocateButton(this);
		locateButton.setIcon("locate");
//		locateButton.setRoseAction(new LocateAction());
		pathPanel.add(locateButton);
		
	}
	
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
