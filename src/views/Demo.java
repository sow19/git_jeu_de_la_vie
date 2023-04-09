package views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import model.Grid;
import constants.Rules;
import app.Generator;
import app.Game;
import model.rule.Rule;
import model.Position;
public class Demo {

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		Grid grid = new Grid(100, 100);
		grid.initOneCellGrid(new Position(0,0),1);
		grid.initOneCellGrid(new Position(0,1),1);
		grid.initOneCellGrid(new Position(1,1),1);

//		grid.initRandomGrid();
		Generator generator = new Generator();
		Game game = new Game(grid, generator);
		new MainWindow(game);
//		new MainWindow();
	}

}
