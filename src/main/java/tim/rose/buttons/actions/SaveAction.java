/**
 * 
 */
package tim.rose.buttons.actions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import tim.game.Map;

/**
 * @author tfontaine
 *
 */
public class SaveAction extends RoseAction {

	/**
	 * 
	 */
	public SaveAction() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see tim.rose.buttons.actions.RoseAction#doAction()
	 */
	@Override
	public void doAction() {
		Map map = back.getMap();
		try {
			FileOutputStream fos = new FileOutputStream("rose.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(map);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
