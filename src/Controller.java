import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Controller extends JFrame implements KeyListener {
	
    private JLabel label;
    private GamePlay game;
    private RougeView view;
    
    public Controller(String s) {
        super(s);
        JPanel p = new JPanel();
        label = new JLabel("Key Listener!");
        p.add(label);
        add(p);
        addKeyListener(this);
        setSize(200, 100);
        setVisible(true);

    }
    
    public void sendInfo(GamePlay play, RougeView view){
    	this.game=play;
    	this.view=view;
    }
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void keyPressed (KeyEvent e) {
		int key = e.getKeyCode();
		
	    if (key == KeyEvent.VK_H) {
	    	int[] left = {-1,0};
	    	System.out.println("left");
	    	game.move(left);
	    }         
	
	    if (key == KeyEvent.VK_L) {
	        int[] right = {1,0};
	        System.out.println("right");
	        game.move(right);
	    }
	
	    if (key == KeyEvent.VK_J) {
	        int[] up = {0,1};
	        System.out.println("up");
	        game.move(up);
	    }
	
	    if (key == KeyEvent.VK_K) {
	        int[] down = {0,-1};
	        System.out.println("down");
	        game.move(down);
	    }
	    
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
}