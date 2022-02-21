package unit.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import models.Pinfalls;

public class PinfallsTest {

	@Test
	void testIsStrike() throws Exception {
		// arrange
		Pinfalls strike = new Pinfalls("10");

		// act
		boolean actual = strike.isStrike();

		// assert
		assertTrue(actual);
	}
	
	@Test
	void testIsNotStrike() throws Exception {
		// arrange
		Pinfalls strike = new Pinfalls("2");

		// act
		boolean actual = strike.isStrike();

		// assert
		assertFalse(actual);
	}

	@Test
	void testIsSpare() throws Exception {
		// arrange
		Pinfalls spare = new Pinfalls("2");
		spare.setSecondShot("8");

		// act
		boolean actual = spare.isSpare();

		// assert
		assertTrue(actual);
	}
	
	@Test
	void testIsNotSpare() throws Exception {
		// arrange
		Pinfalls spare = new Pinfalls("2");
		spare.setSecondShot("7");

		// act
		boolean actual = spare.isSpare();

		// assert
		assertFalse(actual);
	}

	@Test
	void testIsDone() throws Exception {
		// arrange
		Pinfalls done = new Pinfalls("10");
		done.setDone(true);

		// act
		boolean actual = done.isDone();

		// assert
		assertTrue(actual);
	}
	
	@Test
	void testIsNotDone() throws Exception {
		// arrange
		Pinfalls notDone = new Pinfalls("2");

		// act
		boolean actual = notDone.isDone();

		// assert
		assertFalse(actual);
	}
	
	@Test
	void testHasThirdShot() throws Exception {
		// arrange
		Pinfalls thirdShot = new Pinfalls("10");
		thirdShot.setSecondShot("3");
		thirdShot.setThirdShot("5");
		
		// act
		boolean actual = thirdShot.hasThirdShot();

		// assert
		assertTrue(actual);
	}
	
	@Test
	void testNotHasThirdShot() throws Exception {
		// arrange
		Pinfalls thirdShot = new Pinfalls("10");
		thirdShot.setSecondShot("3");
		
		// act
		boolean actual = thirdShot.hasThirdShot();

		// assert
		assertFalse(actual);
	}
}
