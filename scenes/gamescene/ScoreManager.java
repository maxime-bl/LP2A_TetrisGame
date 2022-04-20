package scenes.gamescene;

public class ScoreManager {

	private int score;
	private boolean isBack2Back = false;
	
	public ScoreManager() {
		score = 0;
	}
	
	public ScoreManager(int startScore) {
		this.score = startScore;
	}
	
	public void update(int nbLines) {
		if (nbLines < 4) {
			this.score += nbLines * 100;
			isBack2Back = false;
		} else {
			if (isBack2Back == true) {
				this.score += 1200;
			} else {
				this.score += 800;
			}
			isBack2Back = true;
		}
	}
	
	public void display() {
		
	}
}
