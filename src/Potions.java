

import java.util.Random;

public class Potions extends Item {

	//From https://strategywiki.org/wiki/Rogue/Items	
	public Potions() {
		this.boardName = '!';
		this.getPotion();
	}


	public int getPotionCase() {
		int num = 0;
		Random rnd = new Random(); 
		num = rnd.nextInt(100);	
		if (num < 7) 
		{
			return 1; //Confusion	7% chance
		}
		else if (num < 15 && num >= 7) 
		{
			return 2; //Poison	8% chance
		}
		else if (num < 28 && num >= 15)
		{
			return 3; //Healing	13% chance
		}
		else if (num < 34 && num >= 28)
		{
			return 4; //Monster detection	6% chance
		}
		else if (num < 42 && num >= 34)
		{
			return 5; //Hallucination	8% chance
		}
		else if (num < 48 && num >= 42)
		{
			return 6;	//Levitation	6% chance
		}
		else if (num < 51 && num >= 48)
		{
			return 7;  //See invisible	3% chance
		}
		else if (num < 64 && num >= 51)
		{
			return 8; //Gain strength	13% chance
		}
		else if (num  < 69 && num >= 64)
		{
			return 9; //Extra healing	5% chance
		}
		else if (num < 74 && num >= 69)
		{
			return 10; //Haste self	5% chance
		}
		else if (num < 87 && num >= 74)
		{
			return 11; //Restore strength	13% chance
		}
		else if (num < 92 && num >= 87) 
		{
			return 12; //Blindness	5% chance
		}
		else if (num < 98 && num >= 92)
		{
			return 13; //Magic detection	6% chance
		}
		else if (num < 100 && num >= 98)
		{
			return 14; //Raise level	2% chance
		}
		else
		{
			return -1;
		}			
	}

	public boolean methodsImplemented(int num) //Update this every time you implement a new item!
	{		
		if (num == 1 || num == 2 || num == 3 || num == 8 || num == 9 ||  num == 11 || num == 14)
		{
			return true;
		}
		else {
			return false;
		}
	}

	public int getImplementedItems()
	{
		int num = getPotionCase();
		while (!methodsImplemented(num)) {
			num = getPotionCase();
		}
		return num;
	}


	public void getPotion()
	{
		int num = getImplementedItems();
		switch (num)
		{
		case 1: this.name = "Confusion"; //Implemented
		this.eMessage = "Wait, what's going on here. Huh? What? Who?";
		//Confusion	7	Confuses the player for 19-21 turns
		break;

		case 2: this.name = "Poison"; //Implemented
		this.eMessage = "You feel very sick now.";
		DiceRoller loseS = new DiceRoller();
		int loseAmount = loseS.rollDie(3);
		this.potionStrength = -loseAmount;
		//Poison	8	Reduces strength by 1-3 points.
		break;

		case 3: this.name = "Healing"; //Implemented
		this.healing = true;
		this.eMessage = "You begin to feel better.";
		DiceRoller gainHP = new DiceRoller();
		int hpGain = gainHP.rollDie(5);
		this.hpFromPotion = hpGain;
		//Healing	13	Heals 1df per character level. Increase max HP by 1 if you are at full health.
		break;

		case 4: this.name = "Monster Detection"; //Not Implemented
		this.eMessage = "You begin to sense the presence of monsters";
		//	Monster detection	6	Reveals monsters on the map.
		break;

		case 5: this.name = "Hallucination"; //Not Implemented
		//this.hallucination = true;
		this.eMessage = "Is this the real life? Or is this just fantasy?";
		//Hallucination	8	Causes hallucinations for 850 turns - can't recognize monsters or items
		//Only works for enemies, not items
		break;

		case 6: this.name = "Levitation"; //Not implemented
		//Levitation	6
		//The enemy will be 10% less likely to hit the player
		this.eMessage = "You're flying!";
		break;

		case 7: this.name = "See Invisiable"; //Not Implemented
		this.eMessage = "This potion tastes like slime mold juice.";
		//			See invisible	3	This potion tastes like slime mold juice. Reveals Phantoms.
		break;

		case 8: this.name = "Gain Strength"; //Implemented
		this.eMessage = "You feel stronger, now.  What bulging muscles!";
		this.potionStrength = 1;
		//Gain strength	13	Increases strength by 1.
		break;

		case 9: this.name = "Extra Healing"; //Implemented
		this.extraHealing = true;
		this.eMessage = "You begin to feel much better.";
		DiceRoller extraHP = new DiceRoller();
		int hpExtra = extraHP.rollDie(8);
		this.hpFromPotion = hpExtra;
		//Extra healing	5	Heals 1d8 per character level. Increase max HP by 1, or by 2 if you are at full health.
		break;

		case 10: this.name = "Haste Self"; //Not Implemented
		this.eMessage = "You feel yourself moving much faster.";
		//this.hasteSelf = true;
		//Haste self	5	Hastens player for 4-8 turns.
		break;

		case 11: this.name = "Restore Strength"; //Implemented
		this.eMessage = "Hey, this tastes great.  It make you feel warm all over.";
		this.restoreStrength = true;
		//Restore strength	13	Hey, this tastes great. It make you feel warm all over. Restores strength to maximum.
		break;

		case 12: this.name = "Blindness"; //Not Implemented
		this.eMessage = "A cloak of darkness falls around you.";
		//this.blindness = true;
		//Blindness	5	Blinds player for 807-892 turns
		break;

		case 13: this.name = "Magic Detection"; //Not Implemented
		this.eMessage = "You can detect scrolls, wands, and potions on the map.";	
		//Magic detection	6	Reveals magic items on the map.
		break;		

		case 14: this.name = "Raise Level"; //Implemented
		this.eMessage = "You suddenly feel much more skillful";
		this.playerXP = 1; 
		//Raise level	2	Increases experience level by 1.

		break;

		default: this.name = "NOP";
		this.eMessage = "This potion tastes extremely dull.";
		break;	

		}
	}	
}
















