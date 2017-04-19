import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Item.Item;

//import RougeView.ViewUpdate;

public class Controller extends JFrame implements KeyListener {
	
    private JLabel label;
    private GamePlay game;
    private RougeView view;
    public static JFrame frame;
    public boolean narration;
    private Item [] items;
    
    public Controller(String s) {
        super(s);
        System.out.println("Controller Constructor called");
        /*JPanel p = new JPanel();
        label = new JLabel("Key Listener!");
        p.add(label);
        add(p);
        addKeyListener(this);
        setSize(200, 100);
        setVisible(true);
        */
        
        narration = false;
        frame= new JFrame();
		frame.setVisible(true);
		frame.setSize(200, 100);
		Graphics gr = frame.getGraphics();
		frame.addKeyListener(this);
        
        //initialize variables
        this.view = new RougeView(frame);
        this.game = new GamePlay(view);
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
			if (key == KeyEvent.VK_SLASH) {
				//Tells what the next character you type is
			}
		
			if (key == KeyEvent.VK_SLASH) {
				//Tells what the next character you type is
			}
		
			if (key == KeyEvent.VK_H || key == KeyEvent.VK_LEFT) {
				int[] left = {-1,0};
				System.out.println("left");
				narration = game.move(left);
			}         
	
			if (key == KeyEvent.VK_L) {
				int[] right = {1,0};
				System.out.println("right");
				narration = game.move(right);
			}
			
			if (key == KeyEvent.VK_J) {
				int[] down = {0,1};
				System.out.println("down");
				narration = game.move(down);
			}
			
			if (key == KeyEvent.VK_K) {
				int[] up = {0,-1};
				System.out.println("up");
				narration = game.move(up);
			}
	    
			if (key == KeyEvent.VK_Y) {
				//moves diagonally left and up
				int[] leftup = {-1,1};
				System.out.println("leftup");
				narration = game.move(leftup);
			}         
	
			if (key == KeyEvent.VK_U) {
				//moves diagonally right and up
				int[] rightup = {1,1};
				System.out.println("rightup");
				narration = game.move(rightup);
			}
	
			if (key == KeyEvent.VK_B) {
				//moves diagonally left and down
				int[] leftdown = {-1,-1};
				System.out.println("leftdown");
				narration = game.move(leftdown);
			}
	
			if (key == KeyEvent.VK_N) {
				//moves diagonally right and down
				int[] rightdown = {1,-1};
				System.out.println("rightdown");
				narration = game.move(rightdown);
			}
	    
			if (key == KeyEvent.VK_T) {
				//Throws an object in the next specified direction
			}
	    
			if (key == KeyEvent.VK_F) {
				//Fights until someone dies
				//Given a direction, fight the enemy in that direction
			}
	    
			if (key == KeyEvent.VK_M) {
				//Move onto something without picking it up
				//must be given a direction after and only moves one step without picking it up
			}
	    
			if (key == KeyEvent.VK_Z) {
				//uses a wand or stave, but must be pointed in a direction
			}
		}
	    
	}
	
	public void keyTyped(KeyEvent e) {
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