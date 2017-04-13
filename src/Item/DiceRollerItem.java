package Item;
import java.util.Random;

public class DiceRollerItem {
	
	public int rollDie(int sides){
		Random rand= new Random();
		return rand.nextInt(sides)+1;
	}
	
	
}
