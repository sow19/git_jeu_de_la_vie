package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import automatecellulaire.Position;

class PositionTest{
	private Position tester;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.print("Testing automatecellulaire.Position start");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.print("Testing automatecellulaire.Position end");
	}

	@BeforeEach
	void setUp() throws Exception {
		tester = new Position(1,1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void ensureCreationOfPositionWorks() {
		assertEquals(1, tester.getRow(), "Position row should be 1");
		assertEquals(1, tester.getCol(), "Position col should be 1");
	}

}
