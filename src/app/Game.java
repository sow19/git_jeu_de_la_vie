package app;

import model.Grid;
import model.hashlife.Hashlife;
import util.AbstractListenableModel;

/**
 * The class which play the game
 */
public class Game extends AbstractListenableModel {
    private Grid grid;
    private Generator generator;
    
    /**
     * Build a new instance
     * @param grid
     * @param generator
     */
	public Game(Grid grid, Generator generator) {
		super();
		this.grid = grid;
		this.generator = generator;
	}
    
    // Getters and setters
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	public Generator getGenerator() {
		return generator;
	}
	public void setGenerator(Generator generator) {
		this.generator = generator;
	}
	
	// Methods
	/**
	 * The method playing the game
	 */
	public void play() {
		// We print the grid in his initial state
		System.out.println("ancien===================================================");
		System.out.println(this.grid.toString());
		
		// We generate next generations until all cells of the grid are dead
		while(!this.grid.isAllDead()) {
			this.grid = this.generator.nextGeneration(this.grid);
			this.fireChangement(null);
			System.out.println("nouveau");
			System.out.println(this.grid.toString());
			break; //
		}
	}

	// public void playHashlife() {
	// 	// We print the grid in his initial state
	// 	System.out.println("ancien");
	// 	System.out.println(this.grid.toString());

	// 	Hashlife hashlife = new Hashlife(generator);


	// 	// We generate next generations until all cells of the grid are dead
	// 	while(!this.grid.isAllDead()) {
	// 		this.grid = hashlife.jumpGenerations(grid,1);
	// 		System.out.println("nouveau");
	// 		System.out.println(this.grid.toString());
	// 		break; //
	// 	}
	// }
}