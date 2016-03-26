import java.awt.geom.*;

public class Brick extends ColorShape {
		private int xPos = 3;
		private int yPos = 50;
		private int width = 70;
		private int height = 20;
		private Rectangle2D.Double shape;

		// constructor
		public Brick(int x, int y, int w, int h) {
			super(new Rectangle2D.Double(x, y, w, h));
			
			//set brick x, y, width, and height
			xPos = x;
			yPos = y;
			width = w;
			height = h;
	
			// update shape
			shape = (Rectangle2D.Double)super.shape;
			shape.setRect(xPos, yPos, width, height);
		}

		public int getX() {
			return xPos;
		}

		public Rectangle2D.Double getShape() {
			return shape;
		}
	}