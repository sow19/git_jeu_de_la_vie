package automatecellulaire;

public class Main {

	public static void main(String[] args) {
		Grid grid = new Grid(3, 3);
		grid.initRandomGrid();
		Generator generator = new Generator();
		Game game = new Game(grid, generator);
		
		game.play();
	}

}
