/**
 * 
 */
package tim.com.client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import tim.com.client.RoseClient;
import tim.com.client.actions.RoseAction;
import tim.com.client.controller.GUI;
import tim.com.client.shared.City;
import tim.com.client.shared.Node;
import tim.com.client.view.panels.CityPanel;
import net.miginfocom.swing.MigLayout;

/**
 * @author tfontaine
 *
 */
public class Canvas extends JPanel {
	
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
	
    public Component addToCanvas(JComponent comp) {
//        addAsFrame(comp);
        add(comp);
        return comp;
    }
	
	/* (non-Javadoc)
	 * @see java.awt.Container#paintComponents(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		
		System.out.println("painting");
		Graphics2D g2d = (Graphics2D) g;
		
		mapViewer.display(g2d);
		
		super.paintComponent(g);
		super.paintChildren(g);
	
		
	}
	
	public void createKeyBindings() {
		for (RoseAction action : roseClient.getActionManager().getActions().values()) {
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(action.getAccelerator(), action.getId());
			getActionMap().put(action.getId(), action);
		}
	}
	
	public void showCityPanel(Node tile) {
//		mapViewer.setFocus(tile);
		showCity(tile.getCity());
//		mapViewer.setFocus(tile);
		
	}

	/**
	 * @param city
	 */
	private void showCity(City city) {
		JPanel panel = new CityPanel(roseClient, gui, city);
//		repaint();
		addAsFrame(panel);
//		panel.requestFocus();
//		JPanel panel = new JPanel();
//		panel.add(new JButton("hello"));
//		panel.setLayout(new MigLayout());
//		panel.setSize(200, 200);
//		add(panel);
		
	}
	
	private void addAsFrame(JComponent comp) {
		JInternalFrame iFrame = new JInternalFrame();
		iFrame.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		comp.setVisible(true);
		
		if (iFrame.getContentPane() instanceof JComponent) {
		}
		iFrame.getContentPane().add(comp);
		
		
		
		iFrame.setVisible(true);
		
		iFrame.setSize(comp.getSize());
		
		iFrame.pack();
		
		iFrame.setLocation(100,100);
		comp.setBounds(100, 100, 100, 100);
		iFrame.setBounds(100, 100, 100, 100);
		
//		add(iFrame, JLayeredPane.MODAL_LAYER);
		add(comp);
		
		repaint();
	}
	
	public void refresh() {
		repaint(0, 0, getWidth(), getHeight());
		System.out.println("repaint");
	}

	/**
	 * @param panel
	 */
	public void close(JPanel panel) {
		System.out.println("removing panel");
		JInternalFrame frame = getInteralFrame(panel);
		frame.dispose();
		repaint();
		requestFocusInWindow();
	}
	
	private JInternalFrame getInteralFrame(Component comp) {
		Component parent = comp.getParent();
		while(! (parent instanceof JInternalFrame)) {
			parent = parent.getParent();
		}
		return (JInternalFrame) parent;
	}

}
