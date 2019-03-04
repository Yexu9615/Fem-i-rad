package game;

import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * JPanel that represents the graphic interface
 * 
 * @author Yelin
 *
 */
public class Chessboard extends JPanel {
	public static int FIRST=1;

	public static final int CAPACITY = 15;
	private ArrayList<Position> locations = new ArrayList<>();
	private Color lineColor = new Color(66, 66, 66);// ÆåÅÌ±³¾°É«
	public static final int CELL_SIZE=35;
	public static final int MARGIN=20;

	public static final int SQUARE=CELL_SIZE*(CAPACITY-1)+MARGIN*2; 
	private HashMap<Position,Integer>positions=new HashMap<>();
	
	private Color backgroundColor = new Color(255, 245, 166);// ÆåÅÌ±³¾°É«
	Chessboard(){
		start();
		setPreferredSize(new Dimension(600, 600));
		setMaximumSize(new Dimension(600,600));
		setMinimumSize(new Dimension(600, 600));
		setLayout(null);
		
	}
	public boolean isLegal(int x, int y) {
		if (x >= 0 && x < CAPACITY && y >= 0 && y < CAPACITY) {
			return true;
		}
		return false;
	}
	public void start() {
		
	}
	public void add(Position loc){
	locations.add(loc);
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		drawChessboard(g);
		drawChessman(g);

	}
public boolean win() {
	
	//TODO
	return false;
}
	private void drawChessman(Graphics g) {
		for (Position l:locations) {

			if (l.getPlayer() == FIRST) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.WHITE);
			}
	
			g.fillOval(MARGIN + CELL_SIZE*l.getX() - CELL_SIZE/2, MARGIN + CELL_SIZE*l.getY() - CELL_SIZE/2, CELL_SIZE, CELL_SIZE);
		}
	}

	private void drawChessboard(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(lineColor);
		
		for(int i = 0; i <CAPACITY; i++){
			g.drawLine(MARGIN, MARGIN + i*CELL_SIZE, SQUARE - MARGIN, MARGIN + i*CELL_SIZE);//»­ºáÏß
			g.drawLine(MARGIN + i*CELL_SIZE, MARGIN, MARGIN + i*CELL_SIZE, SQUARE - MARGIN);//»­×ÝÏß
		}
	}
}
