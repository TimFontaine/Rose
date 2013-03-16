/**
 * 
 */
package tim.game.hud;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import tim.core.ResourceManager;
import tim.game.Back;
import tim.game.Logic;
import tim.game.buttons.GameButton;
import tim.game.factory.GameApplicationFactory;
import tim.game.usercentric.InterfaceTranslator.Selection;



/**
 * @author tfontaine
 *
 */
public class Interface implements ActionListener {
	
	ResourceManager resourceManager;
	
	JFrame frame;
	Back back;
	Mediator mediator;
	
	public Interface(JFrame frame) {
		this.frame = frame;
		GameApplicationFactory applicationFactory = GameApplicationFactory.getInstance();
		back = applicationFactory.getBack();
		resourceManager = ResourceManager.getInstance();
		this.mediator = applicationFactory.getMediator();
		mediator.registerJFrame(frame);
	}
	
	public void buildInterface() {
		FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
		frame.getContentPane().setLayout(null);
		GameViewPanel panel = new GameViewPanel();
		mediator.registerGameViewPanel(panel);
//		panel.setLayout(flowLayout);
//		panel.setBackground(Color.white);
		panel.setSize(frame.getWidth(), 100);
		panel.setLocation(0, frame.getHeight() - 100);
		frame.getContentPane().add(panel);
		JPanel travel = new TravelSpeedMenu(mediator);
		mediator.switchItemPanel(Selection.NONE);
		
		travel.setBackground(Color.white);
//		travel.setSize(300, 300);
		travel.setLocation(300, 300);

	}
	
	public void buildSystemMenu() {
		JPanel systemPanel = new SystemPanel(mediator);
		systemPanel.setLocation(frame.getWidth() /2, frame.getHeight()/2);
//		systemPanel.setSize(300, 300);
		frame.add(systemPanel);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		GameButton button = (GameButton) e.getSource();
		
		//put the event in a queue and process later
//		queue = new ArrayList<MouseEvent>();
		System.out.println("button pressed");
//		synchronized (queue) {
			Logic.addToEventQueue(button);
//		}
		
		
		
		
	}
}
