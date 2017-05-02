

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
		this.playerHunger = 40;
		break;
		
		case 2: this.name = "lettuce.";
		this.playerHunger = 50;
		break;
		
		case 3: this.name = "brussel sprouts.";
		this.playerHunger = 60;
		break;
		
		case 4: this.name = "prune juice.";
		this.playerHunger = 10;
		break;
		
		case 5: this.name = "chicken.";
		this.playerHunger = 70;
		break;
		
		case 6: this.name = "waffles.";
		this.playerHunger = 20;
		break;
		
		case 7: this.name = "steak.";
		this.playerHunger= 80; 
		break;		
		
		case 8: this.name = "spaghetti.";		
		this.playerHunger = 30;		
		break;
		
		case 9: this.name = "lobster.";
		this.playerHunger = 90;
		break;
		
		
		default:
			this.name= "This food has an unusual taste!";
			break;
		}
		
		
		
		
	
	}	
	
	
}


