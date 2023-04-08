package views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import model.Position;

import model.Grid;
import constants.Rules;
import app.Generator;
import app.Game;
import model.rule.Rule;
public class Demo {

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		Grid grid = new Grid(24, 24);

		// grid.initRandomGrid();
		grid.initPattern("patterns/Burst.txt");
		// Position[] tab = new Position[3];
		// tab[0] = new Position(0,1);
		// tab[1] = new Position(0,2);
		// tab[2] = new Position(1,1);
		// grid.initGridUser(tab);

		Generator generator = new Generator();
		Game game = new Game(grid, generator);
		new MainWindow(game);
//		new MainWindow();
	}

}
