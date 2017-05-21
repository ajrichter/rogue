

public class Weapon extends Item {
	DiceRoller dice;

	public Weapon() {
		this.boardName= ')';
		this.getWeapon();

	}
	//From https://strategywiki.org/wiki/Rogue/Items

	public void getWeapon()
	{
		dice = new DiceRoller();
		DiceRoller d= new DiceRoller();

		switch (d.rollDie(9)) { 	

		case 1: this.name = "Mace";
		//		Mace	2d4	
		this.weaponStrength = dice.roll(2, 4);
		break;


		case 2: this.name = "Long Sword";
		//		Long sword	3d4	
		this.weaponStrength = dice.roll(3, 4);
		break;

		case 3: this.name = "Short Bow";
		//		Short bow	1d1	
		this.weaponStrength = 1; //1d1 damage 
		break;

		case 4: this.name = "Arrow";
		//		Arrow	2d3	Takes bonuses from equipped short bow.
		this.weaponStrength = dice.roll(2, 3);
		break;

		case 5: this.name = "Dagger"; 
		//		Dagger	1d6
		this.weaponStrength = dice.roll(1,6);
		break;


		case 6: this.name = "Spear"; 
		//		Spear	2d3	1d6 thrown //Thrown Not implemented
		this.weaponStrength = dice.roll(2, 3);
		break;

		case 7: this.name = "Two-Handed Sword"; 
		//		Two handed sword	4d4	
		this.weaponStrength = dice.roll(4,4);
		break;

		case 8: this.name = "Shuriken";
		//		Shuriken	2d4 thrown	
		this.weaponStrength = dice.roll(2, 4);
		break;

		case 9: this.name = "Dart";  
		//		Dart	1d3 thrown	
		this.weaponStrength = dice.roll(1,3);
		break;

		default:
			this.name = "NOP";
			break;
		}

	}	





}















