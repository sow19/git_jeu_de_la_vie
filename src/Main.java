import static org.junit.Assert.assertEquals;

import app.Game;
import app.Generator;
import model.Cellule;
import model.Grid;
import model.Position;
import model.hashlife.Hashlife;
import model.hashlife.QuadNode;
import model.rule.RuleMulttF;

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
		//Generator generator = new Generator();
		/*Game game = new Game(grid, generator);
		
		game.play();*/
        //Hashlife hashlife = new Hashlife(generator);
		
		  QuadNode rootNode = new QuadNode(new QuadNode(new QuadNode(1), new QuadNode(1), new QuadNode(0), new QuadNode(0)),
		           new QuadNode(new QuadNode(0), new QuadNode(1), new QuadNode(0), new QuadNode(0)),
		           new QuadNode(new QuadNode(0), new QuadNode(0), new QuadNode(1), new QuadNode(0)),
		           new QuadNode(new QuadNode(0), new QuadNode(0), new QuadNode(0), new QuadNode(1)));
		  
		  	Generator generator = new Generator();
		    Hashlife hashlife = new Hashlife(generator);
		    Grid grid2 = hashlife.convertToGrid(rootNode);
		    Cellule[][] board = grid2.getBoard();
		    System.out.println("l'etat doit etre le même que celle du QaudNoeud à la meme position"+ board[0][1].getEtat());
		   
	}
	
	

}