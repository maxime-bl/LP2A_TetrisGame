package scenes.gamescene;

import tetrominos.Tetromino;

public class TetHolder {
	private Tetromino tetInHold;
	
	public TetHolder() {
		tetInHold = null;
	}
	
	public Tetromino swap(Tetromino current){
		if (tetInHold == null) {
			return current;
		} else {
			Tetromino temp = tetInHold;
			
			///////////////////////////////////
			
			return temp;
		}
	}
}
