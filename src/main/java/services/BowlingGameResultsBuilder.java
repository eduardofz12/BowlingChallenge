package services;

import java.util.List;

import exception.BowlingGameException;
import interfaces.GameResultsBuilder;
import models.Bowler;
import models.BowlingGameResults;
import models.Frame;
import models.Pinfalls;
import models.Play;

public class BowlingGameResultsBuilder implements GameResultsBuilder<BowlingGameResults> {

	@Override
	public BowlingGameResults build(List<Play> plays) {

		BowlingGameResults frames = new BowlingGameResults();

		plays.forEach(play -> processPlay(frames, play));

		return frames;
	}

	private void processPlay(BowlingGameResults frames, Play play) {
		if (!hasCurrentBowler(play.getBowlerName(), frames)) {

			frames.put(play.getBowlerName(), initializeBowlerFrame(play));

		} else {

			Frame frame = frames.get(play.getBowlerName());
			List<Pinfalls> shots = frame.getBowler().getShots();
			
			Pinfalls pinfall = shots.get(shots.size() - 1);

			if (!pinfall.isDone()) {
				addSecondOrThirdShot(pinfall, frame.getBowler().getShotsSize(), play);
			} else {
				addFirstShot(shots, play);
			}
		}
	}

	private Boolean hasCurrentBowler(String bowlerName, BowlingGameResults frames) {
		return frames.containsKey(bowlerName);
	}

	private Frame initializeBowlerFrame(Play play) {

		Bowler bowler = new Bowler();
		Pinfalls pinfall = new Pinfalls();

		pinfall.setFirstShot(play.getScore());

		if (pinfall.isStrike()) {
			pinfall.setDone(true);
		}

		bowler.addShot(pinfall);
		
		return new Frame(play.getBowlerName(), bowler);
	}

	private void addSecondOrThirdShot(Pinfalls pinfall, Integer shotsSize, Play play) {

		if (shotsSize == 10) {
			if(pinfall.hasThirdShot()) {
				throw new BowlingGameException(String.format("Invalid number of shots for player: %s.", play.getBowlerName()));
			}
			
			if (!pinfall.hasSecondShot()) {
				pinfall.setSecondShot(play.getScore());
			} else {
				pinfall.setThirdShot(play.getScore());
			}
		} else {
			pinfall.setSecondShot(play.getScore());
			pinfall.setDone(true);
		}
	}

	private void addFirstShot(List<Pinfalls> shots, Play play) {
		
		Pinfalls newPinfall = new Pinfalls(play.getScore());
		if (newPinfall.isStrike() && shots.size() != 9) {
			newPinfall.setDone(true);
		}
		shots.add(newPinfall);
	}
}
