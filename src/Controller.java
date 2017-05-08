import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Controller extends JFrame implements KeyListener {
	
    private JLabel label;
    private GamePlay game;
    private RougeView view;
    public static JFrame frame;
    public boolean narration;
    private Item [] items;
    private boolean equipItem = false;
    private boolean dropItem = false;
    public Controller(String s) {
        super(s);
        System.out.println("Controller Constructor called");
        
        narration = false;
        frame= new JFrame();
		frame.setSize(1000, 500);
		frame.setMinimumSize(new Dimension(1000,400));
		Graphics gr = frame.getGraphics();
		frame.addKeyListener(this);
		frame.setVisible(true);
        
        //initialize variables
        this.view = new RougeView(frame);
        this.game = new GamePlay();
        view.updateBoard(game.level.getFloor());
        view.nextTurn();
    }
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void keyPressed (KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_SPACE) {
			narration = false;
			view.updateNaration("");
			frame.repaint();
		}
		
		if (narration == false) {
			// System.out.println("key is " + key);
			// Less is 46
			// Shift is 16
			//TODO
			if (key == KeyEvent.VK_SLASH) {
				//Tells what the next character you type is
			} else if (e.isShiftDown() && key == 46) {
				System.out.println("Time to Descend The Stairs!");
				// This should respawn you in a new level
				game.descend();
			} else if (key == KeyEvent.VK_H || key == KeyEvent.VK_LEFT) {
				int[] left = {-1,0};
				System.out.println("left");
				int update = game.move(left);
				updateView(update);
			} else if (key == KeyEvent.VK_L || key == KeyEvent.VK_RIGHT) {
				int[] right = {1,0};
				System.out.println("right");
				int update = game.move(right);
				updateView(update);
			} else if (key == KeyEvent.VK_J || key == KeyEvent.VK_DOWN) {
				int[] down = {0,1};
				System.out.println("down");
				int update = game.move(down);
				updateView(update);
			} else if (key == KeyEvent.VK_K || key == KeyEvent.VK_UP) {
				int[] up = {0,-1};
				System.out.println("up");
				int update = game.move(up);
				updateView(update);
			} else if (key == KeyEvent.VK_Y) {
				//moves diagonally left and up
				int[] leftup = {-1,1};
				System.out.println("leftup");
				int update = game.move(leftup);
				updateView(update);
			} else if (key == KeyEvent.VK_U) {
				//moves diagonally right and up
				int[] rightup = {1,1};
				System.out.println("rightup");
				int update = game.move(rightup);
				updateView(update);
			} else if (key == KeyEvent.VK_B) {
				//moves diagonally left and down
				int[] leftdown = {-1,-1};
				System.out.println("leftdown");
				int update = game.move(leftdown);
				updateView(update);
			} else if (key == KeyEvent.VK_N) {
				//moves diagonally right and down
				int[] rightdown = {1,-1};
				System.out.println("rightdown");
				int update = game.move(rightdown);
				updateView(update);
			}
	    
			//TODO
			if (key == KeyEvent.VK_T) {
				//Throws an object in the next specified direction
			}
	    
			//TODO
			if (key == KeyEvent.VK_F) {
				//Fights until someone dies
				//Given a direction, fight the enemy in that direction
			}
	    
			//TODO
			if (key == KeyEvent.VK_M) {
				//Move onto something without picking it up
				//must be given a direction after and only moves one step without picking it up
			}
	    
			//TODO
			if (key == KeyEvent.VK_Z) {
				//uses a wand or stave, but must be pointed in a direction
			}
			
			//TODO
			if (key == KeyEvent.VK_S) {
				//searches for traps in all adjacent spots
			}
			
			//TODO
			if (key == KeyEvent.VK_GREATER) {
				//climb up staircase
			}
			
			if (key == KeyEvent.VK_PERIOD) {
				//rest, do nothing for the turn
				view.nextTurn();
			}
			
			//TODO
			if (key == KeyEvent.VK_COMMA) {
				//pick up whatever you're standing on if you're standing on something
			}
			
			//TODO
			if (key == KeyEvent.VK_I) {
				int update = game.printInventory();
				updateView(update);
			}
			
			//TODO
			if (key == KeyEvent.VK_L) {
				//Tells you what a single item in your inventory is
			}
			
			//TODO
			if (key == KeyEvent.VK_Q) {
				//quaff one of the potions you're carrying
			}
			
			//TODO
			if (key == KeyEvent.VK_R) {
				//read one of the scrolls in your inventory
			}
			
			//TODO
			if (key == KeyEvent.VK_E) {
				
				equipItem = true;
			}
			
			if (equipItem == true)
			{
				
			if (key == KeyEvent.VK_0)
			{
				int update = game.equipOrConsumeItem(0);
				updateView(update);
			}
			else if (key == KeyEvent.VK_1)
			{
				int update = game.equipOrConsumeItem(1);
				updateView(update);
			}
			else if (key == KeyEvent.VK_2)
			{
				int update = game.equipOrConsumeItem(2);
				updateView(update);
			}
			else if (key == KeyEvent.VK_3)
			{
				int update = game.equipOrConsumeItem(3);
				updateView(update);
			}
			else if (key == KeyEvent.VK_4)
			{
				int update = game.equipOrConsumeItem(4);
				updateView(update);
			}	
			}
				
				
				
				
		
		
			
			
			
			//TODO
			if (key == KeyEvent.VK_W) {
				//replace weapon you are carrying with weapon in your inventory
			}
			
			//TODO
			if (key == KeyEvent.VK_D) {
				dropItem = true;
			}
			
			if (dropItem == true)
			{
			
				if (key == KeyEvent.VK_0)
				{
					int update = game.throwItem(0);
					updateView(update);
				}
				else if (key == KeyEvent.VK_1)
				{
					int update = game.throwItem(1);
					updateView(update);
				}
				else if (key == KeyEvent.VK_2)
				{
					int update = game.throwItem(2);
					updateView(update);
				}
				else if (key == KeyEvent.VK_3)
				{
					int update = game.throwItem(3);
					updateView(update);
				}
				else if (key == KeyEvent.VK_4)
				{
					int update = game.throwItem(4);
					updateView(update);
				}
			
			}
			
			
			
			//TODO
			if (key == KeyEvent.VK_C) {
				//rename an item something
			}
			
			//TODO
			if (key == KeyEvent.VK_O) {
				//print and allow changes to options
			}
			
			//TODO
			if (key == KeyEvent.VK_V) {
				//print program version number
			}
			
			//TODO
			if (key == KeyEvent.VK_RIGHT_PARENTHESIS) {
				//print current weapon name
			}
			
			//TODO
			if (key == KeyEvent.VK_BRACERIGHT) {
				//print current armor name
			}
			
			//TODO
			if (key == KeyEvent.VK_EQUALS) {
				//print current rings names
			}
		}
	    
		view.updateStats(game.play.playerStats());
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	public void updateView(int update) {
		System.out.println(update);
		if(update == 9 || update==1 || update == 3 || update == 4 || update == 5 || update == 6 || update == 7 || update == 10){
			view.updateNaration(game.narration);
			this.dropItem = false;
			this.equipItem =false;
			if(update == 1 || update == 5 || update == 6 || update == 7 || update == 9 || update == 10){
				narration = true;
			} 
		} else {
			view.updateNaration("");
		}
		view.updateBoard(game.level.getFloor());
		view.nextTurn();
	}
	
	public static void main(String[] args) {
		Controller control = new Controller("Rogue");
		String[] dyingnoises = new String[4];
	
		
		//game.saveGame(dyingnoises); 
		
		
		//makes a board
		control.view.updateBoard(control.game.level.getFloor());
		control.view.updateStats(control.game.play.playerStats());
		control.view.nextTurn();
		
		//makes controller
	}
}