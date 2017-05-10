

import java.util.Random;

public class Scrolls extends Item {


	public Scrolls() {
		this.boardName = '?';
		this.getScrolls();
	}


	public int generateNumberScrolls() {
		int num = 0;
		Random rnd = new Random(); 
		num = rnd.nextInt(100);	
		if (num < 7) //Monster confusion 7
		{
			return 1;
		}
		else if (num < 11 && num >= 7) //Magic Mapping 4
		{
			return 2;
		}
		else if (num < 13 && num >= 12) //Hold Monster 2
		{
			return 3;
		}
		else if (num < 16 && num >= 13) //Sleep 3
		{
			return 4;
		}
		else if (num < 23 && num >= 16) //Enchant 7
		{
			return 5;
		}
		else if (num < 33 && num >= 23) //Identify potion 10
		{
			return 6;
		}
		else if (num < 43 && num >= 33) //Identify scroll 10
		{
			return 7;
		}
		else if (num < 49 && num >= 43) //Idetify weapon 6
		{
			return 8;
		}
		else if (num < 56 && num >= 49) //Identify armor 7
		{
			return 9;
		}
		else if (num < 66 && num >= 56) //Identify ring
		{
			return 10;
		}
		else if (num < 69 && num >= 66) //Scare Monster 3
		{
			return 11;
		}
		else if (num < 71 && num >= 69) //Food detection 2
		{
			return 12;
		}
		else if (num < 76 && num >= 71) //Teleportation 5
		{
			return 13;
		}
		else if (num < 84 && num >= 76) //Enchant weapon 8
		{
			return 14;
		}
		else if (num < 88 && num >= 84) //Create monster 4
		{
			return 15;
		}
		else if (num < 95 && num >= 88) //remove curse 7
		{
			return 16;
		}
		else if (num < 98 && num >= 95)
		{
			return 17;
		}
		else if (num < 100 && num >= 98)
		{
			return 18;
		}
		else {
			return -1;
		}

	}



	//From https://strategywiki.org/wiki/Rogue/Items
	public void getScrolls()
	{

		int num = generateNumberScrolls();
	

		switch (num)
		{
		case 1: this.name = "Moster Confusion";
		this.eMessage = "Your hands begin to glow red";
		//		Monster confusion	7	Your hands begin to glow red. Next melee attack confuses a monster.
		break;

		case 2: this.name = "Map";
		this.eMessage = "Oh, now this scroll has a map on it.";
		//		Magic mapping	4	Reveals the entire map; objects are still hidden
		break;


		case 3: this.name = "Hold Monster";
		//		Hold monster	2	Freezes adjecent monsters.
		this.eMessage = "You stay!";
		break;

		case 4: this.name = "Sleep";
		//		Sleep	3	Sleeps reader for 4-8 turns.
		this.eMessage = "You fall asleep";
		break;


		case 5: this.name = "Enchant Armor";
		break;


		case 6: this.name = "Identify Potion";
		//		Identify potion	10	Identifies a potion.
		this.eMessage = "This scroll is an identify scroll";
		break;




		case 7: this.name = "Identify";
		//		Identify scroll	10	Identifies a scroll.
		break;


		case 8: this.name = "Identify Weapon";
		//		Identify weapon	6	Identifies a weapon.
		break;



		case 9: this.name = "Identify Armor";
		//		Identify armor	7	Identifies a suit of armor.
		break;



		case 10: this.name = "Identify Ring, Wand or Staff"; 
		//		Identify ring, wand or staff	10	Identifies a ring, wand or staff.
		break;


		case 11: this.name = "Scare";
		//		Scare monster	3	When read, you hear maniacal laughter in the distance. To properly use the scroll, drop it; monsters will not walk on it.
		this.eMessage = "You hear maniacal laughter in the distance.";
		break;

		case 12: this.name = "Food detection";
		break;


		case 13: this.name = "Teleport";
		//		Teleportation	5	Teleports to a random location on the map.
		this.eMessage = "You are transported";
		break;


		case 14: this.name = "Enchant Weapon";
		//		Enchant weapon	8	Your (weapon) glows blue for a moment. Increases hit or damage bonus by 1.
		break;


		case 15: this.name = "Create";
		//		Create Monster	4	Summons a monster on an adjecent tile. If it fails, you hear a faint cry of anguish in the distance.
		this.eMessage = "You hear a faint cry of anguish in the distance.";
		break;



		case 16: this.name = "Remove";
		//		Remove curse	7	You feel as if somebody is watching over you. Removes curses from equipped items.
		this.eMessage = "You feel as if somebody is watching over you.";
		break;

		case 17: this.name = "Aggravate Monster";
		//		Aggravate monsters	3	You hear a high pitched humming noise.. Alerts all monsters on the map.
		this.eMessage = "You hear a high pitched humming noise.";
		break;

		case 18: this.name = "Protect Armor"; 
		//		Protect armor	2	Your armor is covered by a shimmering gold shield. Prevents rust damage from aquators.
		break;



		default:
			this.name = "cryptography";
			this.eMessage = "What a puzzling scroll!";
			break;
		}

	}	

}





