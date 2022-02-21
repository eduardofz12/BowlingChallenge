package interfaces;

import java.util.List;

import models.Play;

public interface GameResultsBuilder<T> {

	T build(List<Play> plays);
}
