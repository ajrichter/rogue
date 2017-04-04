import java.util.Random;

public class DiceRoller {

	public DiceRoller(){
	}

	public int rollDie(int sides){
		Random rand= new Random();
		return rand.nextInt(sides)+1;
	}
}
