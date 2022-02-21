package models;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;
import lombok.NoArgsConstructor;
import util.BowlingUtils;
import util.ScoreTypeEnum;

@Data
@NoArgsConstructor
public class Pinfalls {

	private String firstShot;
	private String secondShot;
	private String thirdShot;
	private Boolean done = false;

	public Boolean isDone() {
		return done == true;
	}

	public Boolean hasSecondShot() {
		return StringUtils.isNotBlank(secondShot);
	}

	public Boolean hasThirdShot() {
		return StringUtils.isNotBlank(thirdShot);
	}

	public Pinfalls(String firstShot) {
		this.firstShot = firstShot;
	}

	public Boolean isStrike() {
		return ScoreTypeEnum.STRIKE.getScore().equals(firstShot);
	}

	public Boolean isSpare() {
		return BowlingUtils.parseScoreValue(getFirstShot()) + BowlingUtils.parseScoreValue(getSecondShot()) == 10;
	}

}
