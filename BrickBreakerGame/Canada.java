import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Canada {
	private int height;
	private int width;
	private int x;
	private int y;
	private int speed;
	private ImageIcon img;


	public Canada() {
		speed = 0;
		img = new ImageIcon("canada.png");
		height = img.getIconHeight();
		width = img.getIconWidth();
		x = 800;
		y = 500;

	}

	public void move() {
		x += speed;
	}
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}

	public void paint(Graphics2D brush) {
		brush.drawImage(img.getImage(),x,y,null);
	}

	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(x,y, width,height);
	}

}