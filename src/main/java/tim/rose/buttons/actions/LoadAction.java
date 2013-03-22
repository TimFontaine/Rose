/**
 * 
 */
package tim.rose.buttons.actions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import tim.pathfinding.PathfindingMap;

/**
 * @author tfontaine
 *
 */
public class LoadAction extends RoseAction {

	/**
	 * 
	 */
	public LoadAction() {
	}

	/* (non-Javadoc)
	 * @see tim.rose.buttons.actions.RoseAction#doAction()
	 */
	@Override
	public void doAction() {
		try {
			FileInputStream fileInputStream = new FileInputStream("rose.ser");
			ObjectInputStream ois = new ObjectInputStream(fileInputStream);
			PathfindingMap map = (PathfindingMap) ois.readObject();
			ois.close();
			back.setMap(map);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
