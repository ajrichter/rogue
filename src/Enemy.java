import java.util.concurrent.ThreadLocalRandom;

/*
 * Missing Enemy Generation by difficulty level
 */

public class Enemy extends Unit{

	//special ability
	//boolean[] flags
	private double treasureChance;
	private int trs, expGained,  dmg;

	public Enemy() {
		super();
		this.genMon(2, 20);
	}
	
	/*
	 * https://nethackwiki.com/wiki/Rogue_(game)
	 * 
		Refer to this for information
		http://www.the-spoiler.com/ADVENTURE/Computer.systems/rogue.2.html
		
		Footnote: setStat(Name, Symbol, Level, Armor, XP, TrsChance) as of 4/13/17
					health is automatically set in the method
	*/
	private void genMon(int lvl, int pl){
		DiceRoller d= new DiceRoller();
		switch (d.roll(1, 6)) {
		   case 1: 	setStat("Aquator", 'A', 5, 2, 20, 0);
					//this.dmg = 0;
					break;
			case 2: setStat("Bat", 'B', 1, 3, 1, 0);
					//df was 3
					//this.dmg = d.roll(1,2);
					break;
			case 3: setStat("Centaur", 'C', 4, 4, 25, 15);
					//df (difficulty) was 11
					//fix this
					//this.dmg = d.roll(1,2) + d.roll(2,5);
					break;
			case 4: setStat("Dragon", 'D', 10, -1, 5000, 100);
					//df was 26
					//i think dmg is not correct, it should be options of attack
					//the monster can use, not all of it combined
					//think about it, for 1 attack this dmg is enough to 1 shot player
					//this.dmg = d.roll(2,8) + d.roll(3,10);
					break;
			case 5: setStat("Emu", 'E', 1, 7, 2, 0);
					//this.dmg = d.roll(1,2);
					break;
			case 6: setStat("Venus Flytrap", 'F', 8, 3, 80, 0);
					//dmg of flytrap wasn't specified, but it does lock you down
					//this.dmg = d.roll(1, 2);
					break;
			case 7:	setStat("Griffin", 'G', 13, 2, 2000, 20);
					// 4d3/3d5/4d3 probably attack patterns, make note of it
					//this.dmg = d.roll(4, 3);
					break;
			case 8: setStat("HOBOgoblin", 'H', 1, 5, 3, 0);	//CHANGE THIS LATER LEL
					//this.dmg = d.roll(1, 8);
					break;
			case 9: setStat("Ice Monster", 'I', 1, 9, 15, 0);
					//this.dmg = d.roll(1, 2);
					break;
			case 10:setStat("Jabberwock", 'J', 15, 6, 3000, 70);
					// 2d12/2d4
					//this.dmg = d.roll(2, 12);
					break;
			case 11:setStat("Kestral", 'K', 1, 7, 1, 0);
					//this.dmg = d.roll(1, 4);
					break;
			case 12:setStat("Leprechaun", 'L', 3, 8, 10, 100);
					//Trs is G according to guide, G = 100% GOLD drop, not item
					//this.dmg = d.roll(1, 2);
					break;
			case 13:setStat("Medusa", 'M', 8, 2, 200, 40);
					// 3d4/3d4/2d5
					//this.dmg = d.roll(3, 4);
					break;
			case 14:setStat("Nymph", 'N', 3, 9, 37, 100);
					//this.dmg = 0;
					break;
			case 15:setStat("Orc", 'O', 1, 6, 5, 15);
					//this.dmg = d.roll(1, 8);
					break;
			case 16:setStat("Phantom", 'P', 8, 2, 120, 0);
					//this.dmg = d.roll(4, 4);
					break;
			case 17:setStat("Quagga", 'Q', 3, 2, 32, 30);
					// 1d2/1d2/1d4
					//this.dmg = d.roll(1, 2);
					break;
			case 18:setStat("Rattlesnake", 'R', 2, 8, 9, 0);
					//this.dmg = d.roll(1, 6);
					break;
			case 19:setStat("Snake", 'S', 1, 8, 1, 0);
					//this.dmg = d.roll(1, 3);
					break;
			case 20:setStat("Troll", 'T', 6, 4, 120, 50);
					// 1d8/1d8/2d6
					//this.dmg = d.roll(1, 8);
					break;
			case 21:setStat("Ur-Vile", 'U', 7, -2, 190, 0);
					// 1d3/1d3/1d3/4d6
					//this.dmg = d.roll(1, 3);
					break;
			case 22:setStat("Vampire", 'V', 8, 1, 350, 20);
					//this.dmg = d.roll(1, 10);
					break;
			case 23:setStat("Wraith", 'W', 5, 4, 55, 0);
					//this.dmg = d.roll(1, 6);
					break;
			case 24:setStat("Xeroc", 'X', 7, 7, 100, 30);
					//this.dmg = d.roll(3, 4);
					break;
			case 25:setStat("Yeti", 'Y', 4, 6, 50, 30);
					// 1d6/1d6 ???? wtf is this, need to understand the atk pattern better
					//this.dmg = d.roll(1, 6);
					break;
			case 26:setStat("Zombie", 'Z', 2, 8, 6, 0);
					//this.dmg = d.roll(1, 8);
					break;
		    default:setStat("Zombie", 'Z', 2, 8, 6, 0);
					//this.dmg = d.roll(1, 8);
				    break;
		}

		if(this.level > ((lvl + pl) /2))
			this.genMon(30, 30);
	}
	
