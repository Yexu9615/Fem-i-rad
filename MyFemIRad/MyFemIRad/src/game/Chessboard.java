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
	public static int FIRST = 1;

	public static final int CAPACITY = 15;
	private ArrayList<Position> positionsG = new ArrayList<>();
	private Color lineColor = new Color(66, 66, 66);// ÆåÅÌ±³¾°É«
	public static final int CELL_SIZE = 35;
	public static final int MARGIN = 20;

	public static final int SQUARE = CELL_SIZE * (CAPACITY - 1) + MARGIN * 2;
	private Position[][] positionsL = new Position[CAPACITY][CAPACITY];

	private Color backgroundColor = new Color(255, 245, 166);// ÆåÅÌ±³¾°É«

	Chessboard() {
		start();
		setPreferredSize(new Dimension(600, 600));
		setMaximumSize(new Dimension(600, 600));
		setMinimumSize(new Dimension(600, 600));
		setLayout(null);
		for (int i = 0; i < CAPACITY; i++) {
			for (int j = 0; j < CAPACITY; j++) {
				positionsL[i][j] = new Position(i, j, 0);
			}
		}

	}

	public boolean isLegal(int x, int y) {
		if (x >= 0 && x < CAPACITY && y >= 0 && y < CAPACITY) {
			return true;
		}
		return false;
	}

	public void start() {

	}

	public void add(Position loc) {
		positionsL[loc.getX()][loc.getY()] = loc;
		positionsG.add(loc);
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		drawChessboard(g);
		drawChessman(g);

	}

	public boolean win(int x, int y,int player) {
		//Ò»
		int sum = 0;
		for (int i = x - 4; i < x + 4 && i >= 0 && i < CAPACITY - 1; i++) {

			if (positionsL[i][y].getPlayer() == player) {
				sum++;
			} else {
				sum = 0;
			}
			if (sum == 5) {
				return true;
			}
		}
		//Ø­
		sum = 0;
		for (int i = y - 4; i < y + 4 && i >= 0 && i < CAPACITY - 1; i++) {
			if (positionsL[x][i].getPlayer() == player) {
				sum++;
			} else {
				sum = 0;
			}
			if (sum == 5) {
				return true;
			}
		}
		sum = 0;
		//na 
		for (int i = x - 4, j = y - 4; i < x + 4 && i >= 0 && i < CAPACITY - 1 && j < y + 4 && j >= 0
				&& j < CAPACITY - 1; i++, j++) {
			if (positionsL[i][j].getPlayer() == player) {
				sum++;
			} else {
				sum = 0;
			}
			if (sum == 5) {
				return true;
			}
		}
		//Ø¯
		sum = 0;
		for (int i = x + 4, j = y - 4; i > x - 4 && i >= 0 && i < CAPACITY - 1 && j < y + 4 && j >= 0
				&& j < CAPACITY - 1; i--, j++) {
			if (positionsL[i][j].getPlayer() == player) {
				sum++;
			} else {
				sum = 0;
			}
			if (sum == 5) {
				return true;
			}
		}
		// TODO
		return false;
	}

	private void drawChessman(Graphics g) {
		for (Position l : positionsG) {

			if (l.getPlayer() == FIRST) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.WHITE);
			}

			g.fillOval(MARGIN + CELL_SIZE * l.getX() - CELL_SIZE / 2, MARGIN + CELL_SIZE * l.getY() - CELL_SIZE / 2,
					CELL_SIZE, CELL_SIZE);
		}
	}

	private void drawChessboard(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(lineColor);

		for (int i = 0; i < CAPACITY; i++) {
			g.drawLine(MARGIN, MARGIN + i * CELL_SIZE, SQUARE - MARGIN, MARGIN + i * CELL_SIZE);// »­ºáÏß
			g.drawLine(MARGIN + i * CELL_SIZE, MARGIN, MARGIN + i * CELL_SIZE, SQUARE - MARGIN);// »­×ÝÏß
		}
	}

	public Position searchPosition() {
		// TODO Auto-generated method stub
		return null;
	}
}