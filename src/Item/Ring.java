<<<<<<< HEAD

package Item;

import java.util.Random;

public class Ring extends Item {
	
		
		public Ring() {
			this.boardName = 'R';
			this.getRing();
		}
	
		public int getRingCase() {
			int num = 0;
			Random rnd = new Random(); 
			num = rnd.nextInt(100);	
			if (num < 9)
			{
				return 1;
			}
			else if (num < 18 && num >= 9)
			{
				return 2;
			}
			
			else if (num < 23 && num >= 18)
			{
				return 3;
			}
			
			else if (num < 33 && num >= 23)
			{
				return 4;
			}

			else if (num < 43 && num >= 33) 
			{
				return 5;
			}
		
			else if (num < 44 && num >= 43)
			{
				return 6;
			}
			
			else if (num < 54 && num >= 44)
			{
				return 7;
			}
			
			else if (num < 62 && num >= 54)
			{
				return 8;
			}
			
			else if (num < 70 && num >= 62) 
			{
				return 9;
			}
			
			else if (num < 74 && num >= 70)
			{
				return 10;
			}
			
			else if (num < 83 && num >= 74)
			{
				return 11;
			}
			
			else if (num < 88 && num >= 83)
			{
				return 12;
			}
			
			else if (num < 95 && num >= 88)
			{
				return 13;
			}
			
			else if (num < 100 && num >= 95)
			{
				return 14;
			}
			
			else {
				return -1;
			}
			
		}
		public void getRing()
		{
			int num = getRingCase();

			switch (num)
			{
			case 1: this.name = "Protection";
//			protection	9	Adds to defense and magical saving throws
			break;
			
			case 2: this.name = "Strength";
//			Add strength	9	Adds to strength
			break;
			
			case 3: this.name = "Sustain Strength";
//			Sustain strength	5	Prevents poison from reducing strength
			break;
			
			case 4: this.name = "Searching";
//			Searching	10	Helps detect secret doors and traps.
			break;
			
			case 5: this.name = "See invisible"; 
//			See invisible	10	Reveals Phantoms.
			break;
			
			case 6: this.name = "Adornment";
//			Adornment	1	Worth 10 gold.
			break;
			
			case 7: this.name = "Aggravate monster";
//			Aggravate monster	10	Cursed. Causes monsters to attack more agressively.
			break;
			
			case 8: this.name = "Dexterity";
//			Dexterity	8	Improves weapon accurracy.
			break;
			
			case 9: this.name = "Increase damage";
//			Increase damage	8	Increases weapon damage.
			break;
			
			case 10: this.name = "Regeneration";
//			Regeneration	4	Heals 1 hp per turn
			break;
			
			case 11: this.name = "Slow digestion";
//			Slow digestion	9	Reduces food consumption by about 50%. Two rings can cancel out food consumption.
			break;
			
			case 12: this.name = "Teleportation";
//			Teleportation	5	Cursed. Randomly teleports the player around the map.
			break;
			
			case 13: this.name = "Stealth";
//			Stealth	7	Allows player to move without rousing sleeping monsters.
			break;
			
			case 14: this.name = "Maintain armor";
//			Maintain armor	5	Prevents rust damage
			break;
			
			
			default: this.name = "Odd Ring";
			break;
			

		
		
		}	
			
}
}

=======
package Item;


import java.util.Random;

public class Ring extends Item {
	
		
		public Ring() {
			this.boardName = "%";
			this.getRing();
		}
	
		public int getRingCase() {
			int num = 0;
			Random rnd = new Random(); 
			num = rnd.nextInt(100);	
			if (num < 9)
			{
				return 1;
			}
			else if (num < 18 && num >= 9)
			{
				return 2;
			}
			
			else if (num < 23 && num >= 18)
			{
				return 3;
			}
			
			else if (num < 33 && num >= 23)
			{
				return 4;
			}

			else if (num < 43 && num >= 33) 
			{
				return 5;
			}
		
			else if (num < 44 && num >= 43)
			{
				return 6;
			}
			
			else if (num < 54 && num >= 44)
			{
				return 7;
			}
			
			else if (num < 62 && num >= 54)
			{
				return 8;
			}
			
			else if (num < 70 && num >= 62) 
			{
				return 9;
			}
			
			else if (num < 74 && num >= 70)
			{
				return 10;
			}
			
			else if (num < 83 && num >= 74)
			{
				return 11;
			}
			
			else if (num < 88 && num >= 83)
			{
				return 12;
			}
			
			else if (num < 95 && num >= 88)
			{
				return 13;
			}
			
			else if (num < 100 && num >= 95)
			{
				return 14;
			}
			
			else {
				return -1;
			}
			
		}
		public void getRing()
		{
			int num = getRingCase();

			switch (num)
			{
			case 1: this.name = "Protection";
//			protection	9	Adds to defense and magical saving throws
			break;
			
			case 2: this.name = "Strength";
//			Add strength	9	Adds to strength
			break;
			
			case 3: this.name = "Sustain Strength";
//			Sustain strength	5	Prevents poison from reducing strength
			break;
			
			case 4: this.name = "Searching";
//			Searching	10	Helps detect secret doors and traps.
			break;
			
			case 5: this.name = "See invisible"; 
//			See invisible	10	Reveals Phantoms.
			break;
			
			case 6: this.name = "Adornment";
//			Adornment	1	Worth 10 gold.
			break;
			
			case 7: this.name = "Aggravate monster";
//			Aggravate monster	10	Cursed. Causes monsters to attack more agressively.
			break;
			
			case 8: this.name = "Dexterity";
//			Dexterity	8	Improves weapon accurracy.
			break;
			
			case 9: this.name = "Increase damage";
//			Increase damage	8	Increases weapon damage.
			break;
			
			case 10: this.name = "Regeneration";
//			Regeneration	4	Heals 1 hp per turn
			break;
			
			case 11: this.name = "Slow digestion";
//			Slow digestion	9	Reduces food consumption by about 50%. Two rings can cancel out food consumption.
			break;
			
			case 12: this.name = "Teleportation";
//			Teleportation	5	Cursed. Randomly teleports the player around the map.
			break;
			
			case 13: this.name = "Stealth";
//			Stealth	7	Allows player to move without rousing sleeping monsters.
			break;
			
			case 14: this.name = "Maintain armor";
//			Maintain armor	5	Prevents rust damage
			break;
			
			
			default: this.name = "Odd Ring";
			break;
			

		
		
		}	
			
}
}
>>>>>>> master
