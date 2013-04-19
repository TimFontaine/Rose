/**
 * 
 */
package tim.com.client;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

/**
 * @author tfontaine
 *
 */
public class Canvas extends JDesktopPane {
	
	RoseClient roseClient;
	
	MapViewer mapViewer;
	
	private GUI gui;

	/**
	 * @param mapViewer 
	 * 
	 */
	public Canvas(RoseClient roseClient, GUI gui, MapViewer mapViewer, Dimension size) {
		
		this.roseClient = roseClient;
		this.mapViewer = mapViewer;
		this.gui = gui;
		setLocation(0, 0);
        setSize(size);

        setDoubleBuffered(true);
        setOpaque(false);
        setLayout(null);
        
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
//        requestFocusInWindow();
        
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#paintComponents(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		System.out.println("painting");
		Graphics2D g2d = (Graphics2D) g;
		mapViewer.display(g2d);
	}
	
	void createKeyBindings() {
		for (RoseAction action : roseClient.getActionManager().getActions().values()) {
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(action.getAccelerator(), action.getId());
			getActionMap().put(action.getId(), action);
		}
	}

	/**
	 * @param city
	 */
	public void showCity(City city) {
		JPanel panel = new CityPanel(roseClient, gui, city);
		JInternalFrame iFrame = new JInternalFrame();
		iFrame.getContentPane().add(panel);
		iFrame.setVisible(true);
		iFrame.setLocation(100, 100);
//		iFrame.setSize(100, 100);
		iFrame.pack();
		add(iFrame);
	}

}
