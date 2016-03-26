import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Alien {
	private static int height;
	private static int width;
	private static int xPos;
	private static int yPos;
	private static int speed;
	private ImageIcon img;


	public Alien() {
		speed = 0;
		img = new ImageIcon("alien.png");
		height = img.getIconHeight();
		width = img.getIconWidth();
		xPos = -200;
		yPos = 500;

	}

	public void move() {
		xPos += speed;
	}
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}
	public int getX() {
		return xPos;
	}
	public int getY() {
		return yPos;
	}
	public void paint(Graphics2D brush) {
		brush.drawImage(img.getImage(),xPos,yPos,null);
	}

	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(xPos,yPos, width,height);
	}

}