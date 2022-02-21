package util;

public enum ScoreTypeEnum {

	STRIKE("X", "10", "Strike"),
	SPARE("/", null, "Spare"),
	FOUL("F", "F", "Foul");

	private String code;
	private String score;
	private String description;

	private ScoreTypeEnum(String code, String score, String description) {
		this.code = code;
		this.score = score;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

}
