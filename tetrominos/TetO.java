package tetrominos;

import utils.Color;
import utils.ColorConstants;

public class TetO extends Tetromino {

	public TetO() {
		super(new Color(ColorConstants.YELLOW), 5, 15);
	}

	@Override
	protected boolean hasCollided() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void rotateLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void rotateRight() {
		// TODO Auto-generated method stub
		
	}
}
