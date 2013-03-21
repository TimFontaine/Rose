/**
 * 
 */
package tim.data.back;

import tim.game.Player;


/**
 * @author tfontaine
 *
 */
public class Item extends MapItem {
	
	private Player player;
	private int strength;
	private int attack;
	private int defense;
	

	/**
	 * @param name
	 */
	public Item(String name) {
		super(name);
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	
	
}
