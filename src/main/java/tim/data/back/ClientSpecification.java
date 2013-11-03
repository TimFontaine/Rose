/**
 * 
 */
package tim.data.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tim.namespacetest.client.GameActionProp;

/**
 * @author tim
 *
 */
public class ClientSpecification {
	
	private List<String> defaultUnitActions ;
	private List<GameActionProp> gameActionProps;//single
	
	private Map<String, List<GameActionProp>> gameActionAbilityProps;//group

	/**
	 * 
	 */
	public ClientSpecification() {
		setDefaultUnitActions(new ArrayList<String>());
		setGameActionProps(new ArrayList<GameActionProp>());
	}

	/**
	 * @return the defaultUnitActions
	 */
	public List<String> getDefaultUnitActions() {
		return defaultUnitActions;
	}

	/**
	 * @param defaultUnitActions the defaultUnitActions to set
	 */
	public void setDefaultUnitActions(List<String> defaultUnitActions) {
		this.defaultUnitActions = defaultUnitActions;
	}

	/**
	 * @return the gameActionProps
	 */
	public List<GameActionProp> getGameActionProps() {
		return gameActionProps;
	}

	/**
	 * @param gameActionProps the gameActionProps to set
	 */
	public void setGameActionProps(List<GameActionProp> gameActionProps) {
		this.gameActionProps = gameActionProps;
	}
	
	public String findActionName(String itemName) {
		for (GameActionProp prop :gameActionProps ) {
			if (itemName.equals(prop.getName())) {
				return prop.getActionObject();
			}
		}
		System.out.println("action not found");
		return null;
	}
	
	public GameActionProp findGameActionProp(String itemName) {
		for (GameActionProp prop :gameActionProps ) {
			if (itemName.equals(prop.getName())) {
				return prop;
			}
		}
		System.out.println("prop not found");
		return null;
	}

	/**
	 * @return the gameActionAbilityProps
	 */
	public Map<String, List<GameActionProp>> getGameActionAbilityProps() {
		return gameActionAbilityProps;
	}

	/**
	 * @param gameActionAbilityProps the gameActionAbilityProps to set
	 */
	public void setGameActionAbilityProps(Map<String, List<GameActionProp>> gameActionAbilityProps) {
		this.gameActionAbilityProps = gameActionAbilityProps;
	}

}
