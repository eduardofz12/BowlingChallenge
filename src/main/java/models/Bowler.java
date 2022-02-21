package models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Bowler {

	private List<Pinfalls> shots = new ArrayList<>();
	private List<Integer> score = new ArrayList<>();
	
	public void addShot(Pinfalls shot) {
		shots.add(shot);
	}
	
	public Integer getShotsSize() {
		return shots.size();
	}
}
