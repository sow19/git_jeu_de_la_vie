package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import automatecellulaire.Cellule;
import automatecellulaire.Position;

class CelluleTest {
	private Cellule tester;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.print("Testing automatecellulaire.Cellule start");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.print("Testing automatecellulaire.Cellule end");
	}

	@BeforeEach
	void setUp() throws Exception {
		Position pos = new Position(1,1);
		tester = new Cellule(pos, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void ensureCreationOfCelluleWorks() {
		// todo: ask prof if testing position is useful here
		assertEquals(1, tester.getEtat(), "Cellule etat should be 1");
	}

}
