package brickbreaker;

import javax.swing.JFrame;

// game modified by Kevin Phan

//features ADDED: 

// random ball starting position
// changed colors
// added lives feature
// ADDED MISSLES!!!
// add instructions

//features to add / fix:

//fix ball stuck in paddle glitch == if charges == 4, ballY = xyz
//fix brick + Missle glitch double register 

//add brick "layers" -- create layer by overlapping to maps OR additional values
//make bricks shoot back -- if level > 5, brick missleY 10
//power ups -- power up at top that moves back and forth

//catch (UnsupportedAudioFileException | IOException | LineUnavailableException exp) {

public class Main {

	public static void main(String[] args) {

		JFrame obj = new JFrame();
		Gameplay gamePlay = new Gameplay();

		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Brick Blaster by Kevin Phan");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);

		// trying to get sound to work

	}
}
