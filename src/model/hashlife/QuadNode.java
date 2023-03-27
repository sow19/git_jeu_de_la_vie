package model.hashlife;

public class QuadNode {
    private int size;

    private int depth;

    private int population;
    protected Boolean state;
    protected QuadNode southWest;
    protected QuadNode southEast;
    protected QuadNode northEast;
    protected QuadNode northWest;

    public QuadNode(boolean state) {
        this.southWest = null;
        this.southEast = null;
        this.northEast = null;
        this.northWest = null;
        this.state = state;
        this.depth = 0;
        this.size = 1;
        this.population = this.state ? 1:0;
    }

    public QuadNode(QuadNode southWest, QuadNode southEast, QuadNode northEast, QuadNode northWest) {
        this.southWest = southWest;
        this.southEast = southEast;
        this.northEast = northEast;
        this.northWest = northWest;

        this.depth = this.southWest.depth + 1;
        this.size = this.southWest.size * 2;
        this.population = this.southWest.population + this.southEast.population + this.northEast.population + this.northWest.population;
    }

    public int getPopulation() {
        return this.population;
    }
    public QuadNode getSW() {return southWest;}
    public QuadNode getSE() {return southEast;}
    public QuadNode getNE() {return northEast;}
    public QuadNode getNW() {return northWest;}
    public int getSize() {
        return this.size;
    }

    public int getDepth() {
        return this.depth;
    }
    public Boolean getState() {
        return state;
    }
    @Override
    public int hashCode() {
        if (this.depth == 0) {
            return this.population;
        }
        return this.northWest.hashCode() +
                79 * this.northEast.hashCode() +
                191 * this.southWest.hashCode() +
                311 * this.southEast.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        QuadNode node = (QuadNode) o;
        if (this.depth != node.depth) {
            return false;
        }
        if (this.depth == 0) {
            return this.state == node.state;
        }
        return this.northWest == node.northWest &&
                this.northEast == node.northEast &&
                this.southWest == node.southWest &&
                this.southEast == node.southEast;
    }
}
