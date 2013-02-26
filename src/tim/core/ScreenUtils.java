/**
 * 
 */
package tim.core;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

/**
 * @author tfontaine
 *
 */
public class ScreenUtils {
	
	private static ScreenUtils INSTANCE;
	private ResourceManager resourceManager;
	

	/**
	 * 
	 */
	private ScreenUtils() {
		resourceManager = ResourceManager.getInstance();
	}
	
	public static ScreenUtils getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ScreenUtils();
		}
		return INSTANCE;
	}
	
	public void changeMouseCursor(String name) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image im = resourceManager.getImage(name);
		Cursor cursor = toolkit.createCustomCursor(im, new Point(0,0), "my ima");
//		getRootPane().setCursor(cursor);
		JFrame f = (JFrame)JFrame.getFrames()[0];
		f.getRootPane().setCursor(cursor);
	}
	
	public void changeMouseCursorToDefault() {
		JFrame f = (JFrame)JFrame.getFrames()[0];
		f.getRootPane().setCursor(Cursor.getDefaultCursor());
	}
	

}
