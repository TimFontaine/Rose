/**
 * 
 */
package tim.com.client;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.HeadlessException;
import java.awt.Rectangle;

import javax.swing.JFrame;


/**
 * @author tfontaine
 *
 */
public class FullScreenFrame extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public FullScreenFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param gc
	 */
	public FullScreenFrame(GraphicsDevice gd) {
		super(gd.getDefaultConfiguration());
		setUndecorated(true);
		
		gd.setFullScreenWindow(this);
		
	}

	public void setCanvas(Canvas canvas) {
		this.getContentPane().add(canvas);
	}

}
