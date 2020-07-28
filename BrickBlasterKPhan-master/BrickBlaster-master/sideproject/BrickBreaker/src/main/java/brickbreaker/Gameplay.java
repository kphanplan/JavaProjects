package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Gameplay extends JPanel implements KeyListener, ActionListener {

	// SETTING GAME ATTRIBUTES //	

	private int level = 1;	
	private int lives = 3;
	int maxX = 500;
	int minX = 100;
	int rangeX = maxX - minX + 1;
	
	private int score = 0;
	
	// game does not start playing until input
	private boolean play = false;

	// total bricks
	private int totalBricks = 14 + (level * 7);
	
	// sets the speed of ball, delay, and starting position of slider
	private Timer timer;
	private int delay = 10;
	private int playerX = 310;
	
	// sets ball properties
	private int ballposX = (int) ((Math.random() * rangeX) + minX);
	private int ballposY = 350;
	private double ballXdir = -2;
	private double ballYdir = -4 * (level * 0.3);

	//	Missile (laser) properties
	private int laserposX = 350;
	private int laserposY = 510;
	private int laserYdir = 0;
	private int charges = 0; 

	//generates the map
	private MapGenerator map;
	private boolean gameStart = true; 
	
	// DEFINING GAMEPLAY //

	public Gameplay() {
		// this creates a new instance of map from MapGenerator and plugs in 3 for rows
		// and 7 columns
		map = new MapGenerator(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	// CREATING THE SPRITES, BACKGROUND AND GRAPHICS //
	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);

		// this draws the bricks
		map.draw((Graphics2D) g);

		// borders
		g.setColor(Color.white);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);

		//points
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		// this actually draws the string
		g.drawString("Score: " + score, 530, 30);
		g.drawString("Lives: " + lives, 30, 30);
		g.drawString("Missles: " + charges, 30, 550);
		g.drawString("Level: " + level, 300, 30);

		// paddle
		g.setColor(Color.red);
		g.fillRect(playerX, 500, 100, 8);

		// ball
		g.setColor(Color.blue);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		// laser 
		g.setColor(Color.blue);
		g.fillRect(laserposX, laserposY, 10, 30);
		//---------------------------------------GAME SCREENS
		
		// WON!
		if (totalBricks <= 0) {
			play = false; 
		}
		if (totalBricks <= 0 && play == false) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("GOOD JOB!", 260, 300);

			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("Press Up for next level: " + (level +1), 200, 250);
		} 
		
		//game start
		if (gameStart == true) {
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 25));
			// this actually draws the string
			g.drawString("WELCOME TO BRICK BLASTER", 160, 250);
			g.drawString("Press <- and -> to move! Space to SHOOT!", 130, 300);
			g.drawString("Press UP to start the game!" , 200, 350);
		}
		
		// GAME OVER!
		if (lives <= 0 && play == false) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("FINAL SCORE:" + score, 160, 300);

			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("Press Up to play again", 200, 250);
		}

		if (ballposY > 570) {
			play = false; 
		}
		// gets rid of the values and makes room for the next iteration / update
		g.dispose();
	}

	//============GAME PROPERTIES
	
	public void actionPerformed(ActionEvent arg0) {
		// Note: this recreates each "Frame" of the game
		if (charges >= 4) {
			charges = 4;
		}
		
		if (ballYdir <= -7) {
			ballYdir = -7;
		}
		
		timer.start();
		if (ballposY >= 570) {
			play = false;
			lives -= 1;
			ballposX = (int) ((Math.random() * rangeX) + minX);
			ballposY = 350;
		}
		if (laserposY < 0) {
			laserposY = 510;
			laserYdir = 0; 
		}
		if (play) {
			// creates two "hitboxes" for the objects. if ball(width,height) intesects with
			// paddle(width, height) "bounce" it.
			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 500, 100, 8))) {
				ballYdir = -ballYdir;
				charges += 1; 
			}

			// if the value of brick is > 1, then it will intersect with the ball.
			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;

						// Hitboxes for ball and bricks
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle laser = new Rectangle(laserposX, laserposY, 10, 30);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						if (laser.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							laserposY = 510;
							laserYdir = 0; 
							totalBricks -= 1;
							score += 5 * level;
						}
						
						// in the event ball intersects brick, makes score go up and
						// set brick value to 0, deleting it
						if (ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks -= 1;
							System.out.println(totalBricks);
							score += 5 * level;
						
							// this causes ball to "bounce" when it hits the brick from x || y side.
							if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
								
							} else {
								ballYdir = -ballYdir;
							}
							break A;
						}
					}
				}
			}
			
			//keeps the ball's velocity
			ballposX += ballXdir;
			ballposY += ballYdir;
			laserposY += laserYdir;
			
			// if the ball is at the border, it "Bounces"
			if (ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if (ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if (ballposX > 670) {
				ballXdir = -ballXdir;
			}
		}
		repaint();
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	//---------------------PLAYER CONTROLS //
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (charges > 0 && laserposY == 510) {
			laserYdir = -10;
			charges -= 1; 
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
			totalBricks = 0;
		}
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX >= 580) {
				playerX = 580;
			} else {
				moveRight();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			if (playerX <= 20) { 
				playerX = 20;
			} else {
				moveLeft();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (!play) {
				gameStart = false; 
				// died
				if (lives <= 0) {
					play = true;
					level = 1;	
					lives = 3; 
					score = 0;
					map = new MapGenerator(3, 7);		
					totalBricks = 21; 
					playerX = 310;
					ballposX = (int) ((Math.random() * rangeX) + minX);
					ballposY = 350;
					ballYdir = -4 * (level * 0.3);
				}
				// life lost
				if (lives >= 0 && totalBricks > 0 ) {
					play = true;
					ballposY = 350;
					ballXdir = -2;
					ballYdir =  -4 * (level * 0.3);
				}
				//beat level 
				if (lives >= 0 && totalBricks <= 0) {
					level += 1;
					map = new MapGenerator(2 + level, 7);
					totalBricks = 14 + (level * 7);
					play = true;
					ballposY = 350;
					ballXdir = -2;
					ballYdir = -4 * (level * 0.3);
				} 
				repaint();
			}
		}
	}
	public void moveRight() {
		laserposX = playerX + 70;
		playerX += 30;
	}
	public void moveLeft() {
		laserposX = playerX + 15;
		playerX -= 30;
	}
}
