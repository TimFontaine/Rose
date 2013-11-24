/**
 * 
 */
package tim.com.client.controller;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import org.omg.CORBA.FREE_MEM;

import tim.com.client.RoseClient;
import tim.com.client.shared.Unit;
import tim.com.client.view.Canvas;
import tim.com.client.view.CanvasMouseListener;
import tim.com.client.view.FullScreenFrame;
import tim.com.client.view.MapViewer;
import tim.com.client.view.gui.MapControls;

/**
 * @author tfontaine
 * Root for the GUI, contains graphics, listeners and animations
 *
 */
public class GUI {
	
	GraphicsDevice gd;
	
	RoseClient roseClient;
	
	MapViewer mapViewer;
	
	Canvas canvas;
	
	MapControls mapControls;

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
		canvas = new Canvas(roseClient, this, mapViewer, size);
		FullScreenFrame f = new FullScreenFrame(gd);
		f.setCanvas(canvas);
		f.setVisible(true);
		
		canvas.createKeyBindings();
		
		showMapControls();
	}
	
	public void showMapControls() {
		if (mapControls == null) {
			mapControls = new MapControls(roseClient, this);
			mapControls.addToComponent(canvas);
		}
		mapControls.update();
	}
	
	public void updateMapControls() {
		 if (mapControls != null) {
	            mapControls.update();
		 }
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
	
	public void refresh() {
		canvas.refresh();
	}

	/**
	 * 
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * @param mouseListener
	 */
	public void close(JPanel panel) {
		canvas.close(panel);
	}
	
	

}
