

import java.util.Random;

public class Food extends Item {
	
	
	public Food() {
		this.boardName = ':';
		this.getFood();
	}
	
	
	
	public void getFood()
	{
	
		DiceRoller d= new DiceRoller();
		switch (d.rollDie(8)) { 
		case 1: this.name = "Kale";
		this.playerHunger = 4;
		break;
		
		case 2: this.name = "Lettuce";
		this.playerHunger = 5;
		break;
		
		case 3: this.name = "Brussel Sprouts";
		this.playerHunger = 6;
		break;
		
		case 4: this.name = "Prune Juice";
		this.playerHunger = 1;
		break;
		
		case 5: this.name = "Chicken";
		this.playerHunger = 7;
		break;
		
		case 6: this.name = "Waffles";
		this.playerHunger = 2;
		break;
		
		case 7: this.name = "Steak";
		this.playerHunger= 8; 
		break;		
		
		case 8: this.name = "Spaghetti";		
		this.playerHunger = 3;		
		break;
		
		case 9: this.name = "Lobster";
		this.playerHunger = 9;
		break;
		
		
		default:
			this.name= "This food has an unusual taste!";
			break;
		}
		
		
		
		
	
	}	
	
	
}


