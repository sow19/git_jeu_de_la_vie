package app;

import model.Grid;
import model.hashlife.Hashlife;
import model.rule.Rule;
import util.AbstractListenableModel;

/**
 * The class which play the game
 */
public class Game extends AbstractListenableModel {
    protected Grid grid;
	protected Grid previousGrid;
    protected Generator generator;
	/** If true generate next gen using hashlife else use classic algo */
	protected boolean useHashlife = false;

	/** The thread which generate generation in loop. We're using another class cause
	 * game already extends AbstractListenableModel and connot extends Thread again
	 */
	protected GeneratorThread generatorThread;
	protected Hashlife hashlife;

	private int iteration,nBLiveCell;

	/** Number of milliseconds between each generation */
	protected int genWaitIntervalInMls = 100;

	/** Last generation computation time in milliseconds */
	protected double lastGenComputeTimeInMls = 0;


	/**
     * Build a new instance
     * @param grid
     * @param generator
     */
	public Game(Grid grid, Generator generator) {
		super();
		this.grid = grid;
		this.previousGrid = null;
		this.generator = generator;
		this.hashlife = new Hashlife(generator);
		this.iteration = 0;
		this.nBLiveCell = 0;
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

	public boolean isUseHashlife() {
		return useHashlife;
	}

	public void setUseHashlife(boolean useHashlife) {
		this.useHashlife = useHashlife;
	}

	public double getLastGenComputeTimeInMls() {
		return lastGenComputeTimeInMls;
	}

	public void setLastGenComputeTimeInMls(double timeInMls) {
		this.lastGenComputeTimeInMls = timeInMls;
	}

	

	
	// Methods
	/**
	 * The method playing the game
	 */

	public void runGenThread() {
		this.generatorThread = new GeneratorThread(this);
		this.generatorThread.start();

		// // We generate next generations until all cells of the grid are dead
        // while (!this.getGrid().isAllDead()) {
        //     // System.out.println("Le thread est en cours d'exécution.");
            
        //     if(this.isUseHashlife()) {
        //         this.nextGenerationHashlife();
        //     } else {
        //         this.nextGenerationClassic();
        //     }
        // }
	}

	public void stopGenThread() {
		this.generatorThread.stopThread();
	}

	// public void play() {
	// 	// System.out.println("ancien");
	// 	// System.out.println(this.grid.toString());

	// 	if(useHashlife) {
	// 		this.nextGenerationHashlife();
	// 	} else {
	// 		this.nextGenerationClassic();
	// 	}
	// }

	/** @todo: implements this function */
	public void playConsole() {
		
	}

	public void backupCurrentGrid() {
		this.previousGrid = this.grid;
	}

	public void nextGenerationClassic() {
		this.previousGrid  = this.grid;

		double start = System.currentTimeMillis();
		
		this.grid = this.generator.nextGeneration(this.grid);

		double end = System.currentTimeMillis();
		setLastGenComputeTimeInMls(end - start);
		System.out.println("Généré en " + getLastGenComputeTimeInMls() + " ms");
		
		this.fireChangement(null);
		// System.out.println("nouveau");
		// System.out.println(this.grid.toString());
	}

	public void nextGenerationHashlife() {
		this.previousGrid = this.grid;

		double start = System.currentTimeMillis();

		this.grid = hashlife.jumpGenerations(grid,1);

		double end = System.currentTimeMillis();
		setLastGenComputeTimeInMls(end - start);
		System.out.println("Généré en " + getLastGenComputeTimeInMls() + " ms");

		this.fireChangement(null);
		// System.out.println("nouveau");
		// System.out.println(this.grid.toString());
	}

	// Pour alpha: methodes des events

	/**
	 * Boutton prec: retourne true quand il a pu charger la grille prec
	 * et false sinon. Donc on ne peut pas aller deux fois en arrière max
	 * une fois. quand c'est plus d'une fois ca retourne false
	 * tu peux gérer dans ton affichae
	 * @return
	 */
	public boolean previousGeneration() {
		if(this.previousGrid != null) {
			this.grid = this.previousGrid;
			this.previousGrid = null;
			this.fireChangement(null);
			return true;
		} else {
			this.fireChangement(null);
			return false;
		}

	}

	/**
	 * Boutton accélérer (vitesse max 100 mls)
	 */
	public void increaseSpeed() {
		if(this.genWaitIntervalInMls > 100)
			this.genWaitIntervalInMls -= 100;
		this.fireChangement(null);
	}

	/**
	 * Boutton décélérer (vitesse min 10 sec)
	 */
	public void decreaseSpeed() {
		if(this.genWaitIntervalInMls < 10000)
			this.genWaitIntervalInMls += 100;
		this.fireChangement(null);
	}

	/**
	 * Boutton debut: je pense ca devrait plutôt être reinitialise
	 */
	public void resetGrid() {
		this.grid.reset();
		this.fireChangement(null);
	}

	/**
	 * Boutton classic
	 */
	public void useClassicAlgo() {
		this.useHashlife = false;
	}

	/**
	 * Boutton Hashlife
	 */
	public void useHashlifeAlgo() {
		this.useHashlife = true;
	}


	/**
	 * Select a pattern
	 */
	public void usePattern(String pattern) {
		this.grid.initPattern(pattern);
		this.fireChangement(null);
	}

	/**
	 * Changer de règle
	 */
	public void changeRule(Rule newRule) {
		this.generator.setRule(newRule);
		this.fireChangement(null);
	}
	public int getIteration() {
		return this.iteration;
	}
//	public int getNbLiveCell() {
//		return this.nBLiveCell;
//	}
	public void setIteration() {
		this.iteration= this.generatorThread.getIteration();
		this.fireChangement(null);
	}

	public int getNbLiveCell() {
		 this.nBLiveCell=this.grid.getAliveCell();
		 this.fireChangement(null);
		 return this.nBLiveCell;
	}
}