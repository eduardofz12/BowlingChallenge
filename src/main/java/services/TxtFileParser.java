package services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.BowlingGameException;
import interfaces.FileParser;
import interfaces.Validator;
import models.Play;

public class TxtFileParser implements FileParser<List<Play>> {

	private static final Logger logger = LogManager.getLogger(TxtFileParser.class);
	
	private Validator<String> linesValidator;

	public TxtFileParser(Validator<String> linesValidator) {
		this.linesValidator = linesValidator;
	}

	@Override
	public List<Play> parse(String path) {

		List<Play> plays = new ArrayList<>();
		
		try {
			plays = Files.lines(Paths.get(path)).map(this::validateAndCreatePlay).toList();
		} catch (IOException e) {
			logger.error(String.format("Exception: %s. Message: %s. Cause: %s. ", e.getClass(), e.getMessage(), e.getCause()));
			throw new BowlingGameException(String.format("Error reading from file: %s.", path));	
		} catch (InvalidPathException e) {
			throw new BowlingGameException(String.format("Invalid path: %s.", path));
		}

		if(plays.isEmpty()) {
			throw new BowlingGameException("The file cannot be empty.");
		}
		
		return plays;
	}
	
	private Play validateAndCreatePlay(String line) {
		linesValidator.validate(line);
		String[] play = line.split("\t");
		return new Play(play[0], play[1]);
	}

}
