package interfaces;

import models.BowlingGameResults;

public interface ScoreCalculator<T extends BowlingGameResults> {

	void calculate(T gameResults);
}
