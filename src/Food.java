

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
		case 1: this.name = "kale.";
		this.playerHunger = 4;
		break;
		
		case 2: this.name = "lettuce.";
		this.playerHunger = 5;
		break;
		
		case 3: this.name = "brussel sprouts.";
		this.playerHunger = 6;
		break;
		
		case 4: this.name = "prune juice.";
		this.playerHunger = 1;
		break;
		
		case 5: this.name = "chicken.";
		this.playerHunger = 7;
		break;
		
		case 6: this.name = "waffles.";
		this.playerHunger = 2;
		break;
		
		case 7: this.name = "steak.";
		this.playerHunger= 8; 
		break;		
		
		case 8: this.name = "spaghetti.";		
		this.playerHunger = 3;		
		break;
		
		case 9: this.name = "lobster.";
		this.playerHunger = 9;
		break;
		
		
		default:
			this.name= "This food has an unusual taste!";
			break;
		}
		
		
		
		
	
	}	
	
	
}


