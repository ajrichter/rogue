import java.util.Random;

//Items From https://strategywiki.org/wiki/Rogue/Items
public class Ring extends Item {
	public Ring() {
		this.boardName = '=';
		this.getRing();
	}

	public int getRingCase() {
		int num = 0;
		Random rnd = new Random(); 
		num = rnd.nextInt(100);	
		if (num < 9)
		{
			return 1; //Protection Scroll Probability: 9%
		}
		else if (num < 18 && num >= 9)
		{
			return 2; //Strength Scroll Probability: 9%
		}

		else if (num < 23 && num >= 18)
		{
			return 3;  //Sustain strength Probability: 5%	
		}

		else if (num < 33 && num >= 23)
		{
			return 4; //Searching Probability:	10%
		}

		else if (num < 43 && num >= 33) 
		{
			return 5; //See invisible	10%
		}

		else if (num < 44 && num >= 43)
		{
			return 6; //Adornment 1%
		}

		else if (num < 54 && num >= 44)
		{
			return 7; //Aggravate monster	10%
		}

		else if (num < 62 && num >= 54)
		{
			return 8; //Dexterity	8%
		}

		else if (num < 70 && num >= 62) 
		{
			return 9; //Increase damage	8%
		}

		else if (num < 74 && num >= 70)
		{
			return 10; //Regeneration	4%
		}

		else if (num < 83 && num >= 74)
		{
			return 11; //Slow digestion	9%	
		}

		else if (num < 88 && num >= 83)
		{
			return 12; //Teleportation	5%
		}

		else if (num < 95 && num >= 88)
		{
			return 13; //Stealth	7%
		}

		else if (num < 100 && num >= 95)
		{
			return 14; //Maintain armor	5%
		}

		else {
			return -1;
		}

	}

	public boolean itemsImplemented(int num) //Update this every time you implement a new item!
	{
		if (num == 2 || num == 6 || num == 9 || num == 10)
		{
			return true;
		}
		else {
			return false;
		}
	}

	public int getImplementedItems()
	{
		int num = getRingCase();
		while (!itemsImplemented(num)) {
			num = getRingCase();
		}
		return num;
	}



	public void getRing()
	{


		switch (getImplementedItems())
		{
		case 1: this.name = "Protection"; //Not Implemented 
		//Adds to defense and magical saving throws
		break;

		case 2: this.name = "Strength"; //Implemented
		this.ringStrength = 1;
		//Adds to strength
		break;

		case 3: this.name = "Sustain Strength"; //Not implemented
		//Prevents poison from reducing strength
		break;

		case 4: this.name = "Searching"; //Not implemented
		//Helps detect secret doors and traps.
		break;

		case 5: this.name = "See Invisible"; //Not implemented 
		//See invisible
		break;

		case 6: this.name = "Adornment"; //Implemented
		this.gold = 10;
		break;

		case 7: this.name = "Aggravate Monster"; //Not implemented
		//Aggravate monster	10	Cursed. Causes monsters to attack more agressively.
		break;

		case 8: this.name = "Dexterity"; //Not implemented
		//Dexterity	8	Improves weapon accurracy.
		break;

		case 9: this.name = "Increase Damage"; //Implemented
		this.ringStrength = 1; 
		//Increase damage	8	Increases weapon damage.
		break;

		case 10: this.name = "Regeneration"; //Implemented
		this.hpFromRing = 1;
		//Regeneration	4	Heals 1 hp per turn
		break;

		case 11: this.name = "Slow Digestion"; //Not Implemented
		//Slow digestion	9	Reduces food consumption by about 50%. Two rings can cancel out food consumption.
		break;

		case 12: this.name = "Teleportation"; //Not Implemented
		//Teleportation	5	Cursed. Randomly teleports the player around the map.
		break;

		case 13: this.name = "Stealth"; //Not Implemented
		//Stealth	7	Allows player to move without rousing sleeping monsters.
		break;

		case 14: this.name = "a Maintain Armor"; //Not Implemented
		//Maintain armor	5	Prevents rust damage
		break;


		default: this.name = "Odd Ring";
		break;




		}	

	}
}



