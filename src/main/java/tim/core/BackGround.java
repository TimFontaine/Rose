/**
 * 
 */
package tim.core;

import java.awt.Graphics2D;

/**
 * @author tfontaine
 *
 */
public class BackGround {

	
	private int screenWidth;
	private int screenHeight;
	
	/**
	 * 
	 */
	public BackGround(int width, int height) {
		this.screenWidth = width;
		this.screenHeight = height;
	}
	
	public void draw(Graphics2D g) {
		int tilesOnScreenX = (screenWidth / 50) + 1;
		int tilesOnScreeny = (screenHeight/ 50) + 1;

		
		
		for (int i= 0; i<tilesOnScreenX; i++) {
			for (int j=0; j<tilesOnScreeny; j++) {
				g.drawRect(i * 50, j * 50, 50, 50);
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
