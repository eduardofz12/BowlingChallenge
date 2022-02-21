package util;

public class BowlingUtils {

	public static int parseScoreValue(String score) {
		try {
			return Integer.parseInt(score);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
