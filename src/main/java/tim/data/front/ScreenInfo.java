/**
 * 
 */
package tim.data.front;


/**
 * @author tim
 *
 */
public class ScreenInfo {
	
	private static ScreenInfo INSTANCE;
	
	private int screenWidth;
	
	private int screenHeigth;
	
	private int tilesOnScreenX;
	
	private int tilesOnScreenY;
	
	private static final int tileSize = 50;
	
	private MouseState mouseState;
	
	public ScreenInfo(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeigth = screenHeight; 
		System.out.println("screenwidth: " + screenWidth);
		System.out.println("screenHeight" + screenHeight);
		tilesOnScreenX = (screenWidth / tileSize) + 1;
		tilesOnScreenY = (screenHeight / tileSize) + 1;
		
		INSTANCE = this;
	}
	
	public static ScreenInfo getInstance() {
		return INSTANCE;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeigth() {
		return screenHeigth;
	}

	public void setScreenHeigth(int screenHeigth) {
		this.screenHeigth = screenHeigth;
	}

	public int getTilesOnScreenX() {
		return tilesOnScreenX;
	}

	public void setTilesOnScreenX(int tilesOnScreenX) {
		this.tilesOnScreenX = tilesOnScreenX;
	}

	public int getTilesOnScreenY() {
		return tilesOnScreenY;
	}

	public void setTilesOnScreenY(int tilesOnScreenY) {
		this.tilesOnScreenY = tilesOnScreenY;
	}

	public static int getTileSize() {
		return tileSize;
	}

	public MouseState getMouseState() {
		return mouseState;
	}

	public void setMouseState(MouseState mouseState) {
		this.mouseState = mouseState;
	}
	
	
	
}
