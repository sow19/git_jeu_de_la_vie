package automatecellulaire;

/**
 * The class which play the game
 */
public class Game{
    private Grid grid;
    private Generator generator;
    
    /**
     * Build a new instance
     * @param grid
     * @param generator
     */
	public Game(Grid grid, Generator generator) {
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
		// todo: do it
		
		// We generate next generations until all cells of the grid are dead
		while(!this.grid.isAllDead()) {
			this.generator.nextGeneration(this.grid);
			// print the new grid here
		}
	}
}