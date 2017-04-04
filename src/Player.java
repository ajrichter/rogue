import java.util.concurrent.ThreadLocalRandom;

/*
	Has timers for different things
	Health goes up with each Move
	Move method is for health increase and stats decrease
	Level calls player.Move
*/

public class Player extends Unit{
	private boolean hasA;
	private boolean hasW;
	private int gold, xp, nexp, steps, hunger;

	//private Inventory inventory;

	public Player(){
		this("John Dooley");
	}

	public Player(String s) {
		 super();
		 // the player-character is an @-symbol
 		this.val = "@";
 		this.name=s;
		this.gold=0;
		this.xp=0;
		this.level=1;
		this.armor=3;
		this.nexp = 20;
		this.steps = 0;
		this.hunger = 150;
		this.strength = ThreadLocalRandom.current().nextInt(5, 20 + 1);
		this.maxHP = ThreadLocalRandom.current().nextInt(10, 30 + 1);
		this.hp=this.maxHP;
		/*
			Proper way of generating random numbers.
			public int nextInt(int origin, int bound)
			Returns a pseudorandom int value between the specified origin (inclusive)
			and the specified bound (exclusive).
			int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		*/
	}

	public String[] playerStats(){
		String[] temp= new String[2];
		temp[0]= "Level: " + this.level + " Hits: " +this.hp+ " ("+this.maxHP+") "+
		" Str: "+this.strength+ " (" + this.strength + ") Armor: "+ this.armor+
		 " Gold: "+ gold + " Exp: " + this.exp;
		return temp;
	}

	/*
		Regenerate HP
		Check LevelUp
		Check Hunger

		Stats decrease
	*/
	public int move(){
		steps++;
		if(steps >= 10) {
			steps = 0;
			if(hp < maxHP)
				hp++;
		}

		if(xp == nexp)
			levelUp();

		hunger--;
		chkHu();
	}

	public void levelUp() {
		nexp = nexp * 2;
		level++;
	}

	public void chkHu() {
		if(hunger <= 50)
			System.out.println("You should probably stop by the Gizmo.");
		else if(hunger <= 25)
			System.out.println("You are starving!");
		else if(hunger <= 0)
			System.out.println("You have fainted.");
	}
}
