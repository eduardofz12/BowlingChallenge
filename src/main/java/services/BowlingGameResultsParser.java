package services;

import static util.ScoreTypeEnum.FOUL;
import static util.ScoreTypeEnum.SPARE;
import static util.ScoreTypeEnum.STRIKE;

import interfaces.GameResultsParser;
import models.BowlingGameResults;
import models.Pinfalls;
import util.BowlingUtils;

public class BowlingGameResultsParser implements GameResultsParser<StringBuilder, BowlingGameResults>{

	@Override
	public StringBuilder parse(BowlingGameResults frame) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Frame");
		for (int i = 1; i < 11; i++) {
			sb.append("\t\t" + i);
		}

		for (var entry : frame.entrySet()) {

			sb.append("\n");
			sb.append(entry.getKey());
			sb.append("\n");
			sb.append("Pinfalls\t");

			for (Pinfalls p : entry.getValue().getBowler().getShots()) {

				if (isStrike(p.getFirstShot())) {
					if (p.hasThirdShot()) {
						sb.append(STRIKE.getCode() + "\t");
					} else {
						sb.append("\t");
						sb.append(STRIKE.getCode() + "\t");
						continue;
					}
				} else if (isFoul(p.getFirstShot())) {
					sb.append(FOUL.getCode() + "\t");
				} else {
					sb.append(p.getFirstShot() + "\t");
				}

				if (isSpare(p)) {
					sb.append(SPARE.getCode() + "\t");
				} else if (isFoul(p.getSecondShot())) {
					sb.append(FOUL.getCode() + "\t");
				} else {
					sb.append(p.getSecondShot() + "\t");
				}

				if (p.hasThirdShot()) {
					if (isStrike(p.getThirdShot())) {
						sb.append(STRIKE.getCode());
					} else if (isSpareThirdShot(p)) {
						sb.append(SPARE.getCode());
					} else if (isFoul(p.getThirdShot())) {
						sb.append(FOUL.getCode());
					} else {
						sb.append(p.getThirdShot());
					}
				}
			}

			sb.append("\n");
			sb.append("Score");
			for (Integer score : entry.getValue().getBowler().getScore()) {
				sb.append("\t\t" + score);
			}
		}
		
		return sb;
	}
	
	private Boolean isStrike(String score) {
		return STRIKE.getScore().equals(score);
	}

	private Boolean isFoul(String score) {
		return FOUL.getScore().equals(score);
	}

	private Boolean isSpare(Pinfalls pinfall) {
		return BowlingUtils.parseScoreValue(pinfall.getFirstShot())
				+ BowlingUtils.parseScoreValue(pinfall.getSecondShot()) == 10;
	}

	private Boolean isSpareThirdShot(Pinfalls pinfall) {
		return BowlingUtils.parseScoreValue(pinfall.getSecondShot())
				+ BowlingUtils.parseScoreValue(pinfall.getThirdShot()) == 10;
	}

}
