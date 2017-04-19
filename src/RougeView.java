import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RougeView {

	public static JFrame frame;
	private static ViewUpdate view;
	
	public void updateBoard(char[][] board){
		view.board=board;	
	}
	
	public void updateStats(String[] stats){
		view.stats=stats;
	}
	
	public void updateNaration(String nat){
		view.naration=nat;
	}

	//procceeds after all information has been gathered
	public void nextTurn(){
		frame.repaint();
	}
	
	public RougeView(JFrame sentFrame){
		//width and height of frame
		int x=750;
		int y=750;

		//creates the new painter
		frame= sentFrame;
		frame.setVisible(true);
		Graphics gr = frame.getGraphics();
		
		//creates the new drawer
		this.view = new ViewUpdate();
		frame.add(view);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(x,y);
	}
	
	//does all of the painting
	static class ViewUpdate extends JPanel{
		
		protected char[][] board= new char[0][0];
		protected String naration="";
		protected String[] stats;
		//protected List<Item> inventory;
		protected boolean showInventory; //if the board is showing inventory
		
		
		//Paints the Jframe with different narration, board, and stats
		public void paintComponent(Graphics gr){
			
			super.paintComponent(gr);
			
			this.setBackground(Color.BLACK);
			gr.setColor(Color.WHITE);
			
			//Prints naration
			gr.setFont(new Font("TimesRoman", Font.PLAIN, 26)); 
			gr.drawString(naration,0,25);
			
			//Prints the board
			
			gr.setFont(new Font("monospaced", Font.PLAIN, 12));
			/*
			for(int i=0; i<board.length; i++){
				gr.drawChars(board[i], 0, board[i].length, 45, i*16+125);
			}
			*/
			// gr.setFont(new Font("TimesRoman", Font.PLAIN, 12));
			for(int i=0; i<board.length;i++){
				for(int k=0; k<board[0].length;k++){
					gr.drawString(board[i][k] + "", k*12+10, i*12+125);
				}
			}
			
			
			//prints stats
			gr.setFont(new Font("TimesRoman", Font.PLAIN, 26));
			//gr.drawString(stats[0],0,625);
			gr.drawString("Test String",0,625);
			
		}
		
	}
}
