package levelBuilderBoundary;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * Panel that houses the toggle buttons for tile enable/disable
 * @author John
 * @author Kyle
 * @author Yuheng
 *
 */
@SuppressWarnings("serial")
public class LevelBuilderGamePanel extends JPanel {
	/** the toggle buttons */
	JToggleButton [][] tiles = new JToggleButton[9][9];

	/**
	 * Constructor
	 * Create the panel.
	 */
	public LevelBuilderGamePanel() {
		setBounds(0, 0, 490, 490);
		setLayout(null);
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j <9; j++) {
				JToggleButton button = new JToggleButton();
				button.setBounds(i*55, j*55, 50, 50);
				tiles[i][j] = button;
				add(tiles[i][j]);
			}
		}
	}
	
	/** 
	 * get the toggle button at a certain coordinate
	 * @param x
	 * @param y
	 * @return
	 */
	public JToggleButton getTile(int x,int y ){
		return tiles[x][y];
	}
}
