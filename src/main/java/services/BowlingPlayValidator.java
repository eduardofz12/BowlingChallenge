package services;

import org.apache.commons.lang3.StringUtils;

import exception.BowlingGameException;
import interfaces.Validator;
import util.BowlingUtils;

public class BowlingPlayValidator implements Validator<String> {

	@Override
	public void validate(String line) {

		validateLine(line);
		validateOneTab(line);
		
		String name = line.split("\t")[0];
		String score = line.split("\t")[1];
		
		validateBowlerName(name);
		validateBowlerScore(score);
	}
	
	private void validateLine(String line) {
		if (StringUtils.isBlank(line)) {
			throw new BowlingGameException("The file cannot contain blank lines.");
		}
	}

	private void validateOneTab(String line) {
		if (!(line.lastIndexOf("\t") == line.indexOf("\t")) || line.lastIndexOf("\t") == -1) {
			throw new BowlingGameException(String.format("The file format is invalid. Rows can only have one tab. Line: %s.", line));
		}
	}
	
	private void validateBowlerName(String name) {
		if (!name.matches("[a-zA-Z]+")) {
			throw new BowlingGameException(String.format("The bowler name is invalid. Name: %s.", name));
		}
	}
	
	private void validateBowlerScore(String score){
		if (!score.matches("[0-9]+|F")
				|| BowlingUtils.parseScoreValue(score) < 0
				|| BowlingUtils.parseScoreValue(score) > 10) {
			throw new BowlingGameException(String.format("The informed score is invalid. Score: %s.", score));
		}
	}

}
