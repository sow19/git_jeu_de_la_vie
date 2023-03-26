package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.Position;

/**
 * 
 * @author sow224
 * La classe TestPosition contient les tests unitaires pour la classe Cellule.
 *
 */

public class TestPosition {
	
	@Test
	public void testConstructeur() {
		Position position = new Position(5,7);
		assertEquals(5, position.getRow()," nombre de lignes OK");
		assertEquals(7, position.getCol()," nombre de colonnes OK");
		
	}

}
