/**
 * 
 */
package tim.core;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import tim.data.back.Event;
import tim.data.back.Item;
import tim.data.back.MapItem;
import tim.data.back.Thing;
import tim.data.front.ScreenInfo;
import tim.game.Back;
import tim.game.ImageMapper;

/**
 * @author tfontaine
 *
 */
public class ForeGround {
		
	Back back;
	ResourceManager resourceManager;
	private int screenWidth;
	private int screenHeight;

	/**
	 * 
	 */
	public ForeGround(int width, int height) {
		back = Back.getInstance();
		resourceManager = ResourceManager.getInstance();
		this.screenWidth = width;
		this.screenHeight = height;
	}
	
	public void draw(Graphics2D g) {
		drawMapItems(g);
		int playerX = (int) (back.getPlayer().getX() * 50);
		int playerY = (int) (back.getPlayer().getY() * 50);
		
//		int otherPlayerX = (int) (back.getOtherPlayer().getX() * 50);
//		int otherPlayerY = (int) (back.getOtherPlayer().getY() * 50);
//		
		ImageIcon icon = new ImageIcon(resourceManager.getImage("dot"));
		ImageIcon greenIcon = new ImageIcon(resourceManager.getImage("dot-green"));
		g.drawImage(icon.getImage(),playerX,playerY, null);
//		g.drawImage(greenIcon.getImage(),otherPlayerX,otherPlayerY, null);
		
		drawEvents(g);
		
	}

	private void drawEvents(Graphics2D g) {
		List<Event> events = back.getEvents();
//		int x = screenWidth /2;
//		int y= screenHeight/2;
//		for (Event event : events ) {
//			g.drawString(event.getDescription(), x, y);
//		}
		
	}

	private void drawMapItems(Graphics2D g) {
		
		List<MapItem> mapItems= back.getMapItems();
		for (MapItem mapItem: mapItems) {
			String name = mapItem.getImageName();
			if (name == null) {
				System.out.println("alert: " + mapItem.getName());
			}
			if (mapItem.getType() == null) {
				System.out.println("name without type is: " + mapItem.getName());
			}
			if (mapItem.getType().equals("road")) {
				System.out.println("map item is road");
			}
			Image image = resourceManager.getImage(name);
			ImageIcon thingImageIcon = new ImageIcon(image);
			int x = mapItem.getX() * 50;
			int y = mapItem.getY() * 50;
			g.drawImage(thingImageIcon.getImage(), x, y, null);
			
		}
	}
	

}
