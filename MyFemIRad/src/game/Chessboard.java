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
	public static final int COMP = -1;
	public static final int HUMAN = 1;
	public static final int NONE = 0;
	public static final int DEPTH = 3;

	public static int FIRST = 1;
	public static final int COMP_LOSS = Integer.MIN_VALUE;
	public static final int DRAW = 0;

	public static final int COMP_WIN = Integer.MAX_VALUE;
//15*15
	public static final int CAPACITY = 15;
	private ArrayList<Position> positionsG = new ArrayList<>();
	private Color lineColor = new Color(66, 66, 66);// 棋盘背景色
	public static final int CELL_SIZE = 35;
	public static final int MARGIN = 20;

	public static final int SQUARE = CELL_SIZE * (CAPACITY - 1) + MARGIN * 2;
	private Position[][] positionsL = new Position[CAPACITY][CAPACITY];

	private Color backgroundColor = new Color(255, 245, 166);// 棋盘背景色

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
		if (x >= 0 && x < CAPACITY && y >= 0 && y < CAPACITY&&positionsL[x][y].getPlayer()==NONE) {
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

	public void place(int x, int y, int player) {
		if (positionsL[x][y].getPlayer() == NONE) {
			positionsL[x][y].setPlayer(player);
		}
	}

	public void unplace(int x, int y) {
		positionsL[x][y].setPlayer(NONE);
	}

	public int posTupleScore(int human, int computer) {
		// WWWWW
		if (computer == 5) {
			return Integer.MAX_VALUE;
		}
		// BBBBB
		if (human == 5) {
			return Integer.MIN_VALUE;
		}
		// W
		if (computer == 1 && human == 0) {
			return 15;
		}
		// WW
		if (computer == 2 && human == 0) {
			return 400;
		}
		// WWW
		if (computer == 3 && human == 0) {
			return 1800;
		}
		// WWWW
		if (computer == 4 && human == 0) {
			return 100000;
		}
		// B
		if (computer == 0 && human == 1) {
			return -35;
		}
		if (computer == 0 && human == 2) {
			return -800;
		}
		if (computer == 0 && human == 3) {
			return -15000;
		}
		if (computer == 0 && human == 4) {
			return -800000;
		}
		if (computer == 0 && human == 0) {
			return 7;
		}
		if (computer != 0 && human != 0) {
			return 0;
		}
		return -1;
	}

	public boolean win(int x, int y, int player) {
		// 一
		int sum = 0;
		for (int i = x - 4; i < x + 4 && i >= 0 && i < CAPACITY; i++) {

			if (positionsL[i][y].getPlayer() == player) {
				sum++;
			} else {
				sum = 0;
			}
			if (sum == 5) {
				return true;
			}
		}
		// 丨
		sum = 0;
		for (int i = y - 4; i < y + 4 && i >= 0 && i < CAPACITY; i++) {
			//System.out.println(x + " " + i);
			if (positionsL[x][i].getPlayer() == player) {
			//	System.out.println(positionsL[x][i].getPlayer());
				sum++;
			} else {
				sum = 0;
			}
			if (sum == 5) {
				System.out.println("femnglema");
				return true;
			}
		}
		sum = 0;
		//
		for (int i = x - 4, j = y - 4; i < x + 5 && i < CAPACITY && j < y + 5 && j < CAPACITY; i++, j++) {
			if (i >= 0 && j >= 0) {
				if (positionsL[i][j].getPlayer() == player) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					return true;
				}
			}
		}
		// 丿
		sum = 0;
		for (int i = x + 4, j = y - 4; i > x - 5 && i < CAPACITY && j < y + 5 && j < CAPACITY; i--, j++) {
			if (i >= 0 && j >= 0) {
				if (positionsL[i][j].getPlayer() == player) {
					sum++;
				} else {
					sum = 0;
				}
				if (sum == 5) {
					return true;
				}
			}
		}
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
			g.drawLine(MARGIN, MARGIN + i * CELL_SIZE, SQUARE - MARGIN, MARGIN + i * CELL_SIZE);// 画横线
			g.drawLine(MARGIN + i * CELL_SIZE, MARGIN, MARGIN + i * CELL_SIZE, SQUARE - MARGIN);// 画纵线
		}
	}

	private boolean isEmpty(int x, int y) {

		if (positionsL[x][y].getPlayer() == NONE) {
			return true;
		}
		return false;
	}

	public int evaluate() {
		int maxScore;
		// 一
		for (int i = 0; i < CAPACITY; i++) {
			for (int j = 0; j < CAPACITY; j++) {
				Position[] pos = new Position[5];
				int numberHuman = 0;
				int numberComp = 0;
				int tempScore = 0;
				for (int k = j; k < j + 5 && k + 4 < CAPACITY; k++) {
					if (positionsL[i][k].getPlayer() == HUMAN) {
						numberHuman++;
					}
					if (positionsL[i][k].getPlayer() == COMP) {
						numberComp++;
					}
					tempScore = posTupleScore(numberHuman, numberComp);
				}
				for (int k = j; k < j + 5 && k + 4 < CAPACITY; k++) {
					positionsL[i][k].addScore(tempScore);
				}
			}
		}
		// 一
		for (int j = 0; j < CAPACITY; j++) {
			for (int i = 0; i < CAPACITY; i++) {
				int numberHuman = 0;
				int numberComp = 0;
				int tempScore = 0;
				for (int k = i; k < i + 5 && k + 4 < CAPACITY; k++) {
					if (positionsL[k][j].getPlayer() == HUMAN) {
						numberHuman++;
					}
					if (positionsL[k][j].getPlayer() == COMP) {
						numberComp++;
					}
					tempScore = posTupleScore(numberHuman, numberComp);
				}
				for (int k = i; k < i + 5 && k + 4 < CAPACITY; k++) {
					positionsL[k][j].addScore(tempScore);
				}
			}
		}
		// 3.扫描右上角到左下角上侧部分
		for (int i = 14; i >= 4; i--) {
			for (int k = i, j = 0; j < CAPACITY && k >= 0; j++, k--) {
				int m = k;
				int n = j;
				int numberHuman = 0;
				int numberComp = 0;
				int tempScore = 0;
				while (m > k - 5 && k - 5 >= -1) {
					if (positionsL[m][n].getPlayer() == -1)
						numberComp++;
					else if (positionsL[m][n].getPlayer() == 1)
						numberHuman++;

					m--;
					n++;
				}
				if (m == k - 5) {
					tempScore = posTupleScore(numberHuman, numberComp);
					for (m = k, n = j; m > k - 5; m--, n++) {
						positionsL[m][n].addScore(tempScore);
					}
				}
			}
		}

		// 4.扫描右上角到左下角下侧部分
		for (int i = 1; i < CAPACITY; i++) {
			for (int k = i, j = CAPACITY - 1; j >= 0 && k < CAPACITY; j--, k++) {
				int m = k;
				int n = j;
				int numberHuman = 0;
				int numberComp = 0;
				int tempScore = 0;
				while (m < k + 5 && k + 5 <= 15) {
					if (positionsL[n][m].getPlayer() == -1)
						numberComp++;
					else if (positionsL[n][m].getPlayer() == 1)
						numberHuman++;

					m++;
					n--;
				}
				// 注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
				if (m == k + 5) {
					tempScore = posTupleScore(numberHuman, numberComp);
					// 为该五元组的每个位置添加分数
					for (m = k, n = j; m < k + 5; m++, n--) {
						positionsL[n][m].addScore(tempScore);
					}
				}

			}
		}
		// 5.扫描左上角到右下角上侧部分
		for (int i = 0; i < CAPACITY - 4; i++) {
			for (int k = i, j = 0; j < CAPACITY && k < CAPACITY; j++, k++) {
				int m = k;
				int n = j;
				int numberHuman = 0;
				int numberComp = 0;
				int tempScore = 0;
				while (m < k + 5 && k + 5 <= CAPACITY) {
					if (positionsL[m][n].getPlayer() == -1)
						numberComp++;
					else if (positionsL[m][n].getPlayer() == 1)
						numberHuman++;

					m++;
					n++;
				}
				// 注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
				if (m == k + 5) {
					tempScore = posTupleScore(numberHuman, numberComp);
					// 为该五元组的每个位置添加分数
					for (m = k, n = j; m < k + 5; m++, n++) {
						positionsL[m][n].addScore(tempScore);
					}
				}

			}
		}

		// 6.扫描左上角到右下角下侧部分
		for (int i = 1; i < CAPACITY - 4; i++) {
			for (int k = i, j = 0; j < CAPACITY && k < CAPACITY; j++, k++) {
				int m = k;
				int n = j;
				int numberHuman = 0;
				int numberComp = 0;
				int tempScore = 0;
				while (m < k + 5 && k + 5 <= CAPACITY) {
					if (positionsL[n][m].getPlayer() == -1)
						numberComp++;
					else if (positionsL[n][m].getPlayer() == 1)
						numberHuman++;
					m++;
					n++;
				}
				if (m == k + 5) {
					tempScore = posTupleScore(numberHuman, numberComp);
					for (m = k, n = j; m < k + 5; m++, n++) {
						positionsL[n][m].addScore(tempScore);
					}
				}

			}
		}

		int max = -1;
		for (int i = 0; i < CAPACITY; i++) {
			for (int j = 0; j < CAPACITY; j++) {
				int t = positionsL[i][j].getScore();
				if (t > max) {
					max = t;
				}
			}
		}
		int min = -1;
		for (int i = 0; i < CAPACITY; i++) {
			for (int j = 0; j < CAPACITY; j++) {
				int t = positionsL[i][j].getScore();
				if (t < min) {
					min = t;
				}
			}
		}
		return max+min;
	}

	private MoveInfo immediateCompWin() {
		for (int i = 0; i < CAPACITY; i++) {
			for (int j = 0; j < CAPACITY; j++) {
				if (isEmpty(i, j)) {
					place(i, j, COMP);
					if (win(i, j, COMP)) {
						unplace(i, j);
						return new MoveInfo(i, j, COMP);
					}
					unplace(i, j);

				}
			}
		}
		return null;
	}

	public boolean fullBoard() {
		for (int i = 0; i < CAPACITY; i++) {
			for (int j = 0; j < CAPACITY; j++) {
				if (isEmpty(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	public MoveInfo findCompMove(int depth) {
		int newDepth = depth - 1;
		System.out.println("depth is" + depth);
		int responseValue;
		int value;
		int bestX = 0;
		int bestY = 0;

		MoveInfo quickWinInfo;

		if (fullBoard())
			value = DRAW;
		else if ((quickWinInfo = immediateCompWin()) != null)
			return quickWinInfo;
		else if((quickWinInfo=immediateHumanWin())!=null) {
			return quickWinInfo;
		}
		else {
			value = COMP_LOSS;
			for (int i = 0; i < CAPACITY; i++) {
				for (int j = 0; j < CAPACITY; j++) {
					if (isEmpty(i, j)) {
						place(i, j, COMP);
						responseValue=	evaluate();
						//responseValue = findHumanMove(newDepth).value;
						unplace(i, j); // Restore board

						if (responseValue > value) {
							// Update best move
							value = responseValue;
							bestX = i;
							bestY = j;
						}
					}
				}
			}
		}

		return new MoveInfo(bestX, bestY, value);
	}

	// try to add parameter depth
	public MoveInfo findHumanMove(int depth) {
		int newDepth = depth - 1;
		int i, responseValue;
		int value;
		int bestX = 0;
		int bestY = 0;

		MoveInfo quickWinInfo;
		if (depth <= 0) {

			System.out.println("depth is" + depth);

			return new MoveInfo(0, 0, evaluate());
		} else if (fullBoard())
			value = DRAW;
		else if ((quickWinInfo = immediateHumanWin()) != null)
			return quickWinInfo;
		else {
			value = COMP_WIN;
			for (i = 1; i < CAPACITY; i++) {
				for (int j = 0; j < CAPACITY; j++) {

					if (isEmpty(i, j)) {
						place(i, j, HUMAN);
				//		responseValue = findCompMove(newDepth).value;
						responseValue=evaluate();
						unplace(i, j); // Restore board

						if (responseValue < value) {
							value = responseValue;
							bestX = i;
							bestY = j;
						}
					}
				}
			}
		}

		return new MoveInfo(bestX, bestY, value);
	}

	private MoveInfo immediateHumanWin() {
		for (int i = 0; i < CAPACITY; i++) {
			for (int j = 0; j < CAPACITY; j++) {
				if (isEmpty(i, j)) {

					place(i, j, HUMAN);
					if (win(i, j, HUMAN)) {
						unplace(i, j);
						return new MoveInfo(i, j, NONE);
					}
					unplace(i, j);
				}
			}
		}
		return null;
	}

}