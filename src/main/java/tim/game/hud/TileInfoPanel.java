/**
 * 
 */
package tim.game.hud;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tim.data.back.Node;
import tim.data.unit.Unit;
import tim.game.factory.GameApplicationFactory;

/**
 * @author tfontaine
 *
 */
public class TileInfoPanel extends JPanel {
	
	Mediator mediator;
	
	JLabel labelX;
	JLabel labelY;
	JLabel coordX;
	JLabel coordY;
	
	JLabel unitType;
	JLabel unitName;
	JLabel unitOil;
	

	public TileInfoPanel() {
		this.mediator = GameApplicationFactory.getInstance().getMediator();
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		this.setBackground(Color.WHITE);
		mediator.registerTileInfoPanel(this);
		build();
	}

	/**
	 * 
	 */
	private void build() {
		labelX = new JLabel("x:");
		labelY = new JLabel("y:");
		
		coordX = new JLabel();
		coordY = new JLabel();
		
		unitType = new JLabel();
		unitOil = new JLabel();
		unitName = new JLabel();
		
		add(coordX);
		add(coordY);
	}

	/**
	 * @param node
	 */
	public void updateInfo(Node node) {
		coordX.setText("" + node.getX());
		coordY.setText("" + node.getY());
		
		if (node.containsUnit()) {
			Unit unit = node.getUnits().get(0);
			unitType.setText(unit.getType());
			unitOil.setText("" + unit.getOil());
			add(unitType);
			add(unitOil);
		} else {
			remove(unitType);
			remove(unitOil);
		}
		
	}

}
