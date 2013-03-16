/**0
 * 
 */
package tim.game;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import tim.core.GameAction;
import tim.core.ScreenUtils;
import tim.data.back.Direction;
import tim.data.back.Event;
import tim.data.back.Node;
import tim.data.back.Thing;
import tim.data.front.MouseInfoItem;
import tim.data.front.MouseState;
import tim.data.front.ScreenInfo;
import tim.game.ai.job.GotoJob;
import tim.game.ai.job.Job;
import tim.game.buttons.GameButton;
import tim.game.factory.GameApplicationFactory;
import tim.game.hud.Interface;
import tim.game.hud.Mediator;
import tim.game.usercentric.ComplexOrder;
import tim.game.usercentric.InterfaceTranslator;
import tim.game.usercentric.InterfaceTranslator.Selection;
import tim.rose.buttons.actions.RoseAction;

/**
 * @author tfontaine
 *
 */
public class Logic {

	private HashMap<String, GameAction> actionMap;
	private Back back;
	ScreenInfo screenInfo;
	Mediator mediator;
	
	private InterfaceTranslator translator;//the player coupled to the interface
	private static List<GameButton> queue;
	private static List<RoseAction> actionQueue;
	
	/**
	 * @param mediator 
	 * 
	 */
	public Logic(InterfaceTranslator translator, HashMap<String, GameAction> actionMap) {
		this.actionMap = actionMap;
		this.back = GameApplicationFactory.getInstance().getBack();
		this.translator = translator;
		this.screenInfo = ScreenInfo.getInstance();
		this.mediator = GameApplicationFactory.getInstance().getMediator();
		queue = new ArrayList<GameButton>();
		actionQueue = new ArrayList<RoseAction>();
	}
	
	public void doLogic() {
		handleEvents();
		doMouseLogic();
		
		if (actionMap.get("down").getAmount() > 0) {
			translator.move(Direction.DOWN, 1);
			System.out.println("event is down");
		}
		if (actionMap.get("up").getAmount() > 0) {
			translator.move(Direction.UP, 1);
			System.out.println("event is up");
		}
		if (actionMap.get("left").getAmount() > 0) {
			translator.move(Direction.LEFT, 1);
			System.out.println("event is left");
		}
		if (actionMap.get("right").getAmount() > 0) {
			translator.move(Direction.RIGHT, 1);
			System.out.println("event is right");
		}
		if (actionMap.get("nextStep").getAmount() > 0) {
			back.nextStep();
		}
//		if (actionMap.get("space").getAmount() > 0) {
////			back.putThing();
//			System.out.println("event is space");
//		}
		
		List<GameButton> l = queue;
			for (GameButton button: l) {
				button.doAction();
			}
			l.clear();
			
//		}
		
			for (RoseAction action: actionQueue) {
				action.doAction();
			}
			actionQueue.clear();
	}

	private void handleEvents() {
		List<Event> events = back.getEvents();
		for (Event event: events) {
			
		}
		
	}

	private void doMouseLogic() {
		if (actionMap.get("mouseLeft").isPressed()) {
			Point destination = translateCoordsFromScreen();
			Selection selection = translator.selectScreenItem(destination);
			mediator.switchItemPanel(selection);
		} else if (actionMap.get("mouseRight").isPressed()) {
			Point destination = translateCoordsFromScreen();
			translator.gotoLocation(destination);
		}
	}
	
	private Point translateCoordsFromScreen() {
		Frame frame = JFrame.getFrames()[0];
		Point mouse = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(mouse, frame);
		int tileOnScreenX = mouse.x / ScreenInfo.getTileSize();
		int tileOnScreenY = mouse.y / ScreenInfo.getTileSize();
		Point coords = new Point(tileOnScreenX, tileOnScreenY);
		return coords;
	}
	
	
	public static void addToEventQueue(GameButton button) {
		queue.add(button);
	}
	
	public static void addToEventQueue(RoseAction action) {
		actionQueue.add(action);
	}
	
}
