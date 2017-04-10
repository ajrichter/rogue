package Item;

public class Weapon extends Item {
	DiceRollerItem dice = new DiceRollerItem();


	public Weapon() {
		this.boardName= ")";
		this.getWeapon();
		
	}


	public void getWeapon()
	{
		DiceRollerItem d= new DiceRollerItem();

		switch (d.rollDie(11)) { 	
	
		case 1: this.name = "Mace";
		this.damage = dice.rollDie(4) + dice.rollDie(4); //2d4 damage 
		break;
		
		
		case 2: this.name = "Long Sword";
		this.damage = dice.rollDie(10); //1d10 damage
		break;
		
		case 3: this.name = "Short Bow";
		this.damage = 1; //1d1 damage 
		break;
		
		case 4: this.name = "Arrow";
		this.damage = 1; //1d1 damage
		break;
		
		case 5: this.name = "Dagger"; 
		this.damage = dice.rollDie(6); //1d6 damage  
		break;
		
		
		case 6: this.name = "Rock"; 
		this.damage = dice.rollDie(2);
		break;
		
		case 7: this.name = "Two-Handed Sword"; 
		this.damage = dice.rollDie(6) + dice.rollDie(6) + dice.rollDie(6); 
		break;
		
		case 8: this.name = "Sling";
		this.damage = 0;
		break;
		
		case 9: this.name = "Dart";  
		this.damage = 1;
		break;
		
		case 10: this.name = "Crossbow";
		this.damage = 1;
		break;
		
		case 11: this.name = "Crossbow bolt";
		this.damage = dice.rollDie(2);
		break;
		
		
		default:
			this.name = "NOP";
			break;
		}
		
	}	


	


}














