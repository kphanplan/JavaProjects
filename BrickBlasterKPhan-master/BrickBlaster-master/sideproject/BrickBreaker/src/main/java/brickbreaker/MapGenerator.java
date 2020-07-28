package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	
	//generates the bricks
	public MapGenerator(int row, int col) {
		map = new int[row][col];
		for(int i = 0; i < map.length; i++ ) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 1; 
			}
		}
		
		brickWidth = 540/col;
		brickHeight = 150/row;
		
	}
	

	public void draw(Graphics2D g) {
		//these for loops generate the bricks based on map[][]
		for(int i = 0; i < map.length; i++ ) {
			for (int j = 0; j < map[0].length; j++) {	
				//this will draw a brick wherever the value is 1;
				if(map[i][j] > 0) {
					g.setColor(Color.orange);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
					//this generates the borders of each brick
					g.setStroke(new BasicStroke());
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value; 
	}
}
