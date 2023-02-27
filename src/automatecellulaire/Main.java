package automatecellulaire;

public class Main {

	public static void main(String[] args) {
		Grid grid = new Grid(3, 3);
		grid.initRandomGrid();
		Rule rule = new Rule("B3-4/S23"); // jeu de la vie 
		Generator generator = new Generator(rule);
		Game game = new Game(grid, generator);
		
		game.play();
	}

}
