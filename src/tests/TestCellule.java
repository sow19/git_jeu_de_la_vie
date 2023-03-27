package tests;


/**
 * /**
 * La classe TestCellule contient les tests unitaires pour la classe Cellule.
 * @author sow224
 *
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import model.*;


public class TestCellule {
	
	@Test
	public void testConstructeur() {
		Cellule cellule = new Cellule(new Position(0,1),0);
		assertEquals(0, cellule.getPosition().getRow()," nombre de lignes OK");
		assertEquals(1, cellule.getPosition().getCol()," nombre de colonnes OK");
		assertEquals(0, cellule.getEtat(),"l'etat est bien défini");
	}
}

