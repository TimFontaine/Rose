package tim.core;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/**
 * 
 */

/**
 * @author tfontaine
 *
 */
public abstract class Core extends JFrame {

	private static final long lag = 30;


//	protected JFrame frame;
	

	protected boolean play;
	GraphicsDevice gd;
	
	/**
	 * 
	 */
	public Core(GraphicsDevice gd) {
		super(gd.getDefaultConfiguration());
		this.setIgnoreRepaint(true);
	    setUndecorated(true);
	    setResizable(false);
		gd.setFullScreenWindow(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.createBufferStrategy(2);
		this.createBufferStrategy(2);
		setVisible(true);
		
	}
	
	protected void run() {
		while(play) {
			
			BufferStrategy strategy = this.getBufferStrategy();
			Graphics2D g= (Graphics2D) strategy.getDrawGraphics();
//			Graphics2D g = (Graphics2D) gd.getFullScreenWindow().getGraphics();
			logic();
			if (!strategy.contentsLost()) {
				draw(g);
				strategy.show();
				g.dispose();
			}
			try {
				Thread.sleep(lag);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public abstract void draw(Graphics2D g);

	public abstract void logic();
	
}
