import java.util.Random;

/*
	Has timers for different things
	Health goes up with each Move
	Move method is for health increase and stats decrease
	Level calls player.Move
*/

public class Player extends Unit{
	private boolean hasA;
	private boolean hasW;
	private int gold;
	private int xp;
	private int nexp;
	private int steps;

	//Hunger
	//private Inventory inventory;
	//curRings

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


		// unnecessary just genrate a random #
		DiceRoller d6= new DiceRoller();
		// return rand.nextInt(sides)+1;
		// maybe a method in here. But not a class!

		//random
		this.strength= d6.rollDie(6)+d6.rollDie(6)+d6.rollDie(6);
		this.maxHP= d6.rollDie(6)+ d6.rollDie(6)+d6.rollDie(6)+d6.rollDie(6);
		this.hp=this.maxHP;
	}

	public String[] playerStats(){
		String[] temp= new String[2];
		temp[0]= "Level: " + this.level + " Hits: " +this.hp+ " ("+this.maxHP+") "+ " Str: "+this.strength+ " (" + this.strength + ") Armor: "+ this.armor+ " Gold: "+ gold + " Exp: " + this.exp;
		return temp;
	}

	/*
		Stats decrease
		Check Hunger
		Check LevelUp
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
	}

	/*
		LevelUps increase
	*/
	public void levelUp() {
		nexp = nexp * 2;
		level++;
	}

	public void checkHunger() {
		// TODO
	}

}