	/**
	 * Each enemy have different attack patterns
	 * I would suggest that we create a dmg[] array for enemies with more than one attack pattern
	 * and when we're getting the damage just have it cycle through each move
	 *  - this will be done either randomly or in order
	 */
	public int getDMG(){
		DiceRoller d= new DiceRoller();
		int chance = -1;
		switch (this.val) {
			case 'A':
				chance = ThreadLocalRandom.current().nextInt(0, 1 + 1);
				if(chance == 0) {
					this.dmg = 0;
				} else {
					this.dmg = 0;	//+ status effect
				}
				 break;
		 	case 'B':
				 this.dmg = d.roll(1,2);
				 break;
		 	 case 'C':
		 		 chance = ThreadLocalRandom.current().nextInt(0, 2 + 1);
		 		 if(chance == 0) {
		 			this.dmg = d.roll(1,2);
		 		 } else if(chance == 1) {
		 			this.dmg = d.roll(1,5);
		 		 } else if(chance == 2) {
		 			this.dmg = d.roll(1,5);	//+ status effect?
		 		 }
				 break;
		 	 case 'D':
		 		 chance = ThreadLocalRandom.current().nextInt(0, 2 + 1);
		 		 if(chance == 0) {
		 			this.dmg = d.roll(1,8);
		 		 } else if(chance == 1) {
		 			this.dmg = d.roll(1,8);
		 		 } else if(chance == 2) {
		 			this.dmg = d.roll(3,10);	
		 		 }
		 		 
				 break;
		 	 case 'E':
				 this.dmg = d.roll(1,2);
				 break;
		 	 case 'F':
		 		 this.dmg = 0;	//SPECIAL CASE, NOT SURE IF IT'S REALLY 0
		 		 break;
		 	 case 'G':
		 		chance = ThreadLocalRandom.current().nextInt(0, 1 + 1);
				if(chance == 0) {
					this.dmg = d.roll(4,3);
				} else {
					this.dmg = d.roll(3,5);	
				}
		 		 break;
		 	 case 'H':
		 		 this.dmg = d.roll(1,8);
		 		 break;
		 	 case 'I':
		 		 this.dmg = 0; //+status effect (apparently always freezes)
		 		 break;
		 	 case 'J':
		 		chance = ThreadLocalRandom.current().nextInt(0, 1 + 1);
				if(chance == 0) {
					this.dmg = d.roll(2, 12);
				} else {
					this.dmg = d.roll(2, 4);	//+ status effect
				}
		 		 break;
		 	 case 'K':
		 		 this.dmg = d.roll(1, 4);
		 		 break;
		 	 case 'L':
		 		 this.dmg = d.roll(1, 1); //+ steals gold, apparently always steal
		 		 break;
		 	 case 'M':
		 		chance = ThreadLocalRandom.current().nextInt(0, 2 + 1);
		 		 if(chance == 0) {
		 			this.dmg = d.roll(3,4);
		 		 } else if(chance == 1) {
		 			this.dmg = d.roll(3,4);	//+ status effect?
		 		 } else if(chance == 2) {
		 			this.dmg = d.roll(2,5);	//+ status effect too?
		 		 }
		 		 break;
		 	 case 'N':
		 		 this.dmg = 0;
		 		 break;
		 	 case 'O':
		 		 this.dmg = d.roll(1,8);
		 		 break;
		 	 case 'P':
		 		 this.dmg = d.roll(4, 4);
		 		 break;
		 	 case 'Q':
		 		chance = ThreadLocalRandom.current().nextInt(0, 1 + 1);
				if(chance == 0) {
					this.dmg = d.roll(1, 5);
				} else {
					this.dmg = d.roll(1, 5);	//no status effect apparently
				}
		 		 break;
		 	 case 'R':
		 		 this.dmg = d.roll(1, 6); //+ status effect - reduce strength
		 		 break;
		 	 case 'S':
		 		 this.dmg = d.roll(1,3);
		 		 break;
		 	 case 'T': 
		 		 chance = ThreadLocalRandom.current().nextInt(0, 2 + 1);
		 		 if(chance == 0) {
		 			this.dmg = d.roll(1, 8);
		 		 } else if(chance == 1) {
		 			this.dmg = d.roll(1, 8);	
		 		 } else if(chance == 2) {
		 			this.dmg = d.roll(2, 6);
		 		 }
		 		 break;
		 	 case 'U':
		 		 chance = ThreadLocalRandom.current().nextInt(0, 2 + 1);
		 		 if(chance == 0) {
		 			this.dmg = d.roll(1, 9);
		 		 } else if(chance == 1) {
		 			this.dmg = d.roll(1, 9);	
		 		 } else if(chance == 2) {
		 			this.dmg = d.roll(2, 9);
		 		 }
		 		 break;
		 	 case 'V':
		 		 this.dmg = d.roll(1, 10); //+drains maxHP
		 		 break;
		 	 case 'W':
		 		 this.dmg = d.roll(1,6); //+drains exp
		 		 break;
		 	 case 'X':
		 		 this.dmg = d.roll(4, 4); //imitates object
		 		 break;
		 	 case 'Y':
		 		 this.dmg = d.roll(1,6);
		 		 break;
		 	 case 'Z':
		 		 this.dmg = d.roll(1, 8);
		 		 break;
		 	 default:
				 this.dmg = d.roll(1,2);
				 break;
	 	 }
		return this.dmg;
	}

	/**
	 * Updated on: 4/13/17
	 * 
	 * Things to add:
	 * 	flag/type
	 *	armor
	 *	loot item (gold for leprechaun)
	 *	range
	 */
	public void setStat(String nme, char symb, int lvl, int arm, int exp, int treas) {
		DiceRoller d = new DiceRoller();
		
		this.name = nme;
		this.val = symb;
		this.hp = d.roll(lvl, 8);
		this.level = lvl;
		this.armor = arm;
		//this.df = lvl;	//remove this later, added here in case other areas use df
		this.xp = exp;
		this.trs = treas;
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
