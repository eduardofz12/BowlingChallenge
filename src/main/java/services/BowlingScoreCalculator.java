package services;

import java.util.List;

import interfaces.ScoreCalculator;
import models.BowlingGameResults;
import models.Pinfalls;
import util.BowlingUtils;

public class BowlingScoreCalculator implements ScoreCalculator<BowlingGameResults> {

	@Override
	public void calculate(BowlingGameResults frames) {

		frames.entrySet().stream().forEach(e -> {

			List<Integer> scores = e.getValue().getBowler().getScore();
			List<Pinfalls> shots = e.getValue().getBowler().getShots();

			Integer score = 0;

			for (int i = 0; i < shots.size(); i++) {

				Pinfalls currentPinfalls = shots.get(i);
				if (currentPinfalls.isStrike()) {
					score += calculateScoreForStrike(currentPinfalls, i, shots);
				} else {
					score += calculateScoreNormalPlay(currentPinfalls, i, shots);
				}
				scores.add(score);
			}
		});
	}

	public Boolean hasThirdShot(Pinfalls pinfall) {
		return pinfall.getThirdShot() != null;
	}

	private Integer calculateScoreForStrike(Pinfalls currentPinfalls, int currentFrame, List<Pinfalls> shots) {
		
		Integer score = 10;
		
		if (currentPinfalls.hasThirdShot()) {
			score += getShotsSum(currentPinfalls.getSecondShot(), currentPinfalls.getThirdShot());
		} else if (shots.get(currentFrame + 1).isStrike()) {
			
			if (shots.get(currentFrame + 1).hasThirdShot()) {
				score += getShotsSum(shots.get(currentFrame + 1).getFirstShot(), shots.get(currentFrame + 1).getSecondShot());
			} else {
				score += getShotsSum(shots.get(currentFrame + 1).getFirstShot(), shots.get(currentFrame + 2).getFirstShot());
			}
		} else {
			score += getShotsSum(shots.get(currentFrame + 1).getFirstShot(), shots.get(currentFrame + 1).getSecondShot());
		}
		return score;
	}

	private Integer calculateScoreNormalPlay(Pinfalls currentPinfalls, int currentFrame, List<Pinfalls> shots) {

		Integer score = getShotsSum(currentPinfalls.getFirstShot(), "0");

		if (currentPinfalls.isSpare()) {
			if (hasThirdShot(currentPinfalls)) {
				score += getShotsSum(currentPinfalls.getSecondShot(), currentPinfalls.getThirdShot());
			} else {
				score += getShotsSum(currentPinfalls.getSecondShot(), shots.get(currentFrame + 1).getFirstShot());
			}
		} else {
			score += getShotsSum(currentPinfalls.getSecondShot(), "0");
		}

		return score;
	}

	private Integer getShotsSum(String a, String b) {
		return BowlingUtils.parseScoreValue(a) + BowlingUtils.parseScoreValue(b);
	}
}
