import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends Unit{
	private int trs,  dmg;
	private DiceRoller d;
	protected char lastChar;
	protected ArrayList<Character> type = new ArrayList<Character>();

	public Enemy(int level, int plevel) {
		super();
		d = new DiceRoller();
		this.genMon(level, plevel);
		this.lastChar = '.';
	}
	
	private void genMon(int lvl, int pl){
		switch (d.roll(1, 26)) {
		   case 1: 	setStat("giant ant", 'A', 5, 2, 20, 0);
		   			this.type.add('M');
					break;
			case 2: setStat("bat", 'B', 1, 3, 1, 0);
					this.type.add('F');
					break;
			case 3: setStat("centaur", 'C', 4, 4, 25, 15);
					this.type.add(' ');
					break;
			case 4: setStat("dragon", 'D', 10, -1, 5000, 100);
					this.type.add('M');
					break;
			case 5: setStat("floating eye", 'E', 1, 7, 2, 0);
					this.type.add('M');
					break;
			case 6: setStat("violet fungi", 'F', 8, 3, 80, 0);
					this.type.add('M');
					break;
			case 7:	setStat("gnome", 'G', 13, 2, 2000, 20);
					this.type.add('R');
					this.type.add('F');
					this.type.add('M');
					break;
			case 8: setStat("hobgoblin", 'H', 1, 5, 3, 0);
					this.type.add('M');
					break;
			case 9: setStat("invisible stalker", 'I', 1, 9, 15, 0);
					this.type.add(' ');
					break;
			case 10:setStat("jabberwock", 'J', 15, 6, 3000, 70);
					this.type.add(' ');
					break;
			case 11:setStat("kobold", 'K', 1, 7, 1, 0);
					this.type.add('F');
					this.type.add('M');
					break;
			case 12:setStat("leprechaun", 'L', 3, 8, 10, 100);
					this.type.add(' ');
					break;
			case 13:setStat("mimic", 'M', 8, 2, 200, 40);
					this.type.add('M');
					break;
			case 14:setStat("nymph", 'N', 3, 9, 37, 100);
					this.type.add(' ');
					break;
			case 15:setStat("orc", 'O', 1, 6, 5, 15);
					this.type.add('G');
					break;
			case 16:setStat("purple worm", 'P', 8, 2, 120, 0);
					this.type.add('I');
					break;
			case 17:setStat("quasit", 'Q', 3, 2, 32, 30);
					this.type.add('M');
					break;
			case 18:setStat("rust monster", 'R', 2, 8, 9, 0);
					this.type.add('M');
					break;
			case 19:setStat("snake", 'S', 1, 8, 1, 0);
					this.type.add('M');
					break;
			case 20:setStat("troll", 'T', 6, 4, 120, 50);
					this.type.add('R');
					this.type.add('M');
					break;
			case 21:setStat("umber hulk", 'U', 7, -2, 190, 0);
					this.type.add('M');
					break;
			case 22:setStat("vampire", 'V', 8, 1, 350, 20);
					this.type.add('R');
					this.type.add('M');
					break;
			case 23:setStat("wraith", 'W', 5, 4, 55, 0);
					this.type.add(' ');
					break;
			case 24:setStat("xorn", 'X', 7, 7, 100, 30);
					this.type.add(' ');
					break;
			case 25:setStat("yeti", 'Y', 4, 6, 50, 30);
					this.type.add(' ');
					break;
		    default:setStat("zombie", 'Z', 2, 8, 6, 0);
					this.type.add('M');
				    break;
		}
		if(this.level > (lvl + pl))
			this.genMon(lvl, pl);
	}
	
	public int getDMG(){
		switch (this.val) {
			case 'A':
			case 'F':
		 	case 'I':
		 	case 'N':
				this.dmg = 0;
				break;
		 	case 'B':
		 	case 'E':
				 this.dmg = d.roll(1,2);
				 break;
		 	 case 'C':
		 		 if(ThreadLocalRandom.current().nextInt(0, 2 + 1) == 0)
		 			this.dmg = d.roll(1,2);
		 		 else
		 			this.dmg = d.roll(1,5);
				 break;
		 	 case 'D':
		 		 if(ThreadLocalRandom.current().nextInt(0, 2 + 1) == 0)
		 			this.dmg = d.roll(3,10);
		 		 else
		 			this.dmg = d.roll(1,8);
				 break;
		 	 case 'G':
				if(ThreadLocalRandom.current().nextInt(0, 1 + 1) == 0) {
					this.dmg = d.roll(4,3);
				} else {
					this.dmg = d.roll(3,5);	
				}
		 		 break;
		 	 case 'H':
		 	 case 'O':
		 	 case 'Z':
		 		 this.dmg = d.roll(1,8);
		 		 break;
		 	 case 'J':
				if(ThreadLocalRandom.current().nextInt(0, 1 + 1) == 0) {
					this.dmg = d.roll(2, 12);
				} else {
					this.dmg = d.roll(2, 4);
				}
		 		 break;
		 	 case 'K':
		 		 this.dmg = d.roll(1, 4);
		 		 break;
		 	 case 'L':
		 		 this.dmg = d.roll(1, 1);
		 		 break;
		 	 case 'M':
		 		 if(ThreadLocalRandom.current().nextInt(0, 2 + 1) == 0)
		 			this.dmg = d.roll(2,5);
		 		 else
		 			this.dmg = d.roll(3,4);
		 		 break;
		 	 case 'P':
		 	 case 'X':
		 		 this.dmg = d.roll(4, 4);
		 		 break;
		 	 case 'Q':
		 		 this.dmg = d.roll(1, 5);
				 break;
		 	 case 'R':
		 	 case 'W':
		 	 case 'Y':
		 		 this.dmg = d.roll(1, 6);
		 		 break;
		 	 case 'S':
		 		 this.dmg = d.roll(1,3);
		 		 break;
		 	 case 'T':
		 		 if(ThreadLocalRandom.current().nextInt(0, 2 + 1) == 0)
		 			this.dmg = d.roll(2, 6);
		 		else
		 			this.dmg = d.roll(1, 8);
		 		 break;
		 	 case 'U':
		 		 if(ThreadLocalRandom.current().nextInt(0, 2 + 1) == 0)
		 			this.dmg = d.roll(2, 9);
		 		 else
		 			this.dmg = d.roll(1, 9);
		 		 break;
		 	 case 'V':
		 		 this.dmg = d.roll(1, 10);
		 		 break;
		 	 default:
				 this.dmg = d.roll(1,2);
				 break;
	 	 }
		return this.dmg;
	}

	/*Things to add:
	 * 	flag/type
	 *		armor
	 *		loot item (gold for leprechaun)
	 *		range
	 */
	private void setStat(String nme, char symb, int lvl, int arm, int exp, int treas) {
		this.name = nme;
		this.val = symb;
		this.level = lvl;
		this.hp = d.roll(lvl, 8);
		this.armor = arm;
		this.xp = exp;
		this.trs = treas;
	}
	
	/*
	 *  How much to drop? 
	 */
	public int dropT(){
		int per = ThreadLocalRandom.current().nextInt(1, 100 + 1);
		if(this.trs <= per)
			return 50;
		return 0;
	}
}