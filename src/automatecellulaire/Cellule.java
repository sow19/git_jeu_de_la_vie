package automatecellulaire;

public class Cellule {
	private Position position;
	private boolean etat;

	public Cellule(Position position, boolean etat) {
		this.position = position;
		this.etat = etat;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean getEtat() {
		return this.etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}
}
