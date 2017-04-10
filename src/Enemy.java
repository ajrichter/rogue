import Item.Item;

/*
	Create the different enemies
	Start with 5 enemies.
	addMakeEnemy() Method which randomly generates an Enemy
*/

public class Enemy extends Unit{

	//special ability
	//boolean[] flags
	private double treasureChance;
	private int trs, expGained,  dmg, df;

	public Enemy() {
		super();
		this.genMon(2, 20);
	}

	/*
		Prioritize certain monsters by level.
		Difficulty just has to be implemented, as usual
	*/
	private void genMon(int lvl, int pl){
		DiceRoller d= new DiceRoller();
		switch (d.roll(1, 6)) {
		   case 1: this.val = "A";
		   			this.name = "aquator";
					this.xp = 20;
					this.hp = d.roll(5,8);
					this.dmg = 0;
					this.trs = 0;
					this.df = 5;
					break;
			case 2: this.val = "B";
				   	this.name = "bat";
					this.xp = 1;
					this.hp = d.roll(1,8);
					this.dmg = d.roll(1,2);
					this.trs = 0;
					this.df = 3;
					break;
			case 3: this.val = "C";
					this.name = "centaur";
					this.xp = 17;
					this.hp = d.roll(4,8);
					this.dmg = d.roll(1,2) + d.roll(2,5);
					this.trs = 15;
					this.df = 11;
					break;
			case 4: this.val = "D";
					this.name = "dragon";
					this.xp = 5000;
					this.hp = d.roll(10,8);
					this.dmg = d.roll(2,8) + d.roll(3,10);
					this.trs = 100;
					this.df = 26;
					break;
			case 5: this.val = "E";
					this.name = "emu";
					this.xp = 2;
					this.hp = d.roll(1,8);
					this.dmg = d.roll(1,2);
					this.trs = 0;
					this.df = 3;
					break;
		    default: this.val = "F";
				    this.name = "Venus Flytrap";
				    this.xp = 80;
				    this.hp = d.roll(8,8);
				    this.dmg = d.roll(1,2);
				    this.trs = 0;
					this.df = 10;
				    break;
		}

		if(this.df > ((lvl + pl) /2))
			this.genMon(10, 20);
	}

	public int getDMG(){
		DiceRoller d= new DiceRoller();
		switch (this.val) {
			case "A":
				this.dmg = 0;
				 break;
		 	case "B":
				 this.dmg = d.roll(1,2);
				 break;
		 	 case "C":
				 this.dmg = d.roll(1,2) + d.roll(2,5);
				 break;
		 	 case "D":
				 this.dmg = d.roll(2,8) + d.roll(3,10);
				 break;
		 	 case "E":
				 this.dmg = d.roll(1,2);
				 break;
		 	 default:
				 this.dmg = d.roll(1,2);
				 break;
	 	 }
		return this.dmg;
	}

	// Treasure returned when the enemy dies
	public int dropT(){
		return this.trs;
	}

	// decides to drop an item and returns a random item
	// Should drop treasure
	public Item dropTreasure() {
		Item i= new Item();
		return i;
	}
}
