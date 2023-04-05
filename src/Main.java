import app.Game;
import app.Generator;
import model.Grid;
import model.Position;
import model.hashlife.Hashlife;

public class Main {

	public static void main(String[] args) {
		Grid grid = new Grid(12, 12);
		grid.initRandomGrid();
		//grid.initPattern("patterns/pattern.txt");
		/*Position[] tab = new Position[3];
		tab[0] = new Position(0,1);
		tab[1] = new Position(0,2);
		tab[2] = new Position(1,1);
		grid.initGridUser(tab);*/
		Generator generator = new Generator();
		Game game = new Game(grid, generator);
		
		// game.play();
		game.playHashlife();
//        Hashlife hashlife = new Hashlife(generator);
//        Grid new_grid = hashlife.jumpGenerations(grid,3);
		System.out.print(grid);
//        System.out.print(new_grid);
	}

}