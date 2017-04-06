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
	private int trs, expGained,  dmg;

	public Enemy() {
		//values for prototype
		this.val="E";
		this.name="Slime";
		this.armor=10;
		this.hp=20;
	}

	/*
		Prioritize certain monsters by level.
		Initializes a Monster for Enemy.
	*/
	private void genMon(){
		DiceRoller d= new DiceRoller();
		switch (d.roll(1, 5)) {
		   case 1: this.val = "A"
		   			this.name = "Aquator";
					this.xp = 20;
					this.hp = d.roll(5,8);
					this.dmg = 0;
					this.trs = 0;
					break;
			case 2: this.val = "B"
				   	this.name = "Bat";
					this.xp = 1;
					this.hp = d.roll(1,8);
					this.dmg = d.roll(1,2);
					this.trs = 0;
					break;
			case 3: this.val = "C"
					this.name = "Centaur";
					this.xp = 17;
					this.hp = d.roll(4,8);
					this.dmg = d.roll(1,2) + d.roll(2,5);
					this.trs = 15;
					break;
			case 4: this.val = "D"
					this.name = "Dragon";
					this.xp = 5000;
					this.hp = d.roll(10,8);
					this.dmg = d.roll(2,8) + d.roll(3,10);
					this.trs = 100;
					break;
			case 5: this.val = "E"
					this.name = "Emu";
					this.xp = 2;
					this.hp = d.roll(1,8);
					this.dmg = d.roll(1,2);
					this.trs = 0;
					break;
		    default: this.val = "F"
				    this.name = "Venus Flytrap";
				    this.xp = 80;
				    this.hp = d.roll(8,8);
				    this.dmg = d.roll(1,2);
				    this.trs = 0;
				    break;
		}
	}
	
	// Treasure returned when the enemy dies
	public int dropT(){
		return this.trs;
	}

	// decides to drop an item and returns a random item
	// Should drop treasure
	public Item dropTreasure() {
		Item i= new Item("Amulet of Yendor",null);
		return i;
	}
}
