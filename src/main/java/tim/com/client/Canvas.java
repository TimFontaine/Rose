/**
 * 
 */
package tim.com.client;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JDesktopPane;

/**
 * @author tfontaine
 *
 */
public class Canvas extends JDesktopPane {
	
	RoseClient roseClient;
	
	MapViewer mapViewer;

	/**
	 * @param mapViewer 
	 * 
	 */
	public Canvas(RoseClient roseClient, MapViewer mapViewer, Dimension size) {
		
		this.roseClient = roseClient;
		this.mapViewer = mapViewer;
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

}
