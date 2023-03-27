package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import model.*;

/**
 * La classe GridTest contient les tests unitaires pour la classe Grid.
 * @author sow224
 *
 */

public class TestGrid {
	
	@Test
	public void testConstructeur() {
		
		//creer une grille avec une dimension
	        Grid grid = new Grid(5,5);
	        //verifier que la grille a bien été créé avec la dimension entrée en paramètre
	        assertNotNull(grid);
	        assertEquals(5, grid.getBoard().length);
	        assertEquals(5,grid.getBoard()[0].length);
	        for (int i = 0; i < grid.getRows(); i++) {
	            for (int j = 0; j < grid.getCols(); j++) {
	                assertNotNull(grid.getBoard()[i][j]);
	            }
	        }
		
	}
	
	@Test
	public void testInitGridUser() {
		//création de la grille
		Grid grid = new Grid(3,3);
		//initialisations d'un tableau de position
		Position[] tabPositions = {new Position(0,0),new Position(0,1),new Position(1,1),new Position(2,1)};
		// appeler la fonction avec le tableau de positions
		grid.initGridUser(tabPositions);
		//verifier que l'etat des positions est à 1
		assertTrue(grid.getBoard()[0][0].getEtat()==1);
		assertTrue(grid.getBoard()[0][1].getEtat()==1);
		assertTrue(grid.getBoard()[1][1].getEtat()==1);
		assertTrue(grid.getBoard()[2][1].getEtat()==1);
	}
	
	@Test
	public void testInitOneCellGrid() {
		//création de la grille
		Grid grid = new Grid(3,3);
		Position pos = new Position(0,0);
		grid.initOneCellGrid(pos, 1);
		assertEquals(1, grid.getBoard()[0][0].getEtat());
		
	}
	
	@Test
	public void testGetAliveCell() {
		Grid grid = new Grid(3,3);
		int count1 = grid.getAliveCell();
		assertEquals(0, count1);
		grid.getBoard()[0][0].setEtat(1);
		grid.getBoard()[0][1].setEtat(1);
		grid.getBoard()[2][0].setEtat(1);
		int count2 = grid.getAliveCell();
		assertEquals(3, count2);
		
	}
	
	@Test
	public void testIsAllDead() {
		Grid grid = new Grid(3,3);
		assertTrue(grid.isAllDead());
		grid.getBoard()[0][0].setEtat(1);
		grid.getBoard()[0][1].setEtat(1);
		assertFalse(grid.isAllDead());
		
		
	}
	

}
