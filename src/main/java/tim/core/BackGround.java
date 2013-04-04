/**
 * 
 */
package tim.core;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import tim.data.back.Node;
import tim.game.Back;
import tim.game.Map;
import tim.game.ai.data.MutableResource.Resource;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class BackGround {

	
	private int screenWidth;
	private int screenHeight;
	
	private Back back;
	ResourceManager resourceManager;
	
	/**
	 * 
	 */
	public BackGround(int width, int height) {
		this.screenWidth = width;
		this.screenHeight = height;
		resourceManager = ResourceManager.getInstance();
		back = GameApplicationFactory.getInstance().getBack();
	}
	
	public void draw(Graphics2D g) {
		int tilesOnScreenX = (screenWidth / 50) + 1;
		int tilesOnScreeny = (screenHeight/ 50) + 1;

		
		
		for (int i= 0; i<tilesOnScreenX; i++) {
			for (int j=0; j<tilesOnScreeny; j++) {
				g.drawRect(i * 50, j * 50, 50, 50);
				Node node = back.getNode(i, j);
				if (node.containsResource() && node.getResource() != Resource.NONE){
					Image image = resourceManager.getImage(node.getResource().toString());
					g.drawImage(image, i* 50, j*50, null);
				}
			}
		}
	}


	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

}
