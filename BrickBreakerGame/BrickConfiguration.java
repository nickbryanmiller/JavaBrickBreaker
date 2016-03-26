import java.awt.*;
import java.awt.geom.*;

public class BrickConfiguration {
	
	//location and size variables
	private static final int xStart = 3;
	private static final int yStart = 50;	
	private static final int numCols = 27;
	private static final int numRows = 9;
	private static final int brickHeight = 20;
	private static final int brickWidth = 70;

	// 2D array containing brick objects
	private static Brick[][] bricks = new Brick[numCols][numRows];

	// 2D array telling us whether the brick should be painted (has it been hit?)
	private static boolean[][] paintBricks = new boolean[numCols][numRows];
	
	// constructor
	public BrickConfiguration() {
		
		// create new bricks and store them in bricks array
		for (int i = 0; i < numCols; i++) {
			for (int j = 0; j < numRows; j++) {
				// initialize paintBricks[i][j]
				paintBricks[i][j] = true;
				
				// initialize bricks[i][j]
				bricks[i][j] = new Brick(10 + (50 * i), 50 + (25 * j), 45, 20);
			}
		}		
	}

	// paint the bricks array to the screen
	public void paint(Graphics2D brush) {
		for (int i = 0; i < numCols; i++) {
			for (int j = 0; j < numRows; j++) {
				// determine if brick should be painted
				if (paintBricks[i][j] == true) {
					// if so, call paintBrick()
					paintBrick(bricks[i][j], brush);
				}
			}
		}
	}

	// paint an individual brick
	public void paintBrick(Brick brick, Graphics2D brush) {
		// call brick's paint method
		brick.paint(brush);
	}

	public void removeBrick(int row, int col) {
		// update paintBricks array for destroyed brick
		paintBricks[col][row] = false;
	}

	public Brick getBrick(int row, int col) {
		return bricks[col][row];
	}

	public boolean exists(int row, int col) {
		return paintBricks[col][row];
	}

	public int getRows() {
		return numRows;
	}

	public int getCols() {
		return numCols;
	}

}