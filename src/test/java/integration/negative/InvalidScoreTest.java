package integration.negative;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import exception.BowlingGameException;
import interfaces.FileParser;
import models.Play;
import services.BowlingPlayValidator;
import services.TxtFileParser;

public class InvalidScoreTest {

	@Test
	public void invalidScoreExceptionThrowed() throws IOException {
		FileParser<List<Play>> parser = new TxtFileParser(new BowlingPlayValidator());
		Boolean actual = false;
		
		try {
			parser.parse("src\\test\\resources\\negative\\invalid-score.txt");
		} catch(BowlingGameException e) {
			actual = true;
		}
		
		assertTrue(actual);
	}
}
