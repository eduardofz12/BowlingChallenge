package runner;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import exception.BowlingGameException;
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
import services.ConsoleOutputWriter;
import services.TxtFileParser;

public class ProjectBuilder {

	public static void main(String[] args) {

		String fileName = args.length > 0 ? args[0] : "";
		if (StringUtils.isBlank(fileName)) {
			throw new BowlingGameException("Please inform a file to parse.");
		}

		FileParser<List<Play>> parser = new TxtFileParser(new BowlingPlayValidator());
		GameResultsBuilder<BowlingGameResults> resultsBuilder = new BowlingGameResultsBuilder();
		ScoreCalculator<BowlingGameResults> calculator = new BowlingScoreCalculator();
		GameResultsParser<StringBuilder, BowlingGameResults> game = new BowlingGameResultsParser();
		OutputWriter<StringBuilder> writer = new ConsoleOutputWriter();
		
		List<Play> plays = parser.parse(fileName);
		BowlingGameResults results = resultsBuilder.build(plays);
		calculator.calculate(results);
		writer.write(game.parse(results));
	}
}
