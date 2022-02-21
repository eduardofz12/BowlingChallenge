package unit.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import util.BowlingUtils;

public class BowlingUtilsTest {

	@Test
	void parseScoreValueNormalPlay() throws Exception {
		String score = "8";
		int expected = 8;

		int actual = BowlingUtils.parseScoreValue(score);

		assertEquals(expected, actual);
	}

	@Test
	void parseScoreValueFoul() throws Exception {
		String score = "F";
		int expected = 0;

		int actual = BowlingUtils.parseScoreValue(score);

		assertEquals(expected, actual);
	}

}
