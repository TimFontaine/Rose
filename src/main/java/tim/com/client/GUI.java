/**
 * 
 */
package tim.com.client;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

import org.omg.CORBA.FREE_MEM;

/**
 * @author tfontaine
 * this class contains tools for the OS gui
 *
 */
public class GUI {
	
	GraphicsDevice gd;
	
	RoseClient roseClient;
	
	MapViewer mapViewer;
	
	Canvas canvas;

	/**
	 * 
	 */
	public GUI(RoseClient roseClient) {
		this.roseClient = roseClient;
	}
	
	public static GraphicsDevice getDefaultScreenDevice() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		return ge.getDefaultScreenDevice();
		
	}
	
	 public static Dimension determineFullScreenSize() {
        GraphicsDevice gd = getDefaultScreenDevice();
        Rectangle bounds = gd.getDefaultConfiguration().getBounds();
        return new Dimension(bounds.width - bounds.x,
                             bounds.height - bounds.y);
	 }
	
	public void startGUI(Dimension size) {
		gd = getDefaultScreenDevice();
		mapViewer = new MapViewer(roseClient, this);
		canvas = new Canvas(roseClient, mapViewer, size);
		FullScreenFrame f = new FullScreenFrame(gd);
		f.setCanvas(canvas);
		f.setVisible(true);
		
		canvas.createKeyBindings();
	}
	
	public void setupMouseListenersForCanvas() {
		canvas.addMouseListener(new CanvasMouseListener(roseClient, canvas, mapViewer));
	}

	/**
	 * @return
	 */
	public Unit getActiveUnit() {
		return mapViewer.getActiveUnit();
	}
	
	public void setActiveUnit(Unit unit) {
		mapViewer.setActiveUnit(unit);
	}

	/**
	 * 
	 */
	public void fullRefresh() {
		canvas.repaint();
	}

	/**
	 * 
	 */
	public Canvas getCanvas() {
		return canvas;
	}
	
	

}
