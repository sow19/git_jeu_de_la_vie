package app;

public class GeneratorThread extends Thread {
    private volatile boolean running = true;
    private Game game;

    public GeneratorThread(Game game) {
        this.game = game;
    }

    public void run() {
    	 // System.out.println("Le thread est en cours d'exécution.");
        // We generate next generations until all cells of the grid are dead
        while (running && !game.getGrid().isAllDead()) {
           // it's absolutely necessary to wait at least 100 milliseconds, otherwise th
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            if(game.isUseHashlife()) {
                game.nextGenerationHashlife();
            } else {
                game.nextGenerationClassic();
            }
        }

        // System.out.println(" Stopped.");
    }

    public void stopThread() {
        running = false;
    }

    // ...
}
