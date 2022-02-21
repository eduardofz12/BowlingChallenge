package unit.models;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import models.Bowler;
import models.Pinfalls;

public class BowlerTest {

	@Test
	void testAddShot() throws Exception {
		// arrange
		Bowler bowler = new Bowler();
		Pinfalls shot = new Pinfalls("10");
		
		bowler.addShot(shot);
		
		// act
		boolean actual = bowler.getShots().size() == 1;

		// assert
		assertTrue(actual);
	}
	
	@Test
	void testGetShotsSize() throws Exception {
		// arrange
		Bowler bowler = new Bowler();
		
		// act
		boolean actual = bowler.getShotsSize() == 0;

		// assert
		assertTrue(actual);
	}
	
}
