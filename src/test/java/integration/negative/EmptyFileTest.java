package integration.negative;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import exception.BowlingGameException;
import interfaces.FileParser;
import interfaces.GameResultsBuilder;
import models.BowlingGameResults;
import models.Play;
import services.BowlingGameResultsBuilder;
import services.BowlingPlayValidator;
import services.TxtFileParser;

public class EmptyFileTest {

	@Test
	public void emptyFileExceptionThowed() throws IOException {
		FileParser<List<Play>> parser = new TxtFileParser(new BowlingPlayValidator());
		GameResultsBuilder<BowlingGameResults> resultsBuilder = new BowlingGameResultsBuilder();
		Boolean actual = false;
		List<Play> plays = parser.parse("src\\test\\resources\\negative\\extra-score.txt");
		
		try {
			resultsBuilder.build(plays);
		} catch(BowlingGameException e) {
			actual = true;
		}
		
		
		assertTrue(actual);
	}
}
