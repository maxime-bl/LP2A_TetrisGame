package tetrominos;

import utils.Color;
import utils.ColorConstants;

public class TetJ extends Tetromino {
	
	public TetJ() {
		super(new Color(ColorConstants.BLUE), 5, 15);
		
	}
	
	@Override
	protected boolean hasCollided() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void rotateLeft() {
		
	}
	
	@Override
	protected void rotateRight() {
		// TODO Auto-generated method stub
		
	}
}
