package game;

public class Position {
	private int x,y;
	private int player=0;
	private int score;
	Position(int x,int y, int player){
		this.	x=x;
		this.y=y;
		this.player=player;
	}
	public int getPlayer() {
		return player;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getScore() {
		return score;
	}
	public void addScore(int n) {
		this.score+=n;
	}
public int hashCode() {
	return x*100+y;
}
public boolean equals(Object o) {
	if(o==null)
		return false;
	if(o instanceof Position) {
		Position p = (Position) o;
		return x==p.x&&y==p.y;
	}
	return false;
}
}
