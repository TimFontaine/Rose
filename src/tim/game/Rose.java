package tim.game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tim.core.BackGround;
import tim.core.Core;
import tim.core.ForeGround;
import tim.core.GameAction;
import tim.core.InputManager;
import tim.core.NullRepaintManager;
import tim.core.ResourceManager;
import tim.data.front.MouseInfoItem;
import tim.data.front.MouseState;
import tim.data.front.ScreenInfo;
import tim.game.hud.Interface;
import tim.game.hud.Mediator;


/**
 * 
 */

/**
 * @author tfontaine
 *
 */
public class Rose extends Core {
	
	BackGround backGround;
	ForeGround foreGround;
	InputManager inputManager = new InputManager(this);
	Logic logic;
	Mediator mediator;
	
	HashMap<String, GameAction> actionMap;
	GameAction left;
	GameAction right;
	GameAction up;
	GameAction down;
	GameAction escape = new GameAction();
	GameAction nextStep = new GameAction();
	Interface gui;
	
	int human = 1;

	/**
	 * 
	 */
	public Rose(GraphicsDevice gd) {
		super(gd);
		backGround = new BackGround(getWidth(), getWidth());
		foreGround = new ForeGround(getWidth(), getHeight());
		actionMap = new HashMap<String, GameAction>();
		ResourceManager resourceManager = ResourceManager.getInstance();
		mediator = new Mediator();
		drawInterFace(null, this);
		logic = new Logic(this, actionMap, mediator);
		play = true;
		
		initGame();
		NullRepaintManager.install();
		
		Container contentPane = this.getContentPane();

        // make sure the content pane is transparent
        if (contentPane instanceof JComponent) {
            ((JComponent)contentPane).setOpaque(false);
        }
       
		 this.validate();

		run();
	}

	private void initGame() {
		setupGameActions();
		MapBuilder m = new MapBuilder();
		MouseInfoItem.mouseState = MouseState.FREE;
		
	}

	private void setupGameActions() {
		left = new GameAction();
		right = new GameAction();
		up = new GameAction();
		down = new GameAction();
		GameAction space = new GameAction();
		GameAction nextTurn = new GameAction();
		GameAction mouseClick = new GameAction();
		escape = new GameAction();
		inputManager.mapToKey(left, KeyEvent.VK_LEFT);
		inputManager.mapToKey(right, KeyEvent.VK_RIGHT);
		inputManager.mapToKey(up, KeyEvent.VK_UP);
		inputManager.mapToKey(down, KeyEvent.VK_DOWN);
//		inputManager.mapToKey(space, KeyEvent.VK_SPACE);
		inputManager.mapToKey(nextTurn, KeyEvent.VK_SPACE);
		inputManager.mapToMouse(mouseClick, InputManager.MOUSE_BUTTON_1);
		inputManager.mapToKey(escape, KeyEvent.VK_ESCAPE);
		inputManager.mapToKey(nextStep, KeyEvent.VK_CONTROL);
		
		actionMap.put("left", left);
		actionMap.put("right", right);
		actionMap.put("up", up);
		actionMap.put("down", down);
//		actionMap.put("space", space);
		actionMap.put("nextTurn", nextTurn);
		actionMap.put("mouseLeft", mouseClick);
		actionMap.put("nextStep", nextStep);
		
		
	}


	@Override
	public void draw(Graphics2D g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		drawBackGround(g);
		drawForeGround(g);
		mediator.handleEvents();
		this.getLayeredPane().paintComponents(g);
	}

	private void drawInterFace(Graphics2D g, JFrame window) {
		gui = new Interface(this, mediator);
		gui.buildInterface();
		
	}


	private void drawForeGround(Graphics2D g) {
		foreGround.draw(g);
	}


	private void drawBackGround(Graphics2D g) {
		backGround.draw(g);
		
	}

	@Override
	public void logic() {
		checkSystemInput();
//		if (human != 1) {
//			otherPlayer.doLogic();
//			human=1;
//		} else {
//			logic.doLogic();
//		}
		logic.doLogic();
	}
	

	
	private void checkSystemInput() {
		if (escape.isPressed()) {
			gui.buildSystemMenu();
		}
		if (actionMap.get("nextTurn").isPressed() && human == 1) {
			Back.getInstance().nextPlayer();
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		new Rose(ge.getDefaultScreenDevice());

	}

}
