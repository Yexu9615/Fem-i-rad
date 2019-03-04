package game;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class Window extends JFrame {

	private Chessboard chessboard = new Chessboard();// Îå×ÓÆåÅÌ

	//private Program program = new Program();

	Window() {
		super("Fem-i-rad");
		setLayout(new BorderLayout());
		//JMenuBar mbar = new JMenuBar();
		//setJMenuBar(mbar);
		add(chessboard);
		setSize(555, 575);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		setLocationRelativeTo(null);
		chessboard.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				start(e);
			}
		});
	}

	public static void main(String[] args) {
		new Window();
	}


	public void start(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println(x );
		int controllNumber=Chessboard.MARGIN-Chessboard.CELL_SIZE/2;
		int xLoc=(x-controllNumber)/Chessboard.CELL_SIZE ;
		int yLoc=(y-controllNumber)/Chessboard.CELL_SIZE ;
		if(chessboard.isLegal(xLoc, yLoc)) {
			Position l=new Position(xLoc,yLoc,1);
			chessboard.add(l);
		}
//		if(chessboard.humanWins()) {
//			
//		}
//		Location l=chessboard.searchLocation();
//		chessboard.add(l);
//		if(chessboard.computerWins()) {
//			
//		}
		
	}
}
