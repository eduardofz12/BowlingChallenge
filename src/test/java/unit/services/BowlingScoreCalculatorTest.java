package unit.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import interfaces.ScoreCalculator;
import models.Bowler;
import models.BowlingGameResults;
import models.Frame;
import models.Pinfalls;
import services.BowlingScoreCalculator;

public class BowlingScoreCalculatorTest {

	@Test
	void bowlingScoreCalculator_testOne() throws Exception {
		// arrange
		ScoreCalculator<BowlingGameResults> bowlingScoreCalculator = new BowlingScoreCalculator();
		BowlingGameResults perfectGame = buildPerfectGame("Robson");
		int score = 300;

		// act
		bowlingScoreCalculator.calculate(perfectGame);

		// assert
		assertEquals(score, perfectGame.get("Robson").getBowler().getScore().get(9));
	}

	private BowlingGameResults buildPerfectGame(String bowlerName) {

		List<Pinfalls> shots = new ArrayList<>();
		for(int i = 0; i < 9; i ++) {
			Pinfalls pinfall = new Pinfalls("10");
			pinfall.setDone(true);
			shots.add(pinfall);
		}
		Pinfalls pinfall = new Pinfalls("10");
		pinfall.setSecondShot("10");
		pinfall.setThirdShot("10");
		
		shots.add(pinfall);
		
		Bowler bowler = new Bowler();
		bowler.setShots(shots);
		Frame frame = new Frame(bowlerName, bowler);
		
		BowlingGameResults bowlingGameResults = new BowlingGameResults();
		bowlingGameResults.put(bowlerName, frame);
		return bowlingGameResults;
	}

}
