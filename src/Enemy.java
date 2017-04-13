import java.awt.Point;

import Item.Item;

/*
	Add next 21 enemies.
*/

public class Enemy extends Unit{

	//special ability
	//boolean[] flags
	private double treasureChance;
	//df = level, why this?? there's already a level for unit right?
	//need to add range in later (range is probably when the monster notices player
	//still need type/flag (special factors, flying, aggresive, etc)
	private int trs, expGained,  dmg, df;

	public Enemy() {
		super();
		this.genMon(2, 20);
	}
	
	/*
		Prioritize certain monsters by level.
		Difficulty has to be implemented.
		
		Refer to this for information
		http://www.the-spoiler.com/ADVENTURE/Computer.systems/rogue.2.html
		
		Footnote: setStat(Name, Symbol, Level, XP, TrsChance) as of 4/13/17
					health is automatically set in the method
	*/
	private void genMon(int lvl, int pl){
		DiceRoller d= new DiceRoller();
		switch (d.roll(1, 6)) {
		   case 1: 	setStat("Aquator", "A", 5, 20, 0);
					this.dmg = 0;
					break;
			case 2: setStat("Bat", "B", 3, 1, 0);
					this.dmg = d.roll(1,2);
					break;
			case 3: setStat("Centaur", "C", 11, 17, 15);
					//fix this
					this.dmg = d.roll(1,2) + d.roll(2,5);
					break;
			case 4: setStat("Dragon", "D", 26, 5000, 100);
					//i think dmg is not correct, it should be options of attack
					//the monster can use, not all of it combined
					//think about it, for 1 attack this dmg is enough to 1 shot player
					this.dmg = d.roll(2,8) + d.roll(3,10);
					break;
			case 5: setStat("Emu", "E", 3, 2, 0);
					this.dmg = d.roll(1,2);
					break;
			case 6: setStat("Venus Flytrap", "F", 10, 80, 0);
					this.dmg = d.roll(1, 2);
					break;
			case 7:	setStat("Griffin", "G", 13, 2000, 20);
					// 4d3/3d5/4d3 probably attack patterns, make note of it
					this.dmg = d.roll(4, 3);
					break;
			case 8: setStat("HOBOgoblin", "H", 1, 3, 0);	//CHANGE THIS LATER LEL
					this.dmg = d.roll(1, 8);
					break;
			case 9: setStat("Ice Monster", "I", 1, 15, 0);
					this.dmg = d.roll(1, 2);
					break;
			case 10:setStat("Jabberwock", "J", 15, 4000, 70);
					// 2d12/2d4
					this.dmg = d.roll(2, 12);
					break;
			case 11:setStat("Kestral", "K", 1, 1, 0);
					this.dmg = d.roll(1, 4);
					break;
			case 12:setStat("Leprechaun", "L", 3, 10, 100);
					//Trs is G according to guide, G = 100% GOLD drop, not item
					this.dmg = d.roll(1, 2);
					break;
			case 13:setStat("Medusa", "M", 8, 200, 40);
					// 3d4/3d4/2d5
					this.dmg = d.roll(3, 4);
					break;
			case 14:setStat("Nymph", "N", 3, 37, 100);
					this.dmg = 0;
					break;
			case 15:setStat("Orc", "O", 1, 5, 15);
					this.dmg = d.roll(1, 8);
					break;
			case 16:setStat("Phantom", "P", 8, 120, 0);
					this.dmg = d.roll(4, 4);
					break;
			case 17:setStat("Quagga", "Q", 3, 32, 30);
					// 1d2/1d2/1d4
					this.dmg = d.roll(1, 2);
					break;
			case 18:setStat("Rattlesnake", "R", 2, 9, 0);
					this.dmg = d.roll(1, 6);
					break;
			case 19:setStat("Snake", "S", 1, 1, 0);
					this.dmg = d.roll(1, 3);
					break;
			case 20:setStat("Troll", "T", 6, 120, 50);
					// 1d8/1d8/2d6
					this.dmg = d.roll(1, 8);
					break;
			case 21:setStat("Ur-Vile", "U", 7, 190, 0);
					// 1d3/1d3/1d3/4d6
					this.dmg = d.roll(1, 3);
					break;
			case 22:setStat("Vampire", "V", 8, 350, 20);
					this.dmg = d.roll(1, 10);
					break;
			case 23:setStat("Wraith", "W", 5, 55, 0);
					this.dmg = d.roll(1, 6);
					break;
			case 24:setStat("Xeroc", "X", 7, 100, 30);
					this.dmg = d.roll(3, 4);
					break;
			case 25:setStat("Yeti", "Y", 4, 50, 30);
					// 1d6/1d6 ???? wtf is this, need to understand the atk pattern better
					this.dmg = d.roll(1, 6);
					break;
			case 26:setStat("Zombie", "Z", 2, 6, 0);
					this.dmg = d.roll(1, 8);
					break;
		    default:setStat("Zombie", "Z", 2, 6, 0);
					this.dmg = d.roll(1, 8);
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

	/**
	 * Updated on: 4/13/17
	 * 
	 * Things to add:
	 * 	flag/type
	 *	armor
	 *	loot item (gold for leprechaun)
	 *	range
	 */
	public void setStat(String nme, String symb, int lvl, int exp, int treas) {
		DiceRoller d = new DiceRoller();
		
		this.val = symb;
		this.name = nme;
		this.hp = d.roll(lvl, 8);
		this.level = lvl;
		this.df = lvl;	//remove this later, added here in case other areas use df
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
