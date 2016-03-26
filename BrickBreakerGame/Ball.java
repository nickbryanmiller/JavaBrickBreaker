import java.awt.geom.*;
import java.awt.*;

public class Ball extends ColorShape {
	
	// location and size variables
	private int xPos;
	private int yPos;
	private int xSpeed;
	private int ySpeed;
	private static final int height = 15;
	private static final int width = 15;
	private static final Ellipse2D.Double shape = new Ellipse2D.Double(660, 675, width, height);

	// constructor
	public Ball() {
		super(shape);

		// set ball color
		super.setFillColor(Color.GREEN);
		super.setBorderColor(Color.GREEN);
		
		// set initial values for x and y position and speed
		xPos = 660;
		yPos = 675;
		xSpeed = 0;
		ySpeed = 0;
	}

	public void move() {
		
		// detect if ball should bounce off an edge
		if (xPos >= 1350) {
			xSpeed = -4;
			xPos = xPos - 4;
		}
		if (xPos <= 0) {
			xSpeed = 4;
			xPos = xPos + 4;
		}
		if (yPos <= 0) {
			ySpeed = ySpeed * -1;
			yPos = yPos + 5;
		}

		// move ball
		xPos += xSpeed;
		yPos += ySpeed;

		// update shape to new values
		shape.setFrame(xPos, yPos, width, height);
	}

	public void setXspeed(int newSpeed) {
		xSpeed = newSpeed;
	}

	public void setYspeed(int newSpeed) {
		ySpeed = newSpeed;
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Ellipse2D.Double getShape() {
		return shape;
	}
}