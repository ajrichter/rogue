package Item;

import java.util.Random;

public class Potions extends Item {

	//From https://strategywiki.org/wiki/Rogue/Items	
		public Potions() {
			this.boardName = "!";
			this.getPotion();	
		}
		
	
		public int getPotionCase() {
			int num = 0;
			Random rnd = new Random(); 
			num = rnd.nextInt(100);	
			if (num < 7) 
			{
				return 1;
			}
			else if (num < 15 && num >= 7) 
			{
				return 2;
			}
			else if (num < 28 && num >= 15)
			{
				return 3;
			}
			else if (num < 34 && num >= 28)
			{
				return 4;
			}
			else if (num < 42 && num >= 34)
			{
				return 5;
			}
			else if (num < 48 && num >= 42)
			{
				return 6;
			}
			else if (num < 51 && num >= 48)
			{
				return 7;
			}
			else if (num < 64 && num >= 51)
			{
				return 8;
			}
			else if (num  < 69 && num >= 64)
			{
				return 9;
			}
			else if (num < 74 && num >= 69)
			{
				return 10;
			}
			else if (num < 87 && num >= 74)
			{
				return 11;
			}
			else if (num < 92 && num >= 87) 
			{
				return 12;
			}
			else if (num < 98 && num >= 92)
			{
				return 13;
			}
			else if (num < 100 && num >= 98)
			{
				return 14;
			}
			else
			{
				return -1;
			}			
		}
		
		
		public void getPotion()
		{
			int num = getPotionCase();
			switch (num)
			{
			case 1: this.name = "Confusion";
			break;
//			Confusion	7	Confuses the player for 19-21 turns
			case 2: this.name = "Poison";
//			Poison	8	Reduces strength by 1-3 points.
			break;
			
			case 3: this.name = "Healing";
//			Healing	13	Heals 1df per character level. Increase max HP by 1 if you are at full health.
			break;
			
			case 4: this.name = "Monster Detection";
//			Monster detection	6	Reveals monsters on the map.
			break;
			
			case 5: this.name = "Hallucination";
//			Hallucination	8	Causes hallucinations for 850 turns - can't recognize monsters or items
			break;
			
			case 6: this.name = "Levitation";
//			Levitation	6	Levitates for 29-32 turns
			break;
			
			case 7: this.name = "See Invisiable";
//			See invisible	3	This potion tastes like slime mold juice. Reveals Phantoms.
			break;
			
			case 8: this.name = "Gain Strength";
//			Gain strength	13	Increases strength by 1.
			break;
			
			case 9: this.name = "Extra Healing";
//			Extra healing	5	Heals 1d8 per character level. Increase max HP by 1, or by 2 if you are at full health.
			break;
			
			case 10: this.name = "Haste Self";
//			Haste self	5	Hastens player for 4-8 turns.
			break;
			
			case 11: this.name = "Restore Strength";
			this.potionMessage = "Hey, this tastes great.  It make you feel warm all over.";
//			Restore strength	13	Hey, this tastes great. It make you feel warm all over. Restores strength to maximum.
			break;
			
			case 12: this.name = "Blindness";
			this.potionMessage = "A cloak of darkness falls around you.";
//			Blindness	5	Blinds player for 807-892 turns
			break;
			
			case 13: this.name = "Magic detection";
//			Magic detection	6	Reveals magic items on the map.
			break;
			
			case 14: this.name = "Raise level"; 
//					Raise level	2	Increases experience level by 1.
			break;		
					
					
			default: this.name = "NOP";
			this.potionMessage = "This potion tastes extremely dull.";
			break;	
			
			}
		}	
	}










