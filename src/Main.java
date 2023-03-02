import app.Game;
import app.Generator;
import model.Grid;
import model.rule.Rule;

public class Main {

	public static void main(String[] args) {
		Grid grid = new Grid(3, 3);
		grid.initRandomGrid();
		Rule rule = new Rule("B1-3/S1-3"); // jeu de la vie 
		Generator generator = new Generator(rule);
		Game game = new Game(grid, generator);
		
		game.play();
	}

}
