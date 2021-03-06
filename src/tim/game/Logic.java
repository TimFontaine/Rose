/**0
 * 
 */
package tim.game;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import tim.core.GameAction;
import tim.core.ScreenUtils;
import tim.data.back.Direction;
import tim.data.back.Event;
import tim.data.back.Node;
import tim.data.back.Thing;
import tim.data.front.MouseInfoItem;
import tim.data.front.MouseState;
import tim.data.front.ScreenInfo;
import tim.game.buttons.GameButton;
import tim.game.factory.GameApplicationFactory;
import tim.game.hud.Interface;
import tim.game.hud.Mediator;
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
	
	JFrame frame;
	private static List<GameButton> queue;
	private static List<RoseAction> actionQueue;
	
	/**
	 * @param mediator 
	 * 
	 */
	public Logic(JFrame frame, HashMap<String, GameAction> actionMap) {
		this.actionMap = actionMap;
		this.back = GameApplicationFactory.getInstance().getBack();
		this.screenInfo = ScreenInfo.getInstance();
		this.frame = frame;
		this.mediator = GameApplicationFactory.getInstance().getMediator();
		queue = new ArrayList<GameButton>();
		actionQueue = new ArrayList<RoseAction>();
	}
	
	public void doLogic() {
		handleEvents();
		doMouseLogic();
		
		if (actionMap.get("down").getAmount() > 0) {
			back.move(Direction.DOWN, 1);
			System.out.println("event is down");
		}
		if (actionMap.get("up").getAmount() > 0) {
			back.move(Direction.UP, 1);
			System.out.println("event is up");
		}
		if (actionMap.get("left").getAmount() > 0) {
			back.move(Direction.LEFT, 1);
			System.out.println("event is left");
		}
		if (actionMap.get("right").getAmount() > 0) {
			back.move(Direction.RIGHT, 1);
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
			//if an item is on the stack, place it on the tile
			if (MouseInfoItem.mouseState == MouseState.SELECTED) {
				int x = MouseInfo.getPointerInfo().getLocation().x;
				int y = MouseInfo.getPointerInfo().getLocation().y;
				
				int tileOnScreenX = x / ScreenInfo.getTileSize();
				int tileOnScreenY = y / ScreenInfo.getTileSize();
				
				back.buildOnTile(tileOnScreenX , tileOnScreenY, MouseInfoItem.item );
				
				MouseInfoItem.mouseState = MouseState.FREE;
				ScreenUtils.getInstance().changeMouseCursorToDefault();
			//retreive the info from the tile
			} else if (MouseState.FREE == MouseInfoItem.mouseState) {
				int x = MouseInfo.getPointerInfo().getLocation().x;
				int y = MouseInfo.getPointerInfo().getLocation().y;
				
				int tileOnScreenX = x / ScreenInfo.getTileSize();
				int tileOnScreenY = y / ScreenInfo.getTileSize();
				
				Node node = back.getNode(tileOnScreenX, tileOnScreenY);
				mediator.updateTileInfo(node);
			}
		}
	}
	
	
	public static void addToEventQueue(GameButton button) {
		queue.add(button);
	}
	
	public static void addToEventQueue(RoseAction action) {
		actionQueue.add(action);
	}
	
}
