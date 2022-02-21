package integration.positive;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import interfaces.FileParser;
import interfaces.GameResultsBuilder;
import interfaces.GameResultsParser;
import interfaces.OutputWriter;
import interfaces.ScoreCalculator;
import models.BowlingGameResults;
import models.Play;
import services.BowlingGameResultsBuilder;
import services.BowlingGameResultsParser;
import services.BowlingPlayValidator;
import services.BowlingScoreCalculator;
import services.FileOutputWriter;
import services.TxtFileParser;

public class SimpleInputTest {

	@Test
	public void simpleInputExecuteSuccessfully() throws IOException {
		FileParser<List<Play>> parser = new TxtFileParser(new BowlingPlayValidator());
		GameResultsBuilder<BowlingGameResults> resultsBuilder = new BowlingGameResultsBuilder();
		ScoreCalculator<BowlingGameResults> calculator = new BowlingScoreCalculator();
		GameResultsParser<StringBuilder, BowlingGameResults> game = new BowlingGameResultsParser();
		OutputWriter<StringBuilder> writer = new FileOutputWriter("src\\test\\resources\\test_output.txt");
		
		List<Play> plays = parser.parse("src\\test\\resources\\positive\\scores.txt");
		BowlingGameResults results = resultsBuilder.build(plays);
		calculator.calculate(results);
		writer.write(game.parse(results));
		
		// assert
		boolean actual = FileUtils.contentEquals(new File("src\\test\\resources\\test_output.txt"),
				new File("src\\test\\resources\\filesToCompare\\correct_simple_input_output.txt"));
		assertTrue(actual);
	}
}
