package interfaces;

import models.BowlingGameResults;

public interface GameResultsParser<T, U extends BowlingGameResults> {

	T parse(U game);
}
