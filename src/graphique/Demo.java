package graphique;

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
public class Demo {

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
//		new MainWindow(new Game(new Grid(12, 12), new Generator(new Rule(Rules.GAMEOFLIFE))));
		new MainWindow();
	}

}
