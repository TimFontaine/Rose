/**
 * 
 */
package tim.game.hud;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import tim.data.back.Event;
import tim.data.back.Node;
import tim.game.Back;
import tim.game.buttons.GameButton;
import tim.game.factory.GameApplicationFactory;
import tim.game.usercentric.InterfaceTranslator.Selection;

/**
 * @author tfontaine
 *
 */
public class Mediator {

	GameButton option;
	EventPanel eventPanel;
	TileInfoPanel tileInfoPanel;
	JFrame frame;
	Back back;
	
	private GameViewPanel gameViewPanel;
	
	/**
	 * 
	 */
	public Mediator() {
		back = GameApplicationFactory.getInstance().getBack();
	}
	
	public void openSpeedMenu() {
		TravelSpeedMenu speed = new TravelSpeedMenu(this);
		frame.getContentPane().add(speed);
		speed.setLocation(frame.getWidth() - speed.getWidth(), frame.getHeight() - 100 - speed.getHeight());
	}
	
	public void openLocatePanel() {
		POIPanel poiPanel = new POIPanel();
		frame.getContentPane().add(poiPanel);
		poiPanel.setLocation(frame.getWidth() - poiPanel.getWidth(), frame.getHeight() - 100 - 400 - poiPanel.getHeight());
	}
	
	public void registerOptionButton(GameButton button) {
		option = button;
	}
	
	public void registerEventPanel(EventPanel eventPanel) {
		this.eventPanel = eventPanel;
	}
	
	public void registerJFrame(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * @param tileInfoPanel
	 */
	public void registerTileInfoPanel(TileInfoPanel tileInfoPanel) {
		this.tileInfoPanel = tileInfoPanel;
		
	}

	public void updateSpeed(Map speedMap) {
//		back.updateSpeed(speedMap);
		frame.setFocusable(true);
		frame.requestFocusInWindow();
	}
	
	public void close(Component component) {
		frame.getContentPane().remove(component);
		frame.requestFocusInWindow();
	}
	
	public void switchItemPanel(Selection selection) {
		gameViewPanel.update(selection);
		
	}
	
	public Map<String, Integer> getSpeedSettings() {
		return null;
	}

	public void handleEvents() {
		for (Event event : back.getEvents()) {
				eventPanel.addEvent(event.getSource().getName() +  ":" + event.getDescription());
		}
		back.getEvents().clear();
	}

	public void updateTileInfo(Node node) {
		tileInfoPanel.updateInfo(node);
	}

	public void registerGameViewPanel(GameViewPanel panel) {
		this.gameViewPanel = panel;
	}

	
}
