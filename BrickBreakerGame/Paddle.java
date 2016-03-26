import java.awt.*;
import java.awt.geom.*;

public class Paddle extends ColorShape{
	
	// location and size variables
	private static int speed;
	private static int xPos;
	private static int transition;
	private static int jump;
	private static final int yPos = 700;
	private static final int width = 150;
	private static final int height = 6;

	private static final Rectangle2D.Double shape = new Rectangle2D.Double(600,yPos,width,height);

	public Paddle() {
		super(shape);

		// set paddle color
		setFillColor(Color.WHITE);
		setBorderColor(Color.WHITE);

		// set paddle position and speed
		xPos = 590;
		speed = 0;
	}

	public void move() {		
		// stop the paddle from moving at the edges
		if (xPos >= 1215) {
			setSpeed(0);
			xPos = xPos - 5;		
		}
		if (xPos <= -4) {
			setSpeed(0);
			xPos = xPos + 7;
		}
		// move paddle
		xPos += speed;
		
		// update shape
		shape.setRect(xPos, yPos, width, height);
	}

	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}

	public void jump(int jump) {
		transition = jump;
	}

	public void transition() {
		xPos = xPos + transition;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getX() {
		return xPos;
	}

	public Rectangle2D.Double getShape() {
		return shape;
	} 

}