
package Item;

import java.util.Random;

public class Wand extends Item {
private DiceRoller d;
	
	
	public Wand() {
		this.boardName = '/';
		this.getWand();
		d = new DiceRoller();
	}
	
	public int generateWandNumber() {
		int num = 0;
		Random rnd = new Random(); 
		num = rnd.nextInt(100);	
		if (num < 12) 
		{
			return 1;
		}
		else if (num < 18 && num >= 12) 
		{
			return 2;
		}
		else if (num < 27 && num >= 18) 
		{
			return 3;
		}
		else if (num < 36 && num >= 27)
		{
			return 4;
		}
		else if (num < 46 && num >= 36)
		{
			return 5;
		}
		else if (num < 49 && num >= 46)
		{
			return 6;
		}
		else if (num < 52 && num >= 49)
		{
			return 7;
		}
		else if (num < 55 && num >= 52)
		{
			return 8;
		}
		else if (num < 70 && num >= 55)
		{
			return 9;
		}
		else if (num < 81 && num >= 70)
		{
			return 10;
		}
		else if (num < 86 && num >= 81)
		{
			return 11;
		}
		else if (num < 92 && num >= 86)
		{
			return 12;
		}
		else if (num < 98 && num >= 92)
		{
			return 13;
		}
		else {
			return 14;
		}
	}
	

	
		public void getWand()
		{
			int num = generateWandNumber();
			switch (num) {
			case 1: this.name = "Light";
//			Light	12	Has 10-19 charges. Illuminates the room.
			break;
			
			case 2: this.name = "Invisability";
			this.monsterInvisable = true;
//			Invisibility	6	Makes a monster invisible.
			break;
			
			
			case 3: this.name = "Drain";
//			Drain life	9	Drains half of the hero's hp, then removes the same amount of health evenly from visible monsters.
			break;
			
			case 4: this.name = "Missle";
	
			this.damage = d.rollDie(4); 
//			Magic missile	10	Inflicts 1d4 damage on a single target.
			break;

			
			case 5: this.name = "Haste";
			this.monsterHaste = true;
//			Haste monster	10	Hastens a monster.
			break;
			
			case 6: this.name = "Lightning";
			this.damage = 6 * d.rollDie(6);
//			Lightning	3	Inflicts 6d6 damage for up to 6 tiles. Bounces off walls.
			break;
			
			case 7: this.name = "Fire"; 
			this.damage = 6 * d.rollDie(6);
//	Fire	3	Inflicts 6d6 damage for up to 6 tiles. Bounces off walls. Dragons are immune.
			break;
			
			case 8: this.name = "Cold";
			this.damage = 6 * d.rollDie(6);
			//			Cold	3	Inflicts 6d6 damage for up to 6 tiles. Bounces off walls. Yetis are immune.
			break;
			
			case 9: this.name = "Polymorph";
			this.polyMorph = true;
			
			break;
			
			case 10: this.name = "Slow monster";
			this.slowMonster = true;
//					Slow monster	11	Slows a monster.
			break;
			
			case 11: this.name = "Cancellation"; 
			this.cancellation = true;
			//					Cancellation	5	Supresses monster's special abilities.
			break;
			
			case 12: this.name = "Teleport away"; 
//					Teleport away	6	Teleports a monster randomly on the map.
			break;
			
			case 13: this.name = "Teleport to";
//					Teleport to	6	Causes the monster to teleport next to the player.
			break;
				
			case 14: this.name = "Nothing";
//			Nothing	1	Doesn't do anything.
			break;
					
			default:
				this.name = "Magic wand!";
				break;
			
			
			}
			
	
		
		
		
		
		}	
}



















