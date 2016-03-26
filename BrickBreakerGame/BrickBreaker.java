import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class BrickBreaker {
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Welcome to Nick's Version of Brickbreaker!");
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500,800);
        frame.setTitle("Breakout");
        frame.setResizable(false);
        frame.add(new GamePanel());
        frame.setVisible(true);
	}

	private static class GamePanel extends JPanel {
		
		Ball ball;
		Ball ball2;
		Paddle paddle;
		BrickConfiguration bconfig;
		Alien alien;
		Canada canada;
		ImageIcon beauty;
		Timer timer;
		boolean isDead;
		GamePanel panel;

		public GamePanel() {
			super();

			// call initializeGameObjects()
			initializeGameObjects();
			
			// add PaddleMover as a keyListener
			addKeyListener(new PaddleMover());

			setFocusable(true);		
		}

		public void initializeGameObjects() {
			// instantiate ball, paddle, and brick configuration
			
			paddle = new Paddle();
			ball = new Ball();
			bconfig = new BrickConfiguration();
			alien = new Alien();
			canada = new Canada();
			

			// set up timer to run GameMotion() every 10ms
			timer = new Timer(10, new GameMotion());
			timer.start();

			isDead = false;

			beauty = new ImageIcon("beauty.png");
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
		
			// paint ball, paddle, and brick configuration
			g2.drawImage(beauty.getImage(),0,0,null);
			paddle.paint(g2);
			ball.paint(g2);
			bconfig.paint(g2);

			if (isDead) {
				canada.paint(g2);
				if (alien.getX() < 700) {
					alien.setSpeed(10);
					alien.move();
					alien.paint(g2);
				}
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("Serif", Font.PLAIN, 30));
				g2.drawString("Game Over!", 580, 340);
				g2.drawString("The Canadians have won...", 490, 300);
				timer.stop();
				//scoreTimer.stop();
			}
			
		}

		private class GameMotion implements ActionListener {
			public GameMotion() {

			}

			public void actionPerformed(ActionEvent evt) {
				//move ball automatically
				ball.move();
				

				//move paddle according to key press
				paddle.move();
				

				//check if the ball hits the paddle or a brick
				checkForHit();
				
				
				//call repaint
				repaint();
			}
		}


		private class PaddleMover implements KeyListener {
			public void keyPressed(KeyEvent evt) {
				// change paddle speeds for left and right key presses
				int key = evt.getKeyCode();

				//restart game
				if (key == KeyEvent.VK_ENTER) {
					panel = new GamePanel();
					ball2 = new Ball();
					ball2.setXspeed(0);
					ball2.setYspeed(0);
					paddle.setSpeed(0);
				}
				//start game
				if (key == KeyEvent.VK_SPACE) {
					ball.setXspeed(0);
					ball.setYspeed(-4);
				}
				if (key == KeyEvent.VK_LEFT) {
					paddle.setSpeed(-6);
					if (key == KeyEvent.VK_RIGHT) {
						paddle.setSpeed(6);
						if (key == KeyEvent.VK_LEFT) {
							paddle.setSpeed(-6);
							if (key == KeyEvent.VK_RIGHT) {
								paddle.setSpeed(6);
							}
						}
					}
				}
				if (key == KeyEvent.VK_RIGHT) {
					paddle.setSpeed(6);
					if (key == KeyEvent.VK_LEFT) {
						paddle.setSpeed(-6);
						if (key == KeyEvent.VK_RIGHT) {
							paddle.setSpeed(6);
							if (key == KeyEvent.VK_LEFT) {
								paddle.setSpeed(-6);
							}
						}
					}
				}

				// To Cheat
				if (key == KeyEvent.VK_A) {
					ball.setXspeed(-8);
				}
				if (key == KeyEvent.VK_D) {
					ball.setXspeed(8);
				}
				if (key == KeyEvent.VK_S) {
					ball.setYspeed(8);
				}
				if (key == KeyEvent.VK_W) {
					ball.setYspeed(-8);
				}

			}
			public void keyReleased(KeyEvent evt) {
				// set paddle speed to 0
				paddle.setSpeed(0);
			}
			public void keyTyped(KeyEvent evt) {}
		}

		public void checkForHit() {
			
			// change ball speed when ball hits paddle
			if (ball.getShape().intersects(paddle.getShape())) {
				int leftSide = paddle.getX();
				int middleLeft = paddle.getX() + (int)(paddle.getWidth()/3);
				int middleRight = paddle.getX() + (int)(2*paddle.getWidth()/3);
				int rightSide = paddle.getX() + paddle.getWidth();

				if ((ball.getX() >= leftSide) && (ball.getX() < middleLeft)) {
					// change ball speed
					ball.setXspeed(-4);
					ball.setYspeed(-5);
				}
				if ((ball.getX() >= middleLeft) && (ball.getX() <= middleRight)) {
					// change ball speed
					ball.setXspeed(0);
					ball.setYspeed(-5);
				}
				if ((ball.getX() > middleRight) && (ball.getX() <= rightSide)) {
					// change ball speed
					ball.setXspeed(4);
					ball.setYspeed(-5);
				}
			}

			//change ball speed when ball hits brick
			for (int i = 0; i < bconfig.getRows(); i++) {
				for (int j = 0; j < bconfig.getCols(); j++) {
					if (bconfig.exists(i,j)) {
						if (ball.getShape().intersects(bconfig.getBrick(i,j).getShape())) {
							Point ballLeft = new Point((int)ball.getShape().getX(), (int)(ball.getShape().getY() + ball.getShape().getHeight()/2));
							Point ballRight = new Point((int)(ball.getShape().getX() + ball.getShape().getWidth()), (int)(ball.getShape().getY() + ball.getShape().getHeight()/2));
							Point ballTop = new Point((int)(ball.getShape().getX() + ball.getShape().getWidth()/2), (int)ball.getShape().getY());
							Point ballBottom = new Point((int)(ball.getShape().getX() + ball.getShape().getWidth()/2), (int)(ball.getShape().getY() + ball.getShape().getHeight()));
							if (bconfig.getBrick(i,j).getShape().contains(ballLeft)) {
								// change ball speed
								ball.setXspeed(4);
							}
							else if(bconfig.getBrick(i,j).getShape().contains(ballRight)) {
								// change ball speed
								ball.setXspeed(-4);
							}
							if (bconfig.getBrick(i,j).getShape().contains(ballTop)) {
								// change ball speed
								ball.setYspeed(5);
							}
							else if (bconfig.getBrick(i,j).getShape().contains(ballBottom)) {
								// change ball speed
								ball.setYspeed(-5);
							}
							

							// remove brick
							bconfig.removeBrick(i, j);
						}
					}
				}
			}

			if (ball.getY() >= 750) {
				isDead = true;
			}				
		}
	}
}