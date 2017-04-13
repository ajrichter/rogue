package Item;

public class Weapon extends Item {
	DiceRoller dice;

	public Weapon() {
		this.boardName= ")";
		this.getWeapon();

	}
	//From https://strategywiki.org/wiki/Rogue/Items

	public void getWeapon()
	{
		dice = new DiceRoller();
		DiceRoller d= new DiceRoller();

		switch (d.rollDie(11)) { 	
	
		case 1: this.name = "Mace";
//		Mace	2d4	
		this.damage = 2 * dice.rollDie(4);
		break;
		
		
		case 2: this.name = "Long Sword";
//		Long sword	3d4	
		this.damage = 3 * dice.rollDie(4);
		break;
		
		case 3: this.name = "Short Bow";
//		Short bow	1d1	
		this.damage = 1; //1d1 damage 
		break;
		
		case 4: this.name = "Arrow";
//		Arrow	2d3	Takes bonuses from equipped short bow.
		this.damage = 2 * dice.rollDie(3);
		break;
		
		case 5: this.name = "Dagger"; 
//		Dagger	1d6
		this.damage = dice.rollDie(6); //1d6 damage  
		break;
		
		
		case 6: this.name = "Spear"; 
//		Spear	2d3	1d6 thrown
		this.damage = 2 * dice.rollDie(3);
		break;
		
		case 7: this.name = "Two-Handed Sword"; 
//		Two handed sword	4d4	
		this.damage = 4* dice.rollDie(4);
		break;
		
		case 8: this.name = "Shuriken";
//		Shuriken	2d4 thrown	
		this.damage = 2 * dice.rollDie(4);
		break;
		
		case 9: this.name = "Dart";  
//		Dart	1d3 thrown	
		this.damage = dice.rollDie(3);
		break;
		
		default:
			this.name = "NOP";
			break;
		}
		
	}	


	


}














