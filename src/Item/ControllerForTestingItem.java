package Item;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class ControllerForTestingItem extends JFrame implements KeyListener {
	
    private JLabel label;
    private mainForTestingItem game;
    private RougeViewItem view;
    
    public ControllerForTestingItem(String s) {
        super(s);
        JPanel p = new JPanel();
        label = new JLabel("Key Listener!");
        p.add(label);
        add(p);
        addKeyListener(this);
        setSize(200, 100);
        setVisible(true);

    }
    
    public void sendInfo(mainForTestingItem play, RougeViewItem view){
    	this.game=play;
    	this.view=view;
    }
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void keyPressed (KeyEvent e) {
		int key = e.getKeyCode();
		
		
		if (key == KeyEvent.VK_SLASH) {
	        //Tells what the next character you type is
	    }
		
		if (key == KeyEvent.VK_SLASH) {
	        //Tells what the next character you type is
	    }
		
	    if (key == KeyEvent.VK_H) {
	    	int[] left = {-1,0};
	    	game.move(left);
	    }         
	
	    if (key == KeyEvent.VK_L) {
	        int[] right = {1,0};
	        game.move(right);
	    }
	
	    if (key == KeyEvent.VK_J) {
	        int[] up = {0,1};
	        game.move(up);
	    }
	
	    if (key == KeyEvent.VK_K) {
	        int[] down = {0,-1};
	        game.move(down);
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
	
	public void keyTyped(KeyEvent e) {
	}
	
}