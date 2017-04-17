
package Item;

import java.util.Random;

public class Food extends Item {
	
	
	public Food() {
		this.boardName = ':';
		this.getFood();
	}
	
	
	
	public void getFood()
	{
	
		DiceRollerItem d= new DiceRollerItem();
		switch (d.rollDie(8)) { 
		case 1: this.name = "Pizza";
		this.playerStrength = 4;
		break;
		
		case 2: this.name = "Hot Dog";
		this.playerStrength = 5;
		break;
		
		case 3: this.name = "Hamburger";
		this.playerStrength = 6;
		break;
		
		case 4: this.name = "Pancakes";
		this.playerStrength = 1;
		break;
		
		case 5: this.name = "Chicken Wings";
		this.playerStrength = 7;
		break;
		
		case 6: this.name = "Waffles";
		this.playerStrength = 2;
		break;
		
		case 7: this.name = "Steak";
		this.playerStrength= 8; 
		break;		
		
		case 8: this.name = "Spaghetti";		
		this.playerStrength = 3;		
		break;
		
		case 9: this.name = "Lobster";
		this.playerStrength = 9;
		break;
		
		
		default:
			this.name= "This food has an unusual taste!";
			break;
		}
		
		
		
		
	
	}	
	
	
}

