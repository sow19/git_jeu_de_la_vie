package automatecellulaire;

public class Main {

	public static void main(String[] args) {
		Grid grid = new Grid(10, 10);
		//grid.initRandomGrid();
		grid.initPattern("pattern.txt");
		Generator generator = new Generator();
		Game game = new Game(grid, generator);
		
		game.play();
	}

}
